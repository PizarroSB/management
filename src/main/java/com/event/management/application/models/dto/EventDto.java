package com.event.management.application.models.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private String id;
    private String name;
    private String date;
    private String location;
}
