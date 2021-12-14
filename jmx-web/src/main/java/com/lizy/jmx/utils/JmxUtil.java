package com.lizy.jmx.utils;

import com.lizy.jmx.enums.JobOperationEnum;
import com.lizy.jmx.model.JMXOperationParams;
import com.lizy.jmx.model.JobOptionRequest;
import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>jmx操作工具</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lizy
 * @version 1.0
 * @date Created in 2021年12月14日 17:54
 * @since 1.0
 */
@Slf4j
public class JmxUtil {

    public static String JMX_URL_FRONT = "service:jmx:rmi:///jndi/rmi://";

    public static String COLON = ":";

    public static String JMX_URL_BACK = "/jmxrmi";

    /**
     * 获取单台机器的属性
     *
     * @param host
     * @param port
     * @param jobOperationEnum
     * @return
     */
    public static Object getSingleJMXAttribute(String host, String port, JobOperationEnum jobOperationEnum) {
        Object attribute = null;
        JMXConnector jmxConnector = createJmxConnector(host, port);
        try {
            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
            Set<ObjectName> objectNames = mBeanServerConnection.queryNames(new ObjectName("quartz:type=QuartzScheduler,*"), null);
            attribute = mBeanServerConnection.getAttribute(objectNames.iterator().next(), jobOperationEnum.getOperation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attribute;
    }

    public static void addJobAndTrigger(JMXConnector jmxConnector, JobOptionRequest jobOptionRequest) {
        Map<String, Object> jobMap = new HashMap<>();
        jobMap.put("name", jobOptionRequest.getName());
        jobMap.put("group", jobOptionRequest.getGroup());
        jobMap.put("description", jobOptionRequest.getDescription());
        jobMap.put("jobClass", jobOptionRequest.getJobClass());
        jobMap.put("jobDetailClass", "org.quartz.impl.JobDetailImpl");
        jobMap.put("durability", true);

        HashMap<String, Object> triggerMap = new HashMap<String, Object>();

        triggerMap.put("name", jobOptionRequest.getName());
        triggerMap.put("group", jobOptionRequest.getGroup());
        triggerMap.put("description", jobOptionRequest.getDescription());

        if (jobOptionRequest.getType() == 0) {
            triggerMap.put("startTime", new Date());
            triggerMap.put("triggerClass", "org.quartz.impl.triggers.SimpleTriggerImpl");
        } else {
            triggerMap.put("cronExpression", jobOptionRequest.getCron());
            triggerMap.put("triggerClass", "org.quartz.impl.triggers.CronTriggerImpl");
        }
        triggerMap.put("jobName", jobOptionRequest.getName());
        triggerMap.put("jobGroup", jobOptionRequest.getGroup());

        JMXOperationParams jmxOperationParams = JMXOperationParams.builder()
                .operation(JobOperationEnum.SCHEDULE_JOB.getOperation())
                .signature(new String[]{"java.util.Map", "java.util.Map"})
                .parameters(new Object[]{jobMap, triggerMap})
                .build();

        doJMXOperation(jmxConnector, jmxOperationParams);
    }

    /**
     * 执行jmx操作
     *
     * @param jmxConnector
     * @param jmxOperationParams
     */
    public static void doJMXOperation(JMXConnector jmxConnector, JMXOperationParams jmxOperationParams) {
        try {
            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
            Set<ObjectName> objectNames = mBeanServerConnection.queryNames(new ObjectName("quartz:type=QuartzScheduler,*"), null);
            Object singleResult = mBeanServerConnection.invoke(
                    objectNames.iterator().next(),
                    jmxOperationParams.getOperation(),
                    jmxOperationParams.getParameters(),
                    jmxOperationParams.getSignature());
            System.out.println("dfd");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JMXConnector createJmxConnector(String host, String port) {
        JMXServiceURL url = null;
        JMXConnector jmxConnector = null;
        StringBuilder vUrlString = new StringBuilder(JMX_URL_FRONT);
        vUrlString.append(host);
        vUrlString.append(COLON);
        vUrlString.append(port);
        vUrlString.append(JMX_URL_BACK);
        try {
            url = new JMXServiceURL(vUrlString.toString());
            jmxConnector = JMXConnectorFactory.connect(url, null);
        } catch (Exception e) {
            log.error("jmx远程连接创建失败：{}", e.getMessage());
        }
        return jmxConnector;
    }
}
