package com.example.finalproject.service.impl;

import com.example.finalproject.dto.ProductDto;
import com.example.finalproject.dto.SellerDto;
import com.example.finalproject.exception.ProductNotFoundException;
import com.example.finalproject.mapper.ProductMapper;
import com.example.finalproject.mapper.SellerMapper;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Seller;
import com.example.finalproject.model.User;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.SellerRepository;
import com.example.finalproject.service.ProductService;
import com.example.finalproject.service.SellerService;
import com.example.finalproject.service.UserService;
import com.example.finalproject.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SellerService sellerService;
    private final SellerMapper sellerMapper;
    private final SellerRepository   sellerRepository;


    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        Objects.requireNonNull(productDto,"productDto must not be null");
        Objects.requireNonNull(productDto.getName(),"productDto name must not be null");
        Objects.requireNonNull(productDto.getCategory(),"productDto category must not be null");
        Objects.requireNonNull(productDto.getDescription(),"productDto description must not be null");
        Objects.requireNonNull(productDto.getPrice(),"productDto price must not be null");
        Objects.requireNonNull(productDto.getQuantity(),"productDto quantity must not be null");
        Objects.requireNonNull(productDto.getSellerId(),"productDto sellerId must not be null");

        productDto.setId(UUID.randomUUID().toString());

        Product product = productMapper.toModel(productDto);
        Seller seller = sellerService.findSellerObjectById(productDto.getSellerId());
        product.setSeller(seller);
        Product savedProduct = productRepository.save(product);
        seller.addProduct(savedProduct);

        return productMapper.toDto(productRepository.save(savedProduct));
    }

    @Override
    public List<ProductDto> searchProducts(String search, Integer page, Integer size, String sort) {
        String sortField = sort;
        if (StringUtils.isBlank(sort)) {
            sortField = "createdAt";
        }

        final PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortField)));
        List<Product> products = null;
        if (StringUtils.isBlank(search)) {
            products = productRepository.findAllProducts(pageRequest);
        } else {
            products = productRepository.findByNameContaining(search, pageRequest);
        }
        return products.stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductDto updateExistingProduct(String productId, ProductDto productDto) {
        Objects.requireNonNull(productId, "product can not be null");



        final Product product = productRepository.findById(UUID.fromString(productId)).orElseThrow(() -> new ProductNotFoundException(productId));

        if (StringUtils.isNotBlank(productDto.getName())) {
            product.setName(productDto.getName());
        }
        if (StringUtils.isNotBlank(productDto.getDescription())) {
            product.setDescription(productDto.getDescription());
        }
        if (StringUtils.isNotBlank(productDto.getCategory())) {
            product.setCategory(productDto.getCategory());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getQuantity() != null) {
            product.setQuantity(productDto.getQuantity());
        }
        if (StringUtils.isNotBlank(productDto.getSellerId())) {
            Seller previousSeller = product.getSeller();
            previousSeller.removeProduct(product);
            Seller seller = sellerService.findSellerObjectById(productDto.getSellerId());
            product.setSeller(seller);
            seller.addProduct(product);
        }

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<UUID> getUsersWhoFavorited(String productId) {
            Objects.requireNonNull(productId, "product can not be null");
            final Product product = productRepository.findById(UUID.fromString(productId)).orElseThrow(() -> new ProductNotFoundException(productId));
        List<UUID> userIds = product.getUsersWhoFavorited().stream()
                .map(User::getId)
                .collect(Collectors.toList());

//        userService.deleteFavoriteProducts(userIds , productId);


//        productRepository.delete(product);
        return userIds;

    }

    @Override
    public void deleteProduct(String productId) {
        Objects.requireNonNull(productId, "product can not be null");
        final Product product = productRepository.findById(UUID.fromString(productId)).orElseThrow(() -> new ProductNotFoundException(productId));
        productRepository.delete(product);
    }

    @Override
    public ProductDto findProductById(String productId) {
        Objects.requireNonNull(productId,"product cannot be null");
        return productMapper.toDto(productRepository.findProductById(UUID.fromString(productId)).orElseThrow(() -> new ProductNotFoundException(productId)));
    }




    public void generateSampleProducts(int targetProductSize, List<SellerDto> sellers) {
        List<Product> products = new ArrayList<>();

        List<String> productNames = List.of("Smartphone", "Laptop", "Headphones", "Smartwatch", "Tablet", "Camera", "Monitor", "Printer", "Keyboard", "Mouse");
        List<String> categories = List.of("Electronics", "Computers", "Accessories", "Wearables", "Photography", "Office Supplies");
        List<String> descriptions = List.of(
                "High-quality product with excellent features.",
                "Latest model with advanced technology.",
                "Reliable and durable, perfect for daily use.",
                "Compact and stylish design.",
                "Top-rated by users for its performance."
        );

        Random random = new Random();

        for (int i = 0; i < targetProductSize; i++) {
            String name = productNames.get(random.nextInt(productNames.size()));
            String category = categories.get(random.nextInt(categories.size()));
            String description = descriptions.get(random.nextInt(descriptions.size()));
            double price = 50.0 + (500.0 - 50.0) * random.nextDouble(); // Random price between 50 and 500
            int quantity = random.nextInt(100) + 1; // Random quantity between 1 and 100
            int randomSellerIndex = random.nextInt(sellers.size());
            String sellerId = sellers.get(randomSellerIndex).getId();

            final Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setCategory(category);
            product.setPrice(price);
            product.setQuantity(quantity);

            Seller seller = sellerRepository.findById(UUID.fromString(sellerId))
                    .orElseGet(() -> {
                        SellerDto sellerDto = sellerService.findSellerById(sellerId);
                        Seller newSeller = sellerMapper.toModel(sellerDto);
                        return sellerRepository.save(newSeller);
                    });


            seller.addProduct(product);
            products.add(product);
        }

        productRepository.saveAll(products);
    }


}
