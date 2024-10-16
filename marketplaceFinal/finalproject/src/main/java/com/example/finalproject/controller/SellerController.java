package com.example.finalproject.controller;

import com.example.finalproject.dto.UpdateExistingSellerRequest;
import com.example.finalproject.service.SellerService;
import com.example.finalproject.service.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.dto.BaseResponse;
import com.example.finalproject.dto.CreateNewSellerRequest;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sellers")
@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private SystemService systemService;
    @Qualifier("SellerServiceImpl")
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public BaseResponse<SellerDto> createNewSeller(@RequestBody @Valid CreateNewSellerRequest createNewSellerRequest){
        final SellerDto sellerDto = SellerDto.builder()
                .name(createNewSellerRequest.getName())
                .address(createNewSellerRequest.getAddress())
                .phone(createNewSellerRequest.getPhone())
                .email(createNewSellerRequest.getEmail()).build();

        return new BaseResponse<>(sellerService.createNewSeller(sellerDto));
    }

    @Operation(summary = "Get users by parameters")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping()
    public BaseResponse<List<SellerDto>> searchSellers(@RequestParam(name = "search", required = false) String search,
                                                   @RequestParam(value = "size", required = false, defaultValue = "100") Integer size,
                                                   @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "sort", required = false) String sort)
    {
        return new BaseResponse<>(sellerService.searchSellers(search,page,size,sort));

    }

    @GetMapping("/{sellerId}")
    public  BaseResponse<SellerDto> getSellerDetail(@PathVariable("sellerId") String sellerId){
        return new BaseResponse<>(sellerService.findSellerById(sellerId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{sellerId}")
    public BaseResponse<SellerDto> updateExistingSeller(@PathVariable("sellerId") String sellerId, @RequestBody @Valid UpdateExistingSellerRequest updateExistingSellerRequest){

        SellerDto sellerDto = SellerDto.builder()
                .name(updateExistingSellerRequest.getName())
                .address(updateExistingSellerRequest.getAddress())
                .phone(updateExistingSellerRequest.getPhone())
                .email(updateExistingSellerRequest.getEmail()).build();
        SellerDto sellerUpdated = sellerService.updateExistingSeller(sellerId, sellerDto);


        return new BaseResponse<>(sellerUpdated);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @DeleteMapping("/{sellerId}")
    public void deleteSeller(@PathVariable("sellerId") String sellerId){
        systemService.deleteSeller(sellerId);
        }




}
