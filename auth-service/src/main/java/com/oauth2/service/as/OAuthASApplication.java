package com.oauth2.service.as;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class OAuthASApplication implements AuthorizationServerConfigurer, ResourceServerConfigurer {

    @Autowired protected AuthenticationManager authenticationManager;

    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(

                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .authorities("ROLE_USER")
                        .build()

        );
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()

                // internal client config

                .withClient("internal")
                .secret("internal_secret")
                .scopes("account", "contacts", "internal")
                .resourceIds()
                .authorizedGrantTypes("refresh_token", "password") // to get the access token uses directly username+password
                .autoApprove(true) // all scopes are auto approved for internal client (no scopes approval view is displayed)
                .accessTokenValiditySeconds(10*60) // by default for the internal client access token will expiry after 10 min
                .refreshTokenValiditySeconds(30*60); // by default for the internal client refresh token will expiry after 30 min


    }
    @SuppressWarnings("deprecation")
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService())
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();	}

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Configuration
    public static class AuthenticationMananagerProvider extends WebSecurityConfigurerAdapter {

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(OAuthASApplication.class, args);
    }

}