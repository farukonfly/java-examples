package examples.springsecurity.oauth2.config.password;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import examples.springsecurity.oauth2.JwtTokenEnhancer;
import examples.springsecurity.oauth2.UserService;

/**
 * 授权服务器配置
 * @author farukon
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig_Password extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder ;
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	@Autowired
	private UserService userService ;
	
//	@Autowired
//	private TokenStore RedisTokenStore;
	
	@Autowired
	private TokenStore jwtTokenStore ;
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	@Autowired
	private JwtTokenEnhancer jwtTokenEnhancer ;

	/**
	 * 使用密码模式所需的配置
	 * @param endpoints
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> delegates = new ArrayList<>();
		delegates.add(jwtTokenEnhancer);
		delegates.add(accessTokenConverter);
		enhancerChain.setTokenEnhancers(delegates);
		endpoints.authenticationManager(authenticationManager).userDetailsService(userService)
	//	.tokenStore(RedisTokenStore);
		.tokenStore(jwtTokenStore)
		.accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(enhancerChain);
	}



	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("admin")
		.secret(passwordEncoder.encode("112233"))
		//.accessTokenValiditySeconds(3600) //3600秒
		.redirectUris("http://www.baidu.com")
		.scopes("all")
		.authorizedGrantTypes("password");
	}

}
