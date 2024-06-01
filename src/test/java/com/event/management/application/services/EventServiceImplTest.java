package com.event.management.application.services;

import com.event.management.application.exceptions.BussinesException;
import com.event.management.application.models.dto.EventDto;
import com.event.management.application.models.request.RequestEvent;
import com.event.management.application.services.impl.EventServiceImpl;
import com.event.management.infrastructure.persistence.entities.EventEntity;
import com.event.management.infrastructure.persistence.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {
    @InjectMocks
    private EventServiceImpl eventService;
    @Mock
    private EventRepository eventRepository;

    List<EventEntity> eventEntityList = new ArrayList<>();
    EventEntity eventEntity = new EventEntity();
    RequestEvent requestEvent = new RequestEvent();
    EventDto eventDto = new EventDto();

    @BeforeEach
    public void setUp() {
        eventEntity = new EventEntity("1","prueba","2024-01-02","mi casa",new ArrayList<>());
        eventEntityList.add(eventEntity);
        eventDto = new EventDto("1","prueba","2024-01-02","mi casa");
        requestEvent = new RequestEvent(eventDto);
    }

    @Test
    public void getAllEvents_NO_EVENTS() {
        eventEntityList = new ArrayList<>();
        Mockito.when(eventRepository.findAll()).thenReturn(eventEntityList);
        Assertions.assertThrows(BussinesException.class,()->{
            eventService.getAllEvents();
        });
    }

    @Test
    public void getAllEvents_OK() {
        Mockito.when(eventRepository.findAll()).thenReturn(eventEntityList);
        List<EventDto> eventDtos = eventService.getAllEvents();
        Assertions.assertFalse(eventDtos.isEmpty());
    }

    @Test
    public void saveEvent_CREATED() {
        Mockito.when(eventRepository.save(Mockito.any())).thenReturn(eventEntity);
        EventDto eventDtoResponse = eventService.saveEvent(requestEvent);
        Assertions.assertNotNull(eventDtoResponse);
    }

    @Test
    public void saveEvent_NO_CREATED() {
        Mockito.when(eventRepository.save(Mockito.any())).thenThrow(BussinesException.class);
        Assertions.assertThrows(BussinesException.class,()->{
            eventService.saveEvent(requestEvent);
        });

    }

    @Test
    public void getEventByID_OK() {
        String eventId = "98d2aebc-28ed-4953-9ecb-75b2de888cbe";
        Mockito.when(eventRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(eventEntity));
        EventDto eventDtos = eventService.getEventById(eventId);
        Assertions.assertNotNull(eventDtos);
    }

    @Test
    public void deleteEventByID_OK() {
        String eventId = "98d2aebc-28ed-4953-9ecb-75b2de888cbe";
        String messageExpected ="EL EVENTO HA SIDO ELIMINADO CORRECTAMENTE";

        String messageResponse = eventService.deleteEvent(eventId);
        Assertions.assertEquals(messageExpected,messageResponse);
    }
}
