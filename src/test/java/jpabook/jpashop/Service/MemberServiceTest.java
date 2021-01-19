package jpabook.jpashop.Service;

import jpabook.jpashop.domain.member.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("lee");

        // when
        Long joinId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.getOne(joinId));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생");
    }

    @Test
    public void all_조회() throws Exception {
        // given
        List<Member> members = new ArrayList<>();

        Member member1 = new Member();
        member1.setName("kim1");
        members.add(member1);
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("kim2");
        members.add(member2);
        memberService.join(member2);

        // when
        List<Member> findMembers = memberService.findMembers();

        // then
        assertEquals(members.size(), findMembers.size());
        for(int i = 0; i < members.size(); i++){
            assertEquals(members.get(i), findMembers.get(i));
        }
    }
}