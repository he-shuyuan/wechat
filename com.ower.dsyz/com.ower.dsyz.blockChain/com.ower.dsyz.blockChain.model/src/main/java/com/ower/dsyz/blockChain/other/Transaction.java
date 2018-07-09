/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.blockChain.other;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;


/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年3月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Transaction implements Serializable {

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 发送方
     */
    @NotBlank(message="发送方 recipient 不能为空")
    private String sender;
    
    /**
     * 接收方
     */
    @NotBlank(message="接收方 recipient 不能为空")
    private String recipient;
    
    /**
     * 交易数量
     */
    @NotNull(message="amount不能为空")
    @Min(value=1,message="交易数量不能小于1")
    private Long amount;

    
    public String getSender() {
        return sender;
    }

    
    public String getRecipient() {
        return recipient;
    }

    
    public Long getAmount() {
        return amount;
    }

    
    public void setSender(String sender) {
        this.sender = sender;
    }

    
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    
    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Transaction [sender=" + sender + ", recipient=" + recipient + ", amount=" + amount + "]";
    }
    
    
}
