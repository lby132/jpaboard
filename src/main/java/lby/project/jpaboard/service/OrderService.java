package lby.project.jpaboard.service;

import lby.project.jpaboard.domain.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> getOrderList();
}
