package lby.project.jpaboard.service;

import lby.project.jpaboard.domain.Order;
import lby.project.jpaboard.domain.OrderStatus;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static lby.project.jpaboard.domain.OrderStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("상품저장,조회")
    void 저장_조회() {

        final Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(CANCEL)
                .build();
        em.persist(order);

        ProductDto item = ProductDto.builder()
                .productName("망고")
                .productCnt(2)
                .price(10000)
                .order(order)
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
        final Product item = Product.builder()
                .productName("수박")
                .productCnt(3)
                .price(3000)
                .build();
        productRepository.save(item);

        final ProductDto productOne = productService.getProductOne(item.getId());

        assertEquals("수박", productOne.getProductName());
    }

    @Test
    @DisplayName("상품 전체 조회")
    void ProductList() {
        //given
        final Product apple = new Product("사과", 2, 5000);
        final Product grape = new Product("포도", 1, 4000);
        final Product banana = new Product("바나나", 5, 3000);

        em.persist(apple);
        em.persist(grape);
        em.persist(banana);

        em.flush();
        em.clear();
        //when
        final List<ProductDto> productList = productService.getProductList();
        //then
        assertThat(productList.size()).isEqualTo(3);
        assertThat(productList.get(0).getProductName()).isEqualTo("사과");
    }
}