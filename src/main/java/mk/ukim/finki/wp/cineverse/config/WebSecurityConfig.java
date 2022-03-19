package mk.ukim.finki.wp.cineverse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider customUsernamePasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernamePasswordAuthenticationProvider = customUsernamePasswordAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about-us", "/movies", "/movie/{id}", "/movie/{id}/{trailer}", "/register", "/not-found", "/access-denied", "/assets/**",
                        "/css/**", "/img/**", "/video/**", "/js/**", "https://**", "http://**",
                        "www.facebook.com/**").permitAll()
                .antMatchers("/profile/user/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("cineverse-cookie")
                .logoutSuccessUrl("/home")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                auth.inMemoryAuthentication()
//                .withUser("zagorka.jovanova")
//                .password(passwordEncoder.encode("zj"))
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("admin"))
//                .authorities("ROLE_ADMIN");
        auth.authenticationProvider(customUsernamePasswordAuthenticationProvider);
    }
}
