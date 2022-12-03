package lby.project.jpaboard.controller;

import lby.project.jpaboard.domain.Address;
import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.form.MemberForm;
import lby.project.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @GetMapping("/")
    public String joinForm(Model model) {
        model.addAttribute("members", new MemberForm());
        return "login/login";
    }

    @PostMapping("/members/join")
    public String joinMember(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/";
        }
        final Address address = new Address(form.getCity(), form.getStreet());
        final Member member = Member.builder()
                .userName(form.getName())
                .address(address)
                .build();

        memberService.join(member);
        return "product/proChooseView";
    }
}
