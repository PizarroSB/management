package com.event.management.application.services.impl;

import com.event.management.application.exceptions.BussinesException;
import com.event.management.application.mappers.EventMapper;
import com.event.management.application.models.dto.EventDto;
import com.event.management.application.models.request.RequestEvent;
import com.event.management.application.services.usecases.IEventService;
import com.event.management.infrastructure.persistence.entities.EventEntity;
import com.event.management.infrastructure.persistence.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDto> getAllEvents() {
        List<EventEntity> listEvents = eventRepository.findAll();
        if(listEvents.isEmpty()){
            throw new BussinesException("No hay eventos Registrados", HttpStatus.OK);
        }
        return listEvents.stream().map(EventMapper.MAPPER::toDto).toList();
    }


    @Override
    public EventDto saveEvent(RequestEvent event) {
        EventEntity eventEntity = EventMapper.MAPPER.toEntity(event.getEvent());
        try {
            EventEntity eventResponse = eventRepository.save(eventEntity);
            return EventMapper.MAPPER.toDto(eventResponse);
        }catch (BussinesException e){
            throw new BussinesException(e.getMessage(),e.getStatus());
        }
    }


    @Override
    public EventDto getEventById(String id) {
        EventEntity eventEntity = eventRepository.findById(id).orElseThrow(()-> new BussinesException(
                "NO SE ENCONTRO EL EVENTO", HttpStatus.NO_CONTENT));
        return EventMapper.MAPPER.toDto(eventEntity);
    }

    @Override
    public EventDto updateEvent(String id,RequestEvent event) {
        EventDto eventDto = getEventById(id);

        EventEntity eventEntity = EventMapper.MAPPER.toEntity(event.getEvent());
        try {
            EventEntity eventResponse = eventRepository.save(eventEntity);
            return EventMapper.MAPPER.toDto(eventResponse);
        }catch (BussinesException e){
            throw new BussinesException(e.getMessage(),e.getStatus());
        }
    }

    @Override
    public String deleteEvent(String event) {
        try {
            eventRepository.deleteById(event);
        }catch (BussinesException ex){
            throw new BussinesException("Ocurrio un error al eliminar el evento",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "EL EVENTO HA SIDO ELIMINADO CORRECTAMENTE";
    }
}
