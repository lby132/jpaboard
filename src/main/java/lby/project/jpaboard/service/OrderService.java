package lby.project.jpaboard.service;

import lby.project.jpaboard.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getOrderList();

    OrderDto getOrderItem(Long orderNum);

    void regOrder(OrderDto orderDto);
}
