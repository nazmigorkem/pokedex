package tech.obss.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.obss.interceptor.RequestInInterceptor;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Autowired
    private RequestInInterceptor requestInInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInInterceptor);
    }
}
