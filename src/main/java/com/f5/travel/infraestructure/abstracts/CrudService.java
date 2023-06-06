package com.f5.travel.infraestructure.abstracts;

public interface CrudService<REQ, RES, ID>{

  RES create(REQ request);
  RES read(ID id);
  RES update(REQ request,ID id);
  void delete(ID id);

}