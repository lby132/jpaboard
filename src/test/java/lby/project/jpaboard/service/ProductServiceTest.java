package lby.project.jpaboard.service;

import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("상품저장,조회")
    void 저장_조회() {
        final ProductDto item = ProductDto.builder()
                .productName("망고")
                .productCnt(2)
                .build();

        productService.saveProduct(item);

        final List<Product> products = productRepository.findAll();
        assertThat(products.size()).isEqualTo(1);
        final Product product = productRepository.findAll().get(0);
        assertEquals("망고", product.getProductName());
        assertEquals(2, product.getProductCnt());
    }

    @Test
    @DisplayName("상품 하나 조회")
    void productOne() {
        final ProductDto item = ProductDto.builder()
                .productId(1L)
                .productName("수박")
                .productCnt(3)
                .build();
        productService.saveProduct(item);

        final Product product = productRepository.findById(item.getProductId())
                .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
        assertThat(product.getProductName()).isEqualTo("수박");
    }

}