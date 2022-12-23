package lby.project.jpaboard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lby.project.jpaboard.domain.Address;
import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.domain.Order;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.repository.MemberRepository;
import lby.project.jpaboard.repository.OrderRepository;
import lby.project.jpaboard.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static lby.project.jpaboard.domain.OrderStatus.ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문한 상품 조회")
    void orders() throws Exception {
        final Product item = new Product("사과", 10, 5000);
        em.persist(item);

        final Address address = new Address("서울시", "서부샛길");
        final Member member = new Member("member1", address);
        em.persist(member);

        em.flush();
        em.clear();

        final Product findProduct = productRepository.findById(item.getId()).get();
        final Member findMember = memberRepository.findById(member.getMemberId()).get();
        final Long orderId = orderService.regOrder(findProduct.getId(), findMember.getMemberId(), 10);
        final Order order = orderRepository.findOrder(orderId);

        assertThat(orderId).isNotNull();
        assertEquals(1, order.getId());
        assertEquals("member1", order.getMember().getUsername());
        assertEquals(ORDER, order.getStatus());

    }

}