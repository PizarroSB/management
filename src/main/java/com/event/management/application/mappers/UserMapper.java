package com.event.management.application.mappers;

import com.event.management.application.models.dto.UserDto;
import com.event.management.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name",target = "name")
    UserDto toDto(UserEntity userEntity);
    @Mapping(source = "name",target = "name")
    UserEntity toEntity(UserDto userDto);
}
