package com.cqjtu.pcy.online_deal_center.config;

import com.cqjtu.pcy.online_deal_center.component.SecurityInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInteceptor())
                .addPathPatterns("/personal_center.html","/shopcart.html","/pay.html","addToCart");
    }
}
