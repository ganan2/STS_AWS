package com.devopsbuddy.config;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.devopsbuddy.backend.service.UserSecurityService;
import com.devopsbuddy.web.controllers.ForgotMyPasswordController;
import com.devopsbuddy.web.controllers.SignupController;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    
    @Autowired
    private UserSecurityService userSecurityService;

    /** The encryption SALT. */
    private static final String SALT = "dmspmf;sdjf;s;fmkas['f,";
    //fdalkjalk;3jlwf00sfaof

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /** Public URLs. */
    private static final String[] PUBLIC_MATCHERS = {
    		// these are publicly accessible URLS. 
    		//One needs to be careful what needs to be public.
            "/webjars/**",
            "/css/**",		//static content
            "/js/**",		//static content
            "/images/**",	//static content
            "/",			//home page
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            ForgotMyPasswordController.FORGOT_PASSWORD_URL_MAPPING,
            ForgotMyPasswordController.CHANGE_PASSWORD_PATH,
            SignupController.SIGNUP_URL_MAPPING
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("dev")) {
            http.csrf().disable(); /** Required to disable while developing H2 */
            http.headers().frameOptions().disable();
        }
        
        http
                .authorizeRequests()						// authorize all http security
                .antMatchers(PUBLIC_MATCHERS).permitAll()	// No need of authentication for pu
                .anyRequest().authenticated()				// Authenticate everything else
                .and()										
                .formLogin().loginPage("/login").defaultSuccessUrl("/payload")	// After successful login, redirect the users to /payload
                .failureUrl("/login?error").permitAll()		// Send a getResuest() to the login URL with the parameter error
                .and()
                .logout().permitAll();						// Allow anyone to logout without authentication
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}
