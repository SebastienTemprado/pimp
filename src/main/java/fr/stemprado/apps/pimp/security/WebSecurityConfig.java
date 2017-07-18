package fr.stemprado.apps.pimp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import fr.stemprado.apps.pimp.constants.api.AuthenticationApi;
import fr.stemprado.apps.pimp.services.constants.api.UserApi;
import fr.stemprado.apps.pimp.services.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Bean
	public SpringSecurityDialect securityDialect() {
	    return new SpringSecurityDialect();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            //TODO : UserApi.GET_USER added for IT to verify that an user can be added. No authentication during IT 
                .antMatchers("/css/*", "/img/*", "/js/*", AuthenticationApi.SIGN_UP_FORM, AuthenticationApi.SIGN_UP, UserApi.ADD_USER, UserApi.GET_USER).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(AuthenticationApi.LOGIN).successForwardUrl(AuthenticationApi.LOGGED_IN)
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
