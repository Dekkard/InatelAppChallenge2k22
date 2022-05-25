package br.inatel.InternetProviderBrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import br.inatel.InternetProviderBrowser.config.security.AuthenticationService;
import br.inatel.InternetProviderBrowser.config.security.TokenService;
import br.inatel.InternetProviderBrowser.config.security.UserRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthenticationService authServ;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserRepo userRepo;

//	@Bean
//	public AuthenticationManager authenticationBuilder(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(authServ).passwordEncoder(new BCryptPasswordEncoder());
//		return auth.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth1 -> {
			try {
				auth1//
//						.antMatchers("/").permitAll()//
//						.anyRequest().authenticated().and().csrf().disable()//
//						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
//						.and().addFilterBefore(new AuthenticatorTokenFilter(tokenService, userRepo),
//								UsernamePasswordAuthenticationFilter.class);//
						.anyRequest().permitAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (web) -> web.ignoring().anyRequest();
	}

}
