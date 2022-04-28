package com.nyu.nyugateway.Config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerAggregatorController implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public SwaggerAggregatorController(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources= new ArrayList<>();
        routeLocator.getRoutes().subscribe(
                route -> {
                    SwaggerResource swaggerResource = new SwaggerResource();
                    swaggerResource.setName(route.getId());
                    swaggerResource.setLocation("/" + route.getId().toLowerCase() + "/v2/api-docs?group=" + route.getId());
                    resources.add(swaggerResource);
                }
        );
        return resources;
    }
}
