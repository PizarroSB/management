package com.event.management.infrastructure.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel <T>{
   private T data;
   private String message;
   private int status;
}
