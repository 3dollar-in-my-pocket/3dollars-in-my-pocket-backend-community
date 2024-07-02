package com.threedollar.config;

import com.threedollar.config.interceptor.ApiKeyHandleInterceptor;
import com.threedollar.config.resolver.RequestApiKeyResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApiKeyHandleInterceptor apiKeyHandleInterceptor;

    private final RequestApiKeyResolver requestApiKeyResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyHandleInterceptor)
            .excludePathPatterns(
                "/ping",
                "/health/*",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/v3/api-docs/**"
            );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestApiKeyResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


    }
}
