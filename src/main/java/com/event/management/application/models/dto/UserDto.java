package com.event.management.application.models.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String name;
    private List<EventDto> events = new ArrayList<>();
}
