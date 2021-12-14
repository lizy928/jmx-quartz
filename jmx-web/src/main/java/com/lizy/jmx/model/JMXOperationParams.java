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
 * @date Created in 2021年12月14日 18:37
 * @since 1.0
 */
@Data
@Builder
public class JMXOperationParams {

    private String operation;

    private String[] signature;

    private Object[] parameters;
}
