//package kr.co.qsol.fishery.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.CacheControl;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.resource.PathResourceResolver;
//
//@Configuration
//@ComponentScan
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        CacheControl.noCache();
//        registry.addResourceHandler("/resources/static/**").addResourceLocations("/resources");
//
//        registry
//                .addResourceHandler("/js/**", "/css/**")
//                .addResourceLocations("/js/", "/css/")
//                .setCachePeriod(3600)
//                .resourceChain(true)
//                //           .addResolver(new GzipResourceResolver())
//                .addResolver(new PathResourceResolver());
//    }
//
//}
