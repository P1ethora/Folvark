package net.plethora.folvark.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @SneakyThrows
//    @Override
//    protected void configure(HttpSecurity httpSecurity) {
//        httpSecurity
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/maps").hasAnyRole("MODERATOR")
//        .anyRequest()
//        .authenticated()
//        .and()
//        .formLogin().permitAll()
//        .and()
//        .httpBasic();
//    }

//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("moderator")
//                        .password("moderator")
//                        .roles("MODERATOR")
//                        .build()
//        );
//    }
}
