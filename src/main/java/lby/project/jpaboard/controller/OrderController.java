package lby.project.jpaboard.controller;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.domain.Order;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.OrderDto;
import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.form.OrderForm;
import lby.project.jpaboard.service.MemberService;
import lby.project.jpaboard.service.OrderService;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final ProductService productService;

    @GetMapping("/orderReg")
    public String orderForm(Model model) {
        final List<Member> members = memberService.findMembers();
        final List<Product> items = productService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderReg";
    }

    @PostMapping("/orderReg")
    public String orderReg(@RequestParam("memberId") Long memberId,
                           @RequestParam("itemId") Long itemId,
                           @RequestParam("count") int count) {

        orderService.regOrder(memberId, itemId, count);

        return "order/OrderViewYn";
    }

    @GetMapping("/orderList")
    public String orderList(Model model) {
        final List<Order> orderList = orderService.findAll();
        model.addAttribute("orderList", orderList);
        return "order/orderList";
    }

    @PostMapping("/{id}/cancel")
    public String orderCancel(@PathVariable("id") Long id) {
        orderService.cancelOrder(id);
        return "redirect:/product/productList";
    }
}
