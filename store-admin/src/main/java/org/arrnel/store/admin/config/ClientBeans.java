package org.arrnel.store.admin.config;

import org.arrnel.store.admin.security.OAuthClientHttpRequestInterceptor;
import org.arrnel.store.admin.service.RestClientProductsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsService productsRestClient(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${store.services.products.registration-id:keycloak}") String registrationId,
            @Value("${store.services.products.uri:http://localhost:8081}") String productsBaseUri
    ) {
        return new RestClientProductsService(RestClient.builder()
                .requestInterceptor(new OAuthClientHttpRequestInterceptor(
                        new DefaultOAuth2AuthorizedClientManager(
                                clientRegistrationRepository,
                                authorizedClientRepository),
                        registrationId))
                .baseUrl(productsBaseUri)
                .build());
    }
}
