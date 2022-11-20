package lby.project.jpaboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lby.project.jpaboard.domain.Orders;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.repository.OrderRepository;
import lby.project.jpaboard.repository.ProductRepository;
import lby.project.jpaboard.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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

    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문한 상품 조회")
    void orders() throws Exception {
        final LocalDateTime now = LocalDateTime.now();
        final Orders orders = new Orders("member1", now);
        final Product item = Product.builder()
                .productName("자두")
                .productCnt(2)
                .price(2000)
                .orders(orders)
                .build();
        productRepository.save(item);

        final OrderDto orderSave = OrderDto.builder()
                .orderNum(item.getProductId())
                .ordersUser("member1")
                .orderDate(now)
                .build();
        orderService.regOrder(orderSave);

        final String json = objectMapper.writeValueAsString(orderSave);

        mockMvc.perform(get("/order/getOrderList/{orderNum}", orderSave.getOrderNum())
                .contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        final OrderDto orderItem = orderService.getOrderItem(orderSave.getOrderNum());
        assertEquals("member1", orderItem.getOrdersUser());
    }
}