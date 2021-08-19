package examples.encrypt.jwt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import examples.encrypt.jwt.JwtToken.JwtExtend;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JwtTokenTest {

	static String secretkey = "secret-key";

	@Test
	@Order(1)
	public static Stream<String> createToken() {
		String token = JwtToken.createToken(secretkey, new JwtExtend(secretkey, "admin,user,user1"));
		assertNotNull(token);
		return Stream.of(token);

	}

	@ParameterizedTest
	@MethodSource("createToken")
	@Order(2)
	public void parseToken(String token) {
		System.out.println(JwtToken.parseToken(secretkey, token));
	}

	@ParameterizedTest
	@MethodSource("createToken")
	@Order(3)
	public void getDate(String token) {
		System.out.println(JwtToken.getIssuedAt(secretkey, token));
		System.out.println(JwtToken.getExpiration(secretkey, token));
	}
}
