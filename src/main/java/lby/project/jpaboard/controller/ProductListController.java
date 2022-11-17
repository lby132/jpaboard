package lby.project.jpaboard.controller;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductListController {

    @Autowired
    private final ProductService productService;

    @GetMapping("/getProductList")
    public List<ProductDto> getProducts() {
        return productService.getProductList();
    }

    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @GetMapping("/getProductOne/{productId}")
    public ProductDto getProductOne(@PathVariable("productId") Long prodId) {
         return productService.getProductOne(prodId);
    }
}
