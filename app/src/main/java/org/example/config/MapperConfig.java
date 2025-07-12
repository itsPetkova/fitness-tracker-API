package org.example.config;

import org.example.dto.AppRegisterDto;
import org.example.dto.DataEntryResponse;
import org.example.entity.Application;
import org.example.entity.DataEntry;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(DataEntry.class, DataEntryResponse.class).addMappings(mapper ->
                mapper.map(src -> src.getApplication().getName(), DataEntryResponse::setApplication)
        );

        modelMapper.typeMap(AppRegisterDto.class, Application.class).addMappings(mapper ->
                mapper.using(ctx -> ((AppRegisterDto.Category) ctx.getSource()).name().toLowerCase())
                        .map(AppRegisterDto::getCategory, Application::setCategory)
        );

        return modelMapper;
    }
}

