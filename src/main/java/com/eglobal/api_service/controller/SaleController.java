package com.eglobal.api_service.controller;

import com.eglobal.api_service.dto.CancelRequest;
import com.eglobal.api_service.dto.SaleDto;
import com.eglobal.api_service.dto.SaleRequest;
import com.eglobal.api_service.dto.SaleResponse;
import com.eglobal.api_service.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/save")
    SaleResponse sendSale(@RequestBody SaleRequest request){
        return saleService.sendSale(request);
    }

    @GetMapping("/get")
    Page<SaleDto> get(@RequestParam(required = false) Integer page,
                      @RequestParam(required = false) Integer size,
                      @RequestParam(required = false) String sort,
                      @RequestParam(required = false) String direction){
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return saleService.get(PageRequest.of(page, size,  Sort.by(sortDirection, sort)));
    }

    @PatchMapping("/sale/{id}")
    ResponseEntity<Void> cancel(@PathVariable Long id, @RequestBody CancelRequest request){
        saleService.cancel(id, request);
        return ResponseEntity.ok().build();
    }

}
