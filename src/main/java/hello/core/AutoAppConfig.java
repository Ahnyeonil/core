package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/* 자동으로 Bean 으로 설정 */
@ComponentScan(
        /* 제외시킬 대상 지정 */
        /* @Configuration 이 컴포넌트 스캔의 대상이 된 이유도 @Configuration 소스코드를 열어보면
            @Component 애노테이션이 붙어있기 때문이다. */
        /* 예제코드 남겨두기 위해 ( 실무에서는 잘 사용하지 않음 ) */
        /* includeFilters <-> excludeFilters */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        /* 시작위치 지정 가능 */
        /* basePackages = {"hello.core", "hello.service"} 복수 지정 가능 */
        /* default -> ComponentScan 패키지로 default */
        /* Spring boot -> SpringApplication 의 위치에 두는게 관례 */
        basePackages = "hello.core"
)
public class AutoAppConfig {
/*
    *//* 자동 / 수동 충돌시 에러 확인 *//*
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/
}
