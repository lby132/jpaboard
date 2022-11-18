package lby.project.jpaboard.controller;

import lby.project.jpaboard.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderStatusController {

    @Autowired
    private final OrderService orderService;

    @GetMapping("/getOrderList")
    public Map<String, Object> getOrders() {
        return null;
    }
}
