package lby.project.jpaboard.controller;

import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.ListUtils;

import java.util.HashMap;
import java.util.List;
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
        List<OrderDto> orderList = orderService.getOrderList();

        Map<String, Object> result = new HashMap<>();

        if (ListUtils.isEmpty(orderList)) {
            result.put("result", "상품을 등록해주세요.");
        } else {
            result.put("result", "OK");
            result.put("orderList", orderList);
        }

        return result;
    }
}
