package com.thinkbit.engine;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@MapperScan(basePackages = "com.thinkbit.engine.repository")
@SpringBootApplication(scanBasePackages = {"com.thinkbit.engine"})
public class EngineApplication extends WebMvcConfigurerAdapter {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(30);
        pool.setMaxPoolSize(80);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();

        supportedMediaTypes.add(new MediaType("*", "UTF-8", Charset.forName("UTF-8")));

        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonConverter.setSupportedMediaTypes(supportedMediaTypes);
        fastJsonConverter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(fastJsonConverter);
        super.configureMessageConverters(converters);
    }



    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);

    }
}
