/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.wechat.manual.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ower.dsyz.common.core.json.CustomDateDeSerializer;
import com.ower.dsyz.common.core.json.CustomDateSerializer;

/**
 * <pre>
 * 微信公众号配置拓展类
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月11日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class WechatAppDTO  implements Serializable{
    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 423284860650385423L;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : id
     */
    private String id;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : app_name
     */
    private String appName;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : app_id
     */
    private String appId;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : secret
     */
    private String secret;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : token
     */
    private String token;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : domain
     */
    private String domain;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : code
     */
    private String code;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : mess_secret
     */
    private String messSecret;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : is_valid
     */
    private String isValid;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : remark
     */
    private String remark;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 
     * 表 : t_wechat_app
     * 对应字段 : update_date
     */
    private Date updateDate;

    /**
     * get method 
     *
     * @return t_wechat_app.id：
     */
    public String getId() {
        return id;
    }

    /**
     * set method 
     *
     * @param id  
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.app_name：
     */
    public String getAppName() {
        return appName;
    }

    /**
     * set method 
     *
     * @param appName  
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.app_id：
     */
    public String getAppId() {
        return appId;
    }

    /**
     * set method 
     *
     * @param appId  
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.secret：
     */
    public String getSecret() {
        return secret;
    }

    /**
     * set method 
     *
     * @param secret  
     */
    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.token：
     */
    public String getToken() {
        return token;
    }

    /**
     * set method 
     *
     * @param token  
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.domain：
     */
    public String getDomain() {
        return domain;
    }

    /**
     * set method 
     *
     * @param domain  
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.code：
     */
    public String getCode() {
        return code;
    }

    /**
     * set method 
     *
     * @param code  
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.mess_secret：
     */
    public String getMessSecret() {
        return messSecret;
    }

    /**
     * set method 
     *
     * @param messSecret  
     */
    public void setMessSecret(String messSecret) {
        this.messSecret = messSecret == null ? null : messSecret.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.is_valid：
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * set method 
     *
     * @param isValid  
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.remark：
     */
    public String getRemark() {
        return remark;
    }

    /**
     * set method 
     *
     * @param remark  
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * get method 
     *
     * @return t_wechat_app.create_date：
     */
    @JsonSerialize(using = CustomDateSerializer.class) 
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * get method 
     *
     * @return t_wechat_app.update_date：
     */
    @JsonSerialize(using = CustomDateSerializer.class) 
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * set method 
     *
     * @param updateDate  
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appName=").append(appName);
        sb.append(", appId=").append(appId);
        sb.append(", secret=").append(secret);
        sb.append(", token=").append(token);
        sb.append(", domain=").append(domain);
        sb.append(", code=").append(code);
        sb.append(", messSecret=").append(messSecret);
        sb.append(", isValid=").append(isValid);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}
