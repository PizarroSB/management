package com.event.management.application.services.usecases;

import com.event.management.application.models.dto.UserDto;

public interface IUserService {
    String regiserUserInEvent(String idUser, String idEvent);
    UserDto getEventsByUser(String userId);
}
