package com.event.management.application.models.request;


import com.event.management.application.models.dto.EventDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEvent {
    private EventDto event;
}
