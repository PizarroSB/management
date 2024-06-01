package com.event.management.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError {
    String message;
    int statusCode;
}
