package lby.project.jpaboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lby.project.jpaboard.domain.Orders;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.repository.OrderRepository;
import lby.project.jpaboard.repository.ProductRepository;
import lby.project.jpaboard.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderStatusControllerTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문한 상품 리스트")
    void orders() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final Product item = Product.builder()
                .productName("자두")
                .productCnt(2)
                .price(2000)
                .build();
        productRepository.save(item);

        final OrderDto orderSave = OrderDto.builder()
                .orderNum(item.getProductId())
                .ordersUser("member1")
                .orderDate(now)
                .build();
        orderService.regOrder(orderSave);

        final String json = objectMapper.writeValueAsString(orderSave);

        mockMvc.perform(get("/order/getOrderList")
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        final List<OrderDto> orderList = orderService.getOrderList();
        assertThat(orderList.size()).isEqualTo(1);
    }
}