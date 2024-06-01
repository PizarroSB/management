package com.event.management.application.services.usecases;

import com.event.management.application.models.dto.EventDto;
import com.event.management.application.models.request.RequestEvent;

import java.util.List;

public interface IEventService {
    List<EventDto> getAllEvents();
    EventDto getEventById(String id);
    EventDto saveEvent(RequestEvent event);
    EventDto updateEvent(String id,RequestEvent event);
    String deleteEvent(String event);
}
