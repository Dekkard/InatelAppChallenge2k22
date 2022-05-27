package br.inatel.InternetProviderBrowser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.inatel.InternetProviderBrowser.config.security.AuthenticationService;
import br.inatel.InternetProviderBrowser.config.security.AuthenticatorTokenFilter;
import br.inatel.InternetProviderBrowser.config.security.TokenService;
import br.inatel.InternetProviderBrowser.config.security.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationService authServ;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserService userService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
//	@Bean
//	public AuthenticationManager authenticationBuilder(AuthenticationManagerBuilder auth) throws Exception {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authServ).passwordEncoder(new BCryptPasswordEncoder());
//		return auth.build();
	}
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()//
				.antMatchers("/").permitAll()
				.antMatchers("/auth/client").permitAll()//
				.antMatchers("/auth/client/registry").permitAll()//
				.antMatchers("/auth/installer").permitAll()//
				.antMatchers("/auth/installer/registry").permitAll()//
				.antMatchers("/client").hasAuthority("Client")//
				.antMatchers(HttpMethod.GET, "/plan").hasAuthority("Client")//
				.antMatchers("/installer").hasAuthority("Installer")//
				.antMatchers("/plan").hasAuthority("Installer")//
				.anyRequest().authenticated()//
				.and().csrf().disable()//
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
				.and().addFilterBefore( //
						new AuthenticatorTokenFilter(tokenService, userService),
						UsernamePasswordAuthenticationFilter.class);
//		return http.build();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	}
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
//		return (web) -> web.ignoring().anyRequest();
//	}

}
