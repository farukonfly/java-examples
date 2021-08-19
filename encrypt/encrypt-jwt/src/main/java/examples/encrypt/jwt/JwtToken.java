package examples.encrypt.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {

	private final static SignatureAlgorithm SIGNATUREALGORITHM = SignatureAlgorithm.HS256;
	private final static String ISSUER = "farukon"; // 签发人
	private final static String CLAIMS_AUTHORITIES_PARAMETER = "authorities" ;
	private final static int EXPIRATION_MINUTES = 30 ; //30分钟失效

	public static String createToken(String signkey, JwtExtend jwt) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expiration = now.plusMinutes(EXPIRATION_MINUTES);
		Claims claims = Jwts.claims().setIssuer(ISSUER) // 签发人
				.setSubject(jwt.getUsername()) // 主题
				.setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))// 签发时间		
				.setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()));
		claims.put(CLAIMS_AUTHORITIES_PARAMETER, jwt.getAuthorities());
		return Jwts.builder().setClaims(claims).signWith(SIGNATUREALGORITHM, signkey).compact();

	}
	
	public static String getUsername(String secretkey, String token) {
		Claims claims = getClaims(secretkey,token);
		return claims.getSubject();
	}
	
	public static String getAuthorities(String secretkey, String token) {
		Claims claims = getClaims(secretkey,token);
		return (String)claims.get(CLAIMS_AUTHORITIES_PARAMETER);
	}
	
	//签发时间
	public static Date getIssuedAt(String secretkey, String token) {
		Claims claims = getClaims(secretkey,token);
		return claims.getIssuedAt();
	}

   //过期时间
	public static Date getExpiration(String secretkey, String token) {
		Claims claims = getClaims(secretkey,token);
		return claims.getExpiration();
	}
	
	public static JwtExtend parseToken(String secretkey, String token) {
		Claims claims = getClaims(secretkey,token);
		return new JwtExtend(claims.getSubject(), (String) claims.get(CLAIMS_AUTHORITIES_PARAMETER));
	}
	
	
	private static Claims getClaims(String secretkey, String token) {
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}

	public static class JwtExtend{
		private String username;
		private String authorities;

		public JwtExtend(String username, String authorities) {
			super();
			this.username = username;
			this.authorities = authorities;
		}

		public String getUsername() {
			return username;
		}

		public String getAuthorities() {
			return authorities;
		}

		@Override
		public String toString() {
			return "JwtExtend [username=" + username + ", authorities=" + authorities + "]";
		}

		
	}
}
