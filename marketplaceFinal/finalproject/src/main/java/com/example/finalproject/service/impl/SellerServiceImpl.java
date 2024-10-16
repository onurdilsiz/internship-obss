package com.example.finalproject.service.impl;

import com.example.finalproject.dto.CreateNewSellerRequest;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.exception.ProductNotFoundException;
import com.example.finalproject.exception.SellerNotFoundException;
import com.example.finalproject.mapper.SellerMapper;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Seller;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.SellerRepository;
import com.example.finalproject.service.SellerService;
import com.example.finalproject.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;




    @Override
    public SellerDto createNewSeller(SellerDto sellerDto) {
        Objects.requireNonNull(sellerDto,"sellerDto must not be null");
        Objects.requireNonNull(sellerDto.getName(),"sellerDto name must not be null");
        Objects.requireNonNull(sellerDto.getPhone(),"sellerDto phone must not be null");
        Objects.requireNonNull(sellerDto.getEmail(),"sellerDto email must not be null");
        Objects.requireNonNull(sellerDto.getAddress(),"sellerDto address must not be null");


        sellerDto.setId(UUID.randomUUID().toString());
        Seller seller = sellerMapper.toModel(sellerDto);

        return sellerMapper.toDto(sellerRepository.save(seller));

    }


    @Override
    public List<SellerDto> searchSellers(String search, Integer page, Integer size, String sort) {
        String sortField = sort;
        if (StringUtils.isBlank(sort)) {
            sortField = "createdAt";
        }

        final PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortField)));
        List<Seller> sellers = null;
        if (StringUtils.isBlank(search)) {
            sellers = sellerRepository.findAllSellers(pageRequest);
        } else {
            sellers = sellerRepository.findSellersByNameContainingIgnoreCase(search,  pageRequest);
        }
        return sellers.stream().map(sellerMapper::toDto).toList();
    }

    public SellerDto findSellerById(@PathVariable("sellerId") String sellerId) {
        Objects.requireNonNull(sellerId, "sellerId cannot be null");
        return sellerMapper.toDto(sellerRepository.findSellerById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId)));
    }
    public Seller findSellerObjectById(@PathVariable("sellerId") String sellerId) {
        Objects.requireNonNull(sellerId, "sellerId cannot be null");
        return sellerRepository.findSellerById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId));
    }

    @Override
    public SellerDto updateExistingSeller(String sellerId, SellerDto sellerDto) {
        final Seller seller = sellerRepository.findById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId));

        if (StringUtils.isNotBlank(sellerDto.getName())) {
            seller.setName(sellerDto.getName());
        }
        if (StringUtils.isNotBlank(sellerDto.getEmail())) {
            seller.setEmail(sellerDto.getEmail());
        }
        if (StringUtils.isNotBlank(sellerDto.getAddress())) {
            seller.setAddress(sellerDto.getAddress());
        }
        if (StringUtils.isNotBlank(sellerDto.getPhone())) {
            seller.setPhone(sellerDto.getPhone());
        }

        return sellerMapper.toDto(sellerRepository.save(seller));
    }

    @Override
    public List<UUID> getUsersBlacklisted(String sellerId) {
        Objects.requireNonNull(sellerId, "product can not be null");
        final Seller seller = sellerRepository.findById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId));
        List<UUID> userIds = seller.getUsersBlacklisted().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        return userIds;
//        userService.deleteFavoriteProducts(userIds , productId);
//        productRepository.delete(product);
    }
    @Override
    public void deleteSeller(String sellerId) {
        Objects.requireNonNull(sellerId, "seller can not be null");
        final Seller seller = sellerRepository.findById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId));
        sellerRepository.delete(seller);
    }

    public List<UUID> getProductsBySeller(String sellerId) {
        Objects.requireNonNull(sellerId, "product can not be null");
        final Seller seller = sellerRepository.findById(UUID.fromString(sellerId)).orElseThrow(() -> new SellerNotFoundException(sellerId));
        List<UUID> productIds = seller.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        return productIds;
    }


    @Override
    public List<SellerDto> generateSampleSellers(int targetSellerSize) {
        List<SellerDto> sellers = new ArrayList<>();
        for (int i = 0; i < targetSellerSize; i++) {
            SellerDto sellerDto = SellerDto.builder()
                    .name("Seller" + i)
                    .email("seller" + i + "@example.com")
                    .phone("1234567890")
                    .address("Address" + i)
                    .build();
            Seller seller = sellerMapper.toModel(sellerDto);
            SellerDto sellerDto1 = sellerMapper.toDto(sellerRepository.save(seller));
            sellers.add(sellerDto1);
        }

        return sellers;

    }



}
