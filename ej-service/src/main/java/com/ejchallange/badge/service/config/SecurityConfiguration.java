package com.ejchallange.badge.service.config;

import com.ejchallange.badge.service.domain.User;
import com.ejchallange.badge.service.filter.CORSFilter;
import com.ejchallange.badge.service.service.SpringDataJpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private SpringDataJpaUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.userDetailsService)
			.passwordEncoder(User.PASSWORD_ENCODER);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
			.authorizeRequests()
			.antMatchers("/register").permitAll()
			.antMatchers("/reports/topList").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.defaultSuccessUrl("/", true)
			.permitAll()
			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			.logout()
			.logoutSuccessUrl("/");
	}


}
