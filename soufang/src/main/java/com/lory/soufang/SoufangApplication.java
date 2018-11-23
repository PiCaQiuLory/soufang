package com.lory.soufang;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.lory.soufang.repository")
@RestController
public class SoufangApplication implements WebMvcConfigurer{

	@GetMapping("/")
	public String hello(){
		return "hello, lory";
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		WebMvcConfigurer.super.configureMessageConverters(converters);
		//创建fastjson转换器实例
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		//配置对象
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteMapNullValue,
				SerializerFeature.PrettyFormat,SerializerFeature.QuoteFieldNames);
		List<MediaType> mediaTypes = new ArrayList<>();
		//中文编码
		MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
		mediaTypes.add(mediaType);
		converter.setSupportedMediaTypes(mediaTypes);
		converter.setFastJsonConfig(config);
		converters.add(converter);
	}

	public static void main(String[] args) {
		SpringApplication.run(SoufangApplication.class, args);
	}
}
