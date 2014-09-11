package br.com.example.springoauthresserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
            .authorizeRequests()
                .antMatchers("/list").access("#oauth2.hasScope('list')")
                .antMatchers("/get").access("#oauth2.hasScope('get')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
            .resourceId("spring-resource-server")
            .tokenServices(tokenServices());
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();

        tokenServices.setClientId("client");
        tokenServices.setClientSecret("123456");
        tokenServices.setCheckTokenEndpointUrl("http://authorization.example.com:18080/oauth/check_token");

        return tokenServices;
    }
}
