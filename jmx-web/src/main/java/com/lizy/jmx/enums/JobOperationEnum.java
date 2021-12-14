package com.lizy.jmx.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
 * @date Created in 2021年12月14日 18:14
 * @since 1.0
 */
@AllArgsConstructor
@Getter
public enum JobOperationEnum {

    PAUSE_JOB("pauseJob", "停止任务"),
    ALL_JOB_DETAILS("AllJobDetails", "查询任务列表"),
    GET_TRIGGER("getTrigger", "获取getTrigger"),
    ADD_JOB("addJob", "添加job"),
    UNSCHEDULE_JOB("unscheduleJob", "删除失败 回滚已经成功删除的机器"),
    DELETE_JOB("deleteJob", "删除job"),
    GET_TRIGGER_STATE("getTriggerState", "获取job状态"),
    SCHEDULE_JOB("scheduleJob", "添加触发器"),
    RESUME_JOB("resumeJob", "唤醒job");

    /**
     * 操作编码
     */
    private String operation;

    /**
     * 描述
     */
    private String desc;


}
