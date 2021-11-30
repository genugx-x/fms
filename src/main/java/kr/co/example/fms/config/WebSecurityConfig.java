package kr.co.qsol.fishery.config;

import kr.co.qsol.fishery.UserCustomService;
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
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/mof100/**").hasRole("USER")
//                .antMatchers("/", "/login","/**").permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .csrf().disable();
    }

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());*/

//       auth.inMemoryAuthentication()
//               .withUser("user").password("{noop}!qsol1235")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("device6").password("{noop}qsol!@#$")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("keti").password("{noop}keti1234")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("hoseo").password("{noop}oceanit212")
//               .authorities("ROLE_USER")
//               .and()
//                // 수정
//               .withUser("ecoaqua").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("join01").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("join02").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("central01").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("central02").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER")
//               .and()
//               .withUser("sgfs").password("{noop}1z2x3c4v")
//               .authorities("ROLE_USER");

    }

   /* @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
}
