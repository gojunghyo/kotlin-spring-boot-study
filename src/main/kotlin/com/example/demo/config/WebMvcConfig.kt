package com.example.demo.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class WebMvcConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    @Override
    fun configureMessageConverters(converters: List<HttpMessageConverter<*>>){
        val converter = MappingJackson2HttpMessageConverter(this.objectMapper())

        converters.plus(converter)
        converters.plus(StringHttpMessageConverter())
    }
}
