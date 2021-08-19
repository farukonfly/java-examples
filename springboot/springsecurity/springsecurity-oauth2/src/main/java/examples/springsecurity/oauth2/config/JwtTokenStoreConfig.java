package examples.springsecurity.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import examples.springsecurity.oauth2.JwtTokenEnhancer;

@Configuration
public class JwtTokenStoreConfig {

	@Bean
	@DependsOn("jwtAccessTokenConverter")
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("sigin-key");
		return jwtAccessTokenConverter;
	}
	
	@Bean
	public JwtTokenEnhancer jwtTokenEnhancer() {
		return new JwtTokenEnhancer();
	}

}
