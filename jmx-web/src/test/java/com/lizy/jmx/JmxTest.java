package com.lizy.jmx;

import com.lizy.jmx.enums.JobOperationEnum;
import com.lizy.jmx.model.JobOptionRequest;
import com.lizy.jmx.utils.JmxUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.remote.JMXConnector;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lizy
 * @version 1.0
 * @date Created in 2021年12月14日 18:44
 * @since 1.0
 */
@SpringBootTest
public class JmxTest {

    @Test
    public void test(){
        String host = "10.30.26.90";
        String port = "1099";

        JMXConnector jmxConnector = JmxUtil.createJmxConnector(host, port);
        JobOptionRequest request = JobOptionRequest.builder()
                .name("test")
                .group("test")
                .cron("0/10 * * * * ?")
                .jobClass("com.lizy.job.task.MyCronJob")
                .type(1)
                .description("test")
                .build();

        JmxUtil.addJobAndTrigger(jmxConnector, request);
    }
}
