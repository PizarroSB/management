package com.event.management.infrastructure.controllers;

import com.event.management.application.models.dto.EventDto;
import com.event.management.application.models.dto.UserDto;
import com.event.management.application.models.request.RequestEvent;
import com.event.management.application.models.request.RequestRegistryUserInEvent;
import com.event.management.application.services.usecases.IEventService;
import com.event.management.application.services.usecases.IUserService;
import com.event.management.infrastructure.models.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final IEventService eventService;
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<ResponseModel<List<EventDto>>> getAllEvents(){
        List<EventDto> responseEvents = eventService.getAllEvents();
        ResponseModel<List<EventDto>> responseModel = new ResponseModel<>(responseEvents,
                "SE CONSULTARON LOS EVENTOS DE MANERA EXITOSA", HttpStatus.OK.value());

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<EventDto>> getEventById(@PathVariable String id){
        EventDto responseEvents = eventService.getEventById(id);
        ResponseModel<EventDto> responseModel = new ResponseModel<>(responseEvents,
                "SE CONSULTO EL EVENTO DE MANERA EXITOSA", HttpStatus.OK.value());

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<EventDto>> saveEvent(@RequestBody RequestEvent requestEvent){
        EventDto responseEvent = eventService.saveEvent(requestEvent);
        ResponseModel<EventDto> responseModel = new ResponseModel<>(responseEvent,
                "SE GUARDO EL EVENTO DE MANERA EXITOSA", HttpStatus.CREATED.value());
        return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseModel<EventDto>> saveEvent(@PathVariable String id,@RequestBody RequestEvent requestEvent){
        EventDto responseEvent = eventService.updateEvent(id,requestEvent);
        ResponseModel<EventDto> responseModel = new ResponseModel<>(responseEvent,
                "SE ACTUALIZO EL EVENTO DE MANERA EXITOSA", HttpStatus.CREATED.value());
        return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<ResponseModel<String>> registerUserInEvent(@PathVariable String id,@RequestBody RequestRegistryUserInEvent request){
        String responseEventRegister = userService.regiserUserInEvent(request.getIdUser(),id);
        ResponseModel<String> responseModel = new ResponseModel<>(responseEventRegister,
                "REGISTRO EXITOSO", HttpStatus.CREATED.value());
        return new ResponseEntity<>(responseModel,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<String>> deleteEventById(@PathVariable String id){
        String responseEvents = eventService.deleteEvent(id);
        ResponseModel<String> responseModel = new ResponseModel<>(responseEvents,
                "REGISTRO ELIMINADO", HttpStatus.OK.value());

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ResponseModel<UserDto>> getUserEvents(@PathVariable String userId){
        UserDto responseUser = userService.getEventsByUser(userId);
        String message = "SE CONSULTO EL USUARIO DE MANERA EXITOSA" ;
        if(responseUser.getEvents().isEmpty()){
            message = "SE CONSULTO EL USUARIO DE MANERA EXITOSA PERO NO ESTA REGISTRADO EN NINGUN EVENTO";
        }
        ResponseModel<UserDto> responseModel = new ResponseModel<>(responseUser,
                message, HttpStatus.OK.value());
        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

}
