package com.lizy.jmx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
 * @date Created in 2021年12月14日 17:41
 * @since 1.0
 */
@SpringBootApplication
public class JmxApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(JmxApplication.class);
    }
}
