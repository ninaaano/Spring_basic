package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
 * 생성한 객체 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해준다 -> 생성자 주입
 * 커맨트 + E = 히스토리
 */
public class AppConfig {
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); // 생성자 주입, 의존성 주입
    }


    // command + option + M -> 리팩토링
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
