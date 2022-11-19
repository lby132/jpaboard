//package lby.project.jpaboard.controller;
//
//import lby.project.jpaboard.repository.OrderRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class OrderStatusControllerTest {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("주문한 상품 리스트")
//    void orders() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/order/getOrderList"))
//    }
//}