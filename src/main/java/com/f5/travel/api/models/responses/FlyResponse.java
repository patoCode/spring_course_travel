package com.f5.travel.api.models.responses;

import com.f5.travel.util.AeroLine;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse implements Serializable {

  private Integer id;
  private Double originLat;
  private Double originLng;
  private Double destinyLat;
  private Double destinyLng;
  private String originName;
  private String destinyName;
  private BigDecimal price;
  private AeroLine aeroLine;

}