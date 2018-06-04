package com.ower.dsyz.admin.manual.dto;

import java.io.Serializable;

public class AdminInstitutionDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 2908511479543714806L;

	/**
     * 机构id
     * 表 : t_admin_institution
     * 对应字段 : ins_id
     */
    private String insId;

    /**
     * 机构的名称（金财互联、服务商、普通客户的名称）
     * 表 : t_admin_institution
     * 对应字段 : ins_name
     */
    private String insName;

    /**
     * 机构简称
     * 表 : t_admin_institution
     * 对应字段 : ins_short_name
     */
    private String insShortName;

    /**
     * 组织机构代码（社会停信用代码）
     * 表 : t_admin_institution
     * 对应字段 : organization_code
     */
    private String organizationCode;

    /**
     * 
     * 表 : t_admin_institution
     * 对应字段 : type_id
     */
    private String typeId;

    /**
     * 
     * 表 : t_admin_institution
     * 对应字段 : bus_type_id
     */
    private String busTypeId;

    /**
     * 行业类型（01计算机软件02计算机硬件）
     * 表 : t_admin_institution
     * 对应字段 : industry_type
     */
    private String industryType;

    /**
     * 企业法人
     * 表 : t_admin_institution
     * 对应字段 : legal
     */
    private String legal;

    /**
     * 联系人
     * 表 : t_admin_institution
     * 对应字段 : contact
     */
    private String contact;

    /**
     * 联系人手机号码
     * 表 : t_admin_institution
     * 对应字段 : phone
     */
    private String phone;

    /**
     * 机构管理员
     * 表 : t_admin_institution
     * 对应字段 : ins_manager
     */
    private String insManager;

    /**
     * 管理员电话
     * 表 : t_admin_institution
     * 对应字段 : ins_manager_phone
     */
    private String insManagerPhone;

    /**
     * 企业地址
     * 表 : t_admin_institution
     * 对应字段 : address
     */
    private String address;

    /**
     * 企业网址
     * 表 : t_admin_institution
     * 对应字段 : addr_web
     */
    private String addrWeb;

    /**
     * 背景图片
     * 表 : t_admin_institution
     * 对应字段 : ins_logo_backgroud
     */
    private String insLogoBackgroud;

    /**
     * 机构logo
     * 表 : t_admin_institution
     * 对应字段 : ins_logo
     */
    private String insLogo;

    /**
     * 描述
     * 表 : t_admin_institution
     * 对应字段 : remark
     */
    private String remark;

    /**
     * 是否启用
     * 表 : t_admin_institution
     * 对应字段 : is_valid
     */
    private String isValid;

    /**
     * 业务类型
     */
    private String busTypeName;
    
    /**
     * 企业类型
     */
    private String insTypeName;
    
    public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public String getInsShortName() {
		return insShortName;
	}

	public void setInsShortName(String insShortName) {
		this.insShortName = insShortName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getBusTypeId() {
		return busTypeId;
	}

	public void setBusTypeId(String busTypeId) {
		this.busTypeId = busTypeId;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInsManager() {
		return insManager;
	}

	public void setInsManager(String insManager) {
		this.insManager = insManager;
	}

	public String getInsManagerPhone() {
		return insManagerPhone;
	}

	public void setInsManagerPhone(String insManagerPhone) {
		this.insManagerPhone = insManagerPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddrWeb() {
		return addrWeb;
	}

	public void setAddrWeb(String addrWeb) {
		this.addrWeb = addrWeb;
	}

	public String getInsLogoBackgroud() {
		return insLogoBackgroud;
	}

	public void setInsLogoBackgroud(String insLogoBackgroud) {
		this.insLogoBackgroud = insLogoBackgroud;
	}

	public String getInsLogo() {
		return insLogo;
	}

	public void setInsLogo(String insLogo) {
		this.insLogo = insLogo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getBusTypeName() {
		return busTypeName;
	}

	public void setBusTypeName(String busTypeName) {
		this.busTypeName = busTypeName;
	}

	public String getInsTypeName() {
		return insTypeName;
	}

	public void setInsTypeName(String insTypeName) {
		this.insTypeName = insTypeName;
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
        sb.append(", insId=").append(insId);
        sb.append(", insName=").append(insName);
        sb.append(", insShortName=").append(insShortName);
        sb.append(", organizationCode=").append(organizationCode);
        sb.append(", typeId=").append(typeId);
        sb.append(", busTypeId=").append(busTypeId);
        sb.append(", industryType=").append(industryType);
        sb.append(", legal=").append(legal);
        sb.append(", contact=").append(contact);
        sb.append(", phone=").append(phone);
        sb.append(", insManager=").append(insManager);
        sb.append(", insManagerPhone=").append(insManagerPhone);
        sb.append(", address=").append(address);
        sb.append(", addrWeb=").append(addrWeb);
        sb.append(", insLogoBackgroud=").append(insLogoBackgroud);
        sb.append(", insLogo=").append(insLogo);
        sb.append(", remark=").append(remark);
        sb.append(", isValid=").append(isValid);
        sb.append(", busTypeName=").append(busTypeName);
        sb.append(", insTypeName=").append(insTypeName);
        sb.append("]");
        return sb.toString();
    }
}
