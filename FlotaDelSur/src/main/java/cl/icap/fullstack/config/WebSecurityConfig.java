package cl.icap.fullstack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index", "/css/*", "/js/*", "/images/*", "/fonts/*","/valores/**","/agenda_viajes/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		}
	
	
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
				User.withDefaultPasswordEncoder()
					.username("user")
					.password("1234")
					.roles("USER")
					.build();
		
		return new InMemoryUserDetailsManager(user);
		
	}
	}
	