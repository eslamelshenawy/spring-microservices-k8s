package vmware.services.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import vmware.services.gateway.filter.JwtAuthenticationFilter;
import vmware.services.gateway.service.InvalidLoginAttemptHandler;
import vmware.services.gateway.service.UserAuthDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private InvalidLoginAttemptHandler invalidLoginAttemptHandler;


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder

                .userDetailsService(userAuthDetailsService)
                .passwordEncoder(passwordEncoder());

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] PUBLIC_PATH = {
            "/authenticate/**",
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(invalidLoginAttemptHandler)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_PATH)
                .permitAll()
                .anyRequest().authenticated();
         http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
