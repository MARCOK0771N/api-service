package com.eglobal.api_service.service;

import com.eglobal.api_service.dto.CancelRequest;
import com.eglobal.api_service.dto.SaleDto;
import com.eglobal.api_service.dto.SaleRequest;
import com.eglobal.api_service.dto.SaleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {

    SaleResponse sendSale(SaleRequest request);

    Page<SaleDto> get(Pageable pageable);

    void cancel(Long id, CancelRequest request);

}
