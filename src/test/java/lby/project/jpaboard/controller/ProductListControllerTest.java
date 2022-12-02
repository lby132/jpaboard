package lby.project.jpaboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lby.project.jpaboard.domain.Order;
import lby.project.jpaboard.domain.OrderStatus;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static lby.project.jpaboard.domain.OrderStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class ProductListControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 저장, 목록 조회")
    void saveFindAll() throws Exception {
        // given
        final LocalDateTime now = LocalDateTime.now();
        final Order orders = new Order(now, CANCEL);
        final Product request = Product.builder()
                .productName("키위")
                .productCnt(2)
                .price(8000)
                .build();

        final String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/product/saveProduct")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, productRepository.count());

        final Product product = productRepository.findAll().get(0);
        assertEquals("키위", product.getProductName());
        assertEquals(2, product.getProductCnt());
    }

    @Test
    @DisplayName("상품 한개 조회")
    void productOne() throws Exception {
        final Product item = Product.builder()
                .productName("수박")
                .productCnt(3)
                .price(4000)
                .build();
        productRepository.save(item);

        final String json = objectMapper.writeValueAsString(item);

        mockMvc.perform(get("/product/getProductOne/{productId}", item.getId())
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(item.getId()))
                .andExpect(jsonPath("$.productName").value(item.getProductName()))
                .andExpect(jsonPath("$.productCnt").value(item.getProductCnt()))
                .andExpect(jsonPath("$.price").value(item.getPrice()))
                .andDo(print());
    }

}