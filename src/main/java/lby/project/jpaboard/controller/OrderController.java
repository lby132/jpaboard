package lby.project.jpaboard.controller;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.domain.Product;
import lby.project.jpaboard.dto.ProductDto;
import lby.project.jpaboard.form.OrderForm;
import lby.project.jpaboard.service.MemberService;
import lby.project.jpaboard.service.OrderService;
import lby.project.jpaboard.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


}
