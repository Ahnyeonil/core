package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message : " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("disconnect : " + url);
    }

    // 의존관계 주입이 끝나면 호출해준다.
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    // 빈이 종료될때 호출된다.
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

    // 직접 작성 - 의존관계 주입이 끝나면 호출해준다.
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    // 직접 작성 - 빈이 종료될때 호출된다.
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
