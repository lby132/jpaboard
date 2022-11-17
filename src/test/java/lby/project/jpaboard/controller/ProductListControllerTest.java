package lby.project.jpaboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.entity.Product;
import lby.project.jpaboard.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
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
    @DisplayName("상품 목록 조회")
    void findAll() throws Exception {
        // given
        final ProductDto request = ProductDto.builder()
                .productId(1L)
                .productName("망고")
                .productCnt(2)
                .build();

        final String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/saveProduct")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, productRepository.count());

        final Product product = productRepository.findAll().get(0);
        assertEquals("망고", product.getProductName());
        assertEquals(2, product.getProductCnt());
    }

    @Test
    @DisplayName("상품 한개 조회")
    void productOne() throws Exception {
        final Product item = Product.builder()
                .productName("수박")
                .productCnt(3)
                .build();
        productRepository.save(item);

        final String json = objectMapper.writeValueAsString(item);

        mockMvc.perform(get("/getProductOne/{productId}", item.getProductId())
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

}