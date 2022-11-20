package lby.project.jpaboard.service.impl;

import lby.project.jpaboard.domain.Orders;
import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.repository.OrderRepository;
import lby.project.jpaboard.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<OrderDto> getOrderList() {
        List<Orders> orderList = orderRepository.findAll();
        return orderList.stream().map(orders -> modelMapper.map(orders, OrderDto.class)).collect(Collectors.toList());
    }

    @Override
    public void regOrder(OrderDto orderDto) {
        final Orders order = Orders.builder()
                .orderNum(orderDto.getOrderNum())
                .orderDate(orderDto.getOrderDate())
                .orderUser(orderDto.getOrdersUser())
                .build();

        orderRepository.save(order);
    }


}
