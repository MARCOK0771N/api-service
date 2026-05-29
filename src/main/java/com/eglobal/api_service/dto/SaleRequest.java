package com.eglobal.api_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SaleRequest {

    private String operacion;

    private BigDecimal importe;

    private String cliente;

    private String secreto;
}
