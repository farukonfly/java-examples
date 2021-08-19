package examples.springsecurity.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getUserInMemory(username);
	}
	
	private UserDetails getUserInMemory(String username) {
		String password = passwordEncoder.encode("123456");
		return  new MyUser(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
