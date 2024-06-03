package com.develhope.spring.configuration;


import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.models.Rental;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    /**
     * Crea e restituisce un bean ModelMapper.
     *
     * @return un'istanza configurata di ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
