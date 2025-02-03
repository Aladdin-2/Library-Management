package com.aladdin.demobooksite.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Daha sadədir. Ancaq runtime da
// reflection istifadə etdiyi üçün bəzən əl ilə mapplama qədər sürətli olmaya bilər
@Configuration
public class ConfigurationClass {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}