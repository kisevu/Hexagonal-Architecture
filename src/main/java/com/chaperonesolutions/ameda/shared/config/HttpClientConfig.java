package com.chaperonesolutions.ameda.shared.config;


import com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.JsonPlaceHolderApi;
import com.chaperonesolutions.ameda.customer.infrastructure.client.jsonplaceholder.JsonPlaceholderErrorHandler;
import com.chaperonesolutions.ameda.shared.observability.DownstreamLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = JsonPlaceHolderApi.GROUP, types = JsonPlaceHolderApi.class)
public class HttpClientConfig {

    @Bean
    public RestClientHttpServiceGroupConfigurer downStreamLoggingConfigurer(){
        return groups ->  groups.forEachClient((group ,builder ) ->
                builder.requestInterceptor( new DownstreamLoggingInterceptor(group.name())));
    }

    @Bean
    public RestClientHttpServiceGroupConfigurer jsonPlaceHolderConfigurer(JsonPlaceholderErrorHandler errorHandler){
        return groups -> groups.filterByName(JsonPlaceHolderApi.GROUP)
                .forEachClient((group, builder) -> builder.defaultStatusHandler(HttpStatusCode::isError, errorHandler));
    }
}
