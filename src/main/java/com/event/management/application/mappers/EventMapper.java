package com.event.management.application.mappers;

import com.event.management.application.models.dto.EventDto;
import com.event.management.infrastructure.persistence.entities.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "name",target = "name")
    EventDto toDto(EventEntity event);
    @Mapping(source = "name",target = "name")
    EventEntity toEntity(EventDto dto);
}
