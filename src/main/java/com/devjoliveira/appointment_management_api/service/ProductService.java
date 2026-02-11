package com.devjoliveira.appointment_management_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjoliveira.appointment_management_api.domain.Product;
import com.devjoliveira.appointment_management_api.dto.ProductDTO;
import com.devjoliveira.appointment_management_api.dto.ProductMinDTO;
import com.devjoliveira.appointment_management_api.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional(readOnly = true)
  public List<ProductDTO> findAll() {
    return productRepository.findAll().stream().map(ProductDTO::new).toList();
  }

  @Transactional
  public ProductDTO save(ProductMinDTO request) {

    productRepository.findByName(request.name()).ifPresent(product -> {
      throw new RuntimeException("Product with name " + product.getName() + " already exists");
    });

    Product entity = new Product();
    entity.setName(request.name());
    entity.setDuration(request.duration());
    entity.setPrice(request.price());

    var fromDB = productRepository.save(entity);
    return new ProductDTO(fromDB);
  }

  @Transactional
  public ProductDTO change(UUID id, ProductMinDTO request) {

    var fromDB = productRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Product with id " + id + " not found"));

    if (!fromDB.getName().equals(request.name())) {
      productRepository.findByName(request.name()).ifPresent(product -> {
        throw new RuntimeException("Product with name " + product.getName() + " already exists");
      });
    }

    fromDB.setName(request.name());
    fromDB.setDuration(request.duration());
    fromDB.setPrice(request.price());

    var updateProduct = productRepository.save(fromDB);
    return new ProductDTO(updateProduct);
  }

  @Transactional
  public void delete(UUID id) {
    var fromDB = productRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Product with id " + id + " not found"));

    productRepository.delete(fromDB);
  }

}
