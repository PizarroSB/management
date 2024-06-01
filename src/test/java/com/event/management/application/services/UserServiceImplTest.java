package com.event.management.application.services;

import com.event.management.application.exceptions.BussinesException;
import com.event.management.application.models.dto.UserDto;
import com.event.management.application.services.impl.UserServiceImpl;
import com.event.management.infrastructure.persistence.entities.EventEntity;
import com.event.management.infrastructure.persistence.entities.UserEntity;
import com.event.management.infrastructure.persistence.repository.EventRepository;
import com.event.management.infrastructure.persistence.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    EventRepository evetRepository;

    UserEntity userEntity = new UserEntity();
    UserDto userDto = new UserDto();
    EventEntity eventEntity = new EventEntity();


    @BeforeEach
    public void setUp(){
        userEntity = new UserEntity("1","John Mario",new ArrayList<>());
        userDto = new UserDto("1","John Mario",new ArrayList<>());
        eventEntity = new EventEntity("1","prueba","2024-01-02","mi casa",new ArrayList<>());
    }

    @Test
    public void registerUserInEvent_CREATED(){
        String idUser = "1";
        String idEvent = "1";
        String responseExpected = "SE REGISTRO EL USUARIO CORRECTAMENTE EN EL EVENTO" ;

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(evetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(eventEntity));
        userEntity.getEvents().add(eventEntity);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userEntity);

        String response = userService.regiserUserInEvent(idUser,idEvent);

        Assertions.assertEquals(response,responseExpected);

    }

    @Test
    public void registerUserInEvent_NOT_CREATED(){
        String idUser = "1";
        String idEvent = "1";

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(userEntity));
        Mockito.when(evetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(eventEntity));
        userEntity.getEvents().add(eventEntity);
        Mockito.when(userRepository.save(Mockito.any())).thenThrow(BussinesException.class);

        Assertions.assertThrows(BussinesException.class,()->{
            userService.regiserUserInEvent(idUser,idEvent);
        });
    }

    @Test
    public void getEventsByUser(){
        String idUser= "2";
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(userEntity));
        UserDto userdto = userService.getEventsByUser(idUser);
        Assertions.assertNotNull(userdto.getName());
    }

    @Test
    public void getEventsByUser_NO_USER(){
        String idUser= "2";
        Mockito.when(userRepository.findById(Mockito.any())).thenThrow(BussinesException.class);
        Assertions.assertThrows(BussinesException.class,()->{
            userService.getEventsByUser(idUser);
        });
    }
}
