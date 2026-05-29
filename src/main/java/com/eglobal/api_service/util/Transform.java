package com.eglobal.api_service.util;

import com.eglobal.api_service.dto.SaleDto;
import com.eglobal.api_service.dto.SaleRequest;
import com.eglobal.api_service.dto.SaleResponse;
import com.eglobal.api_service.entity.SaleEntity;

import java.util.function.Function;

public class Transform {
    private Transform(){}

    public static final Function<SaleRequest,SaleEntity> toEntity = request ->
            SaleEntity.builder()
                    .operacion(request.getOperacion())
                    .importe(request.getImporte())
                    .cliente(request.getCliente())
                    .secreto(request.getSecreto())
                    .build();

    public static final Function<SaleEntity,SaleResponse> toResponse = entity ->
            SaleResponse.builder()
                    .id(entity.getId())
                    .estatus(entity.getEstatus())
                    .referencia(entity.getReferencia())
                    .operacion(entity.getOperacion())
                    .build();
    public static final Function<SaleEntity, SaleDto> toSaleDto = entity ->
            SaleDto.builder()
                    .id(entity.getId())
                    .cliente(entity.getCliente())
                    .estatus(entity.getEstatus())
                    .referencia(entity.getReferencia())
                    .operacion(entity.getOperacion())
                    .build();


}

