package kr.co.example.fms.config;

import kr.co.example.fms.UserCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserCustomService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .antMatchers("/**").permitAll()
                .antMatchers("/").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/mof100_packets").hasRole("USER")
                .antMatchers("/chart/**").hasRole("USER")
                .antMatchers("/login","/**").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/mof100_packets", true)
                .and()
                .csrf().disable();
    }

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("sbkang").password("{noop}1z2x3c4v")
                .authorities("ROLE_USER");
    }

}
