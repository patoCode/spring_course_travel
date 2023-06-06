package com.f5.travel.infraestructure.abstracts;

import com.f5.travel.api.models.responses.FlyResponse;
import java.util.Set;

public interface IFly extends CatalogService<FlyResponse>{

  Set<FlyResponse> readOriginDestiny(String origen, String destiny);

}