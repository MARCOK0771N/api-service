package com.eglobal.api_service.service;

import com.eglobal.api_service.dto.CancelRequest;
import com.eglobal.api_service.dto.SaleDto;
import com.eglobal.api_service.dto.SaleRequest;
import com.eglobal.api_service.dto.SaleResponse;
import com.eglobal.api_service.entity.SaleEntity;
import com.eglobal.api_service.exception.NotFoundException;
import com.eglobal.api_service.exception.UpdateFailedException;
import com.eglobal.api_service.repository.SaleRepository;
import com.eglobal.api_service.util.ReferenceGenerator;
import com.eglobal.api_service.util.Transform;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Log4j2
@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;


    @Transactional
    @Override
    public SaleResponse sendSale(SaleRequest request) {
        SaleEntity saleEntity = Transform.toEntity.apply(request);
        saleEntity.setEstatus("Aprobada");
        saleEntity.setReferencia(ReferenceGenerator.generarReferencia());
        saleRepository.saveAndFlush(saleEntity);
        log.info("guardado saleEntity: " + saleEntity);
        return Transform.toResponse.apply(saleEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SaleDto> get(Pageable pageable) {
        return saleRepository.findAll(pageable).map(Transform.toSaleDto);
    }

    @Transactional
    @Override
    public void cancel(Long id, CancelRequest request) {

        SaleEntity saleEntity = saleRepository.findByIdAndReferencia(id, request.getReferencia()).orElseThrow(()->new NotFoundException("no se encontro registro con los datos proporcionados"));
        log.info("update saleEntity...: " + saleEntity);

        int updated = saleRepository.actualizarEstatus(id, request.getReferencia(), request.getEstatus());

        if (updated == 0) {
            throw new UpdateFailedException("No se encontró registro con los datos proporcionados");
        }
    }
}
