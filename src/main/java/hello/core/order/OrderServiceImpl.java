package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/* 생성자 주입 만들어줌 */
/*
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /* 생성자주입 -> 불변, 필수! final */
    /* 생성자주입 사용 이유 -> (final 사용) 에러코드를 바로 확인이 가능 (컴파일 에러) */
    /* 프레임워크에 의존하지 않고, 순수한 자바 언어 */
    /* 생성자 주입 사용하되, 옵션이 필요할 시 수정자 주입 사용 */
    //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /* interface 에만 의존하도록 변경 */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

/*
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

/*
    // final 제외한 후 -> setter 주입 (수정자 주입)
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
*/
/*
    // 필드주입 -> 잘 사용하지 않음
    // 테스트코드 등에서만 사용
    @Autowired private MemberRepository memberRepository;
*/
/*
    // 일반 메서드 주입
    // 일반적으로 사용하지 않음
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){...}
*/

    /* 생성자 하나일 경우 Autowired 생략 가능 */
    /* @Autowired */
/*
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
