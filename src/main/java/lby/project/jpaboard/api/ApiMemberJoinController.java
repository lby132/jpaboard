package lby.project.jpaboard.api;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiMemberJoinController {

    @Autowired
    private final MemberService memberService;

   // @PostMapping("/members/join")
    public ResponseEntity memberJoin(Member member) {
        memberService.join(member);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
