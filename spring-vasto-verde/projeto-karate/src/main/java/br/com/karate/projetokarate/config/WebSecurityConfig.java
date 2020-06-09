package br.com.karate.projetokarate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.karate.projetokarate.data.pessoa.PessoaService;
import br.com.karate.projetokarate.security.filter.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Configurações das Rotas e suas Permissões
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/**", "/pessoa/cadastrar").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.and()
//				.authorizeRequests()
//					.antMatchers("/pessoa/*")
//			.and()
				.authorizeRequests()
					.anyRequest().authenticated()
			.and()
			.sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Setando qual é a fonte de informação para realizar o Login.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth //
			.userDetailsService(pessoaService)
			.passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
