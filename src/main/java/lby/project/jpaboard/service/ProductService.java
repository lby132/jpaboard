package lby.project.jpaboard.service;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductList();

    void insertProduct(Product productDto);

    ProductDto getProductOne(Long prodId);
}
