package com.f5.travel.api.models.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// Cuando son clases abstractas se usa el SuperBuilder
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse{

  private String status;
  private Integer code;


}