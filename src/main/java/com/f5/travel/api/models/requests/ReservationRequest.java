package com.f5.travel.api.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest implements Serializable {

    private Integer idClient;
    @Positive(message = "El id_hotel debe ser positivo")
    private Integer idHotel;
    @Min( value = 1, message = "Minimo se puede hospedar 1 dia")
    @Max(value = 30, message = "Maximo se puede hospedar 30 dias por reserva")
    private Integer totalDays;
    @Size(min = 10, max = 250, message = "El email debe estar entre 50 y 250 caracteres de longitud")
    @NotBlank(message = "Este campo es requerido")
    @Email
    private String email;

}