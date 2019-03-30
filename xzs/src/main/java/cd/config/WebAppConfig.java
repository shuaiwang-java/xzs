package cd.config;

import cd.IService.UserIService;
import cd.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置拦截路径
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    /*@Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }*/

    @Autowired
    LoginInterceptor loginInterceptor;


    //添加拦截对象
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }

    //跨域访问
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
