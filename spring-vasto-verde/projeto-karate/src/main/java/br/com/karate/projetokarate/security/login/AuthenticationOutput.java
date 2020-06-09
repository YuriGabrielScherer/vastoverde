package br.com.karate.projetokarate.security.login;

public class AuthenticationOutput {

	private final String jwt;

	/**
	 * @param jwt
	 */
	public AuthenticationOutput(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
