/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.dromara.raincat.common.netty.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dromara.raincat.common.enums.TransactionRoleEnum;
import org.dromara.raincat.common.enums.TransactionStatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * TxTransactionItem.
 *
 * @author xiaoyu
 */
@Data
@Slf4j
public class TxTransactionItem implements Serializable {

    private static final long serialVersionUID = -983809184773470584L;
    /**
     * 线程安全
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * taskKey.
     */
    private String taskKey;

    /**
     * 参与事务id.
     */
    private String transId;

    /**
     * 事务状态. {@linkplain TransactionStatusEnum}
     */
    private int status;

    /**
     * 事务角色. {@linkplain TransactionRoleEnum}
     */
    private int role;

    /**
     * 模块信息.
     */
    private String modelName;

    /**
     * tm 的域名信息.
     */
    private String tmDomain;

    /**
     * 存放事务组id.
     */
    private String txGroupId;

    /**
     * 创建时间.
     */
    private String createDate;

    /**
     * 事务最大等待时间 单位秒.
     */
    private Integer waitMaxTime;

    /**
     * 执行类名称.
     */
    private String targetClass;

    /**
     * 执行方法.
     */
    private String targetMethod;

    /**
     * 耗时 秒.
     */
    private Long consumeTime;

    /**
     * 操作结果信息.
     */
    private Object message;


    public void setMessage(Object message) {
        if (message != null && !(message instanceof String)) {
            try {
                this.message = OBJECT_MAPPER.writeValueAsString(message);
            } catch (JsonProcessingException e) {
                this.message = "internal server error,fail to parse object";
                log.error("设置操作结果信息时出错，message:{}", message, e);
            }
        } else {
            this.message = message;
        }
    }


}
