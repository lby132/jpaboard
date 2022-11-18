package lby.project.jpaboard.service;

import lby.project.jpaboard.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductList();

    void saveProduct(ProductDto productDto);

    ProductDto getProductOne(Long prodId);
}
