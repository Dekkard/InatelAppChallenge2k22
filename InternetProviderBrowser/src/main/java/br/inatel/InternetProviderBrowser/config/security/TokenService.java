package br.inatel.InternetProviderBrowser.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.inatel.InternetProviderBrowser.config.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	private static String secretKeyC = "rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\\\}x3?JR3.2zr~v)gYF^8\\\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\\\J?N,nvH.<2\\\\.r~w]*e~vgak)X\\\"v8H`MH/7\\\"2E`,^k@n<vE-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/";

	private static String secretKeyI = "rm'!@N=Ke!~p8VTA2ZRK~nMDQX5Uvm!m'D&]{@Vr?G;2?XhbC:Qa#9#eMLN\\\\}x3?JR3.2zr~v)gYF^8\\\\:8>:XfB:Ww75N/emt9Yj[bQMNCWwW\\\\J?N,nvH.<2\\\\.r~w]*e~vgak)-wD3g9JWPy;CrY*.Kd2_D])=><D?YhBaSua5hW%{2]_FVXzb9`8FH^b[X3jzVER&:jw2<=c38=>L/zBq`}C6tT*cCSVC^c]-L}&/";

	public String gerarToken(Authentication a) {
		Date now = new Date();
		return Jwts.builder().setIssuer("Spring Boot Security Model")//
				.setSubject(((User) a.getPrincipal()).getId().toString())//
				.setIssuedAt(now)//
				.setExpiration(new Date(now.getTime() + (900000l)))//
				.signWith(SignatureAlgorithm.HS256, secretKeyC)//
				.compact();
	}

	public boolean isClientValid(String token) {
		try {
			Jwts.parser().setSigningKey(secretKeyC).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isInstallerValid(String token) {
		try {
			Jwts.parser().setSigningKey(secretKeyI).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getClientUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKeyC).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public Long getInstallerUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKeyI).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
