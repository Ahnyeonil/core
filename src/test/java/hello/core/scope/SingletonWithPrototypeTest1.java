package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        // singleton은 생성시점에 한번 주입되기 때문에 2로 리턴
        // prototype은 사용할때마다 새로 생성
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    /*@RequiredArgsConstructor*/
    static class ClientBean{

        // Bean 생성시점에 주입
        /*private final PrototypeBean prototypeBean;*/

        // ObjectProvider -> prototypeBean 찾아주는 기능
        // ObjectProvider( 편의 기능 다수 보유 ) == ObjectFactory
        // ObjectProvider 스프링의 DL(Dependency Lookup)기능 제공
        // ObjectProvider -> 스프링에 의존
        /*@Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;*/

        // 자바 표준 -> 라이브러리 필수
        /* implementation 'javax.inject:javax.inject:1' */
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
/*
        // lombok 사용으로 생략
        public CientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
*/
        public int logic(){
            /*PrototypeBean prototypeBean = prototypeBeanProvider.getObject();*/
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private  int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("Prototype.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
