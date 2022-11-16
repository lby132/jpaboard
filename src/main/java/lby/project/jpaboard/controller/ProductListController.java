package lby.project.jpaboard.controller;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductListController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping("/getProductList")
    public List<ProductDto> getProducts() {
        return productService.getProductList();
    }

    @PostMapping("/saveProduct")
    public void saveProduct(ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @GetMapping("/getProductOne")
    public ProductDto getProductOne(@RequestParam("productId") Long prodId) {
         return productService.getProductOne(prodId);
    }
}
