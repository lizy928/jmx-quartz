package com.lizy.jmx.model;

import lombok.Builder;
import lombok.Data;

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
 * @date Created in 2021年12月14日 18:27
 * @since 1.0
 */
@Data
@Builder
public class JobOptionRequest {

    /**
     * 名字
     */
    private String name;

    /**
     * jod组名
     */
    private  String group;

    /**
     * 表达式
     */
    private  String cron;

    /**
     * 任务类全名
     */
    private  String jobClass;

    // 0:普通，1：core表达式
    private Integer type;

    /**
     * 备注
     */
    private  String description;
}
