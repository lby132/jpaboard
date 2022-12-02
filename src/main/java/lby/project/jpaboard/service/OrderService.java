package lby.project.jpaboard.service;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.domain.Order;
import lby.project.jpaboard.domain.OrderItem;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.repository.MemberRepository;
import lby.project.jpaboard.repository.OrderRepository;
import lby.project.jpaboard.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<OrderDto> getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(orders -> modelMapper.map(orders, OrderDto.class)).collect(Collectors.toList());
    }

    public OrderDto getOrderItem(Long orderNum) {
        final Order orders = orderRepository.findById(orderNum).orElseThrow(() -> new IllegalArgumentException("해당 상품의 주문이 없습니다."));
        return modelMapper.map(orders, OrderDto.class);
    }

    @Transactional
    public Long regOrder(Long productId, Long memberId, int count) {
        final Product product = productRepository.findById(productId).orElseThrow();
        final Member member = memberRepository.findById(memberId).orElseThrow();

        final OrderItem orderItem = OrderItem.createOrderItem(product, product.getPrice(), count);

        final Order order = Order.createOrder(member, orderItem);

        orderRepository.save(order);

        return order.getId();

    }


}
