package org.arrnel.store.admin.config;

import org.arrnel.store.admin.service.RestClientProductsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsService productsRestClient(
            @Value("${store.services.products.uri:http://localhost:8081}") String productsBaseUri) {
        return new RestClientProductsService(RestClient.builder()
                .baseUrl(productsBaseUri)
                .build());
    }
}
