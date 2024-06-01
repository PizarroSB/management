package com.event.management.application.services.impl;

import com.event.management.application.exceptions.BussinesException;
import com.event.management.application.mappers.UserMapper;
import com.event.management.application.models.dto.UserDto;
import com.event.management.application.services.usecases.IUserService;
import com.event.management.infrastructure.persistence.entities.EventEntity;
import com.event.management.infrastructure.persistence.entities.UserEntity;
import com.event.management.infrastructure.persistence.repository.EventRepository;
import com.event.management.infrastructure.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    @Override
    public String regiserUserInEvent(String idUser, String idEvent) {
        try {
            UserEntity userEntity = userRepository.findById(idUser).orElseThrow(()-> new BussinesException(
                    "NO SE ENCONTRO EL USUARIO", HttpStatus.OK));

            EventEntity eventEntity = eventRepository.findById(idEvent).orElseThrow(()-> new BussinesException(
                    "NO SE ENCONTRO EL EVENTO", HttpStatus.OK));

            userEntity.getEvents().add(eventEntity);
            userRepository.save(userEntity);

            if(userEntity.getEvents().isEmpty()){
                throw new BussinesException("EL USUARIO NO SE REGISTRO PARA EL EVENTO",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (BussinesException e){
            throw new BussinesException(e.getMessage(),e.getStatus());
        }

        return "SE REGISTRO EL USUARIO CORRECTAMENTE EN EL EVENTO";
    }

    @Override
    public UserDto getEventsByUser(String userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(()-> new BussinesException(
                "NO SE ENCONTRO EL USUARIO", HttpStatus.OK));
        return UserMapper.MAPPER.toDto(userEntity);
    }
}
