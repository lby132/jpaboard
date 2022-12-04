package lby.project.jpaboard.api;

import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.dto.OrderInfo;
import lby.project.jpaboard.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ListUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping("/order")
public class ApiOrderStatusController {

    @Autowired
    private final OrderService orderService;

    //@GetMapping("/getOrderList")
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

    //@GetMapping("/getOrderList/{orderNum}")
    public Map<String, Object> getOrders(@PathVariable("orderNum") Long orderNum) {
        final OrderDto orderItem = orderService.getOrderItem(orderNum);

        Map<String, Object> result = new HashMap<>();
        if (orderItem == null) {
            result.put("result", "해당 상품의 주문이 없습니다.");
        } else {
            result.put("result", "OK");
            result.put("orderItem", orderItem);
        }

        return result;
    }

    //@PostMapping("/regOrder")
    public void regOrder(@RequestBody OrderInfo orderInfo) {
        orderService.regOrder(orderInfo.getProductId(), orderInfo.getMemberId(), orderInfo.getCount());
    }
}
