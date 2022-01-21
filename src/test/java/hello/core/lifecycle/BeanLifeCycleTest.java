package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // 직접 작성한 함수 사용기존 - @Bean
        // 외부 라이브러리 초기화시 사용 가능
        // 기존은 사용 불가
        // destroyMethod 의 초기값 -> (inferred) ==> close, shutdown 이름의 메소드가 있으면 찾아서 호출
        // 자주 사용하지 않음
        /* @Bean(initMethod = "init", destroyMethod = "close") */
        // @PostConstruct, @PreDestroy 사용 - 스프링 권장 ==> 스프링 x 자바 o ==> 외부 라이브러리 적용 불가
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello.spring.dev");
            return networkClient;
        }
    }
}
