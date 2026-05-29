package com.eglobal.api_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SaleResponse {

    private Long id;
    private String estatus;
    private String referencia;
    private String operacion;
}
