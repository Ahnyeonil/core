package hello.core.logdemo;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    // MyLogger -> proxyMode = ScopedProxyMode.TARGET_CLASS 추가
    /*private final MyLogger myLoggerProvider;*/
    private final MyLogger myLogger;

    public void logic(String id) {
        // MyLogger -> proxyMode = ScopedProxyMode.TARGET_CLASS 추가
        /*MyLogger myLogger = myLoggerProvider.getObject();*/
        myLogger.log("Service id = " + id);

    }
}
