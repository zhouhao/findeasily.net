package net.findeasily.website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService currentUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private static final String[] PUBLIC_PAGES = new String[]{
            "/",
            "/signup",
            "/login/**",
            "/account_confirmation",
            "/password/**",
            "/public/**",
            "/css/**",
            "/js/**",
            "/fonts/**",
            "/images/**",
            "/listing/**"
    };

    @Autowired
    public SecurityConfig(UserDetailsService currentUserDetailsService, PasswordEncoder passwordEncoder) {
        this.currentUserDetailsService = currentUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_PAGES).permitAll()
                .antMatchers("/users/**").hasAuthority("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .usernameParameter("email")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("remember-me")
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                    .rememberMe();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(currentUserDetailsService).passwordEncoder(passwordEncoder);
    }

}