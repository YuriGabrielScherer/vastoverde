package br.com.karate.projetokarate.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.karate.projetokarate.data.pessoa.PessoaService;
import br.com.karate.projetokarate.security.login.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private JwtUtil jwtUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String bearer = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		if (bearer != null && bearer.startsWith("Bearer ")) {
			jwt = bearer.substring(7);

			try {
				username = this.jwtUtil.extractUsername(jwt);
			} catch (Exception e) {
				LOGGER.warn("Erro ao extrair username do Bearer token... " + e.getMessage());
				System.out.println(e.getMessage());
			}
		} else {
			// TODO Não autorizado.
			LOGGER.warn("Não autorizado.");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.pessoaService.loadUserByUsername(username);
			if (this.jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken //
						.setDetails( //
								new WebAuthenticationDetailsSource()//
										.buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
