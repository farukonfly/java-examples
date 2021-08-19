package examples.springsecurity.oauth2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/getCurrentUser")
	public Object getCurrentUser(Authentication authentication,HttpServletRequest request) {
		String head = request.getHeader("Authorization");
		String token = head.substring(head.indexOf("bearer ")+7);
		//解析token
		return authentication.getPrincipal();
	}
}
