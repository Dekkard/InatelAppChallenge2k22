package br.inatel.InternetProviderBrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.inatel.InternetProviderBrowser.config.security.AuthenticatorTokenFilter;
import br.inatel.InternetProviderBrowser.config.security.TokenService;
import br.inatel.InternetProviderBrowser.config.security.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfigInstaller {
//	@Autowired
//	private ClientAuthenticationService authServ;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;

//	@Bean
//	public AuthenticationManager authenticationBuilder(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(authServ).passwordEncoder(new BCryptPasswordEncoder());
//		return auth.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()//
				.antMatchers("/").permitAll()//
				.antMatchers("/installer").authenticated()//
				.antMatchers("/plan").authenticated()//
				.anyRequest().denyAll()//
				.and().csrf().disable()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and().addFilterBefore( //
						new AuthenticatorTokenFilter(tokenService, userService),
						UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (web) -> web.ignoring().anyRequest();
	}

}
