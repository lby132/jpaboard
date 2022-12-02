package lby.project.jpaboard.service;

import lby.project.jpaboard.domain.Member;
import lby.project.jpaboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Long join(Member member) {
        validation(member);
        memberRepository.save(member);
        return member.getMemberId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    private void validation(Member member) {
        final List<Member> findMember = memberRepository.findByUsername(member.getUsername());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("존재하는 회원입니다.");
        }
    }
}
