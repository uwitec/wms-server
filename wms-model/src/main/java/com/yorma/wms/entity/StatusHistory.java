/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.0.0
 */

package com.yorma.wms.entity;

import java.util.Date;

/**
 * STATUS_HISTORY
 * 
 * @author subiao
 * @version 1.0.0 2017-08-29
 */
public class StatusHistory {
    /** 版本号 */
    @SuppressWarnings("unused")
	private static final long serialVersionUID = -6116896369498084853L;

    /**  */
    private Long id;

    /**  */
    private String applyId;

    /** 更新日期 */
    private Date lastTime;

    /** 用户输入的日期 */
    private Date operateTime;

    /**  */
    private String lastUser;

    /**  */
    private String memo;

    /**  */
    private Integer statusId;

    /** 创建日期 */
    private Date createTime;

    /**  */
    private String partId;

    /**  */
    private String type;

    /**
     * 获取
     * 
     * @return 
     */
    public Long getId() {
        return this.id;
    }
    /**
     * 构造无参方法
     */
    public StatusHistory() {
		
	}
    /**
     * 构造有参方法
     */
	public StatusHistory(Long id, String applyId, Date lastTime, Date operateTime, String lastUser, String memo,
			Integer statusId, Date createTime, String partId, String type) {
		super();
		this.id = id;
		this.applyId = applyId;
		this.lastTime = lastTime;
		this.operateTime = operateTime;
		this.lastUser = lastUser;
		this.memo = memo;
		this.statusId = statusId;
		this.createTime = createTime;
		this.partId = partId;
		this.type = type;
	}

	/**
     * 设置
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getApplyId() {
        return this.applyId;
    }

    /**
     * 设置
     * 
     * @param applyId
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取更新日期
     * 
     * @return 更新日期
     */
    public Date getLastTime() {
        return this.lastTime;
    }

    /**
     * 设置更新日期
     * 
     * @param lastTime
     *          更新日期
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取用户输入的日期
     * 
     * @return 用户输入的日期
     */
    public Date getOperateTime() {
        return this.operateTime;
    }

    /**
     * 设置用户输入的日期
     * 
     * @param operateTime
     *          用户输入的日期
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getLastUser() {
        return this.lastUser;
    }

    /**
     * 设置
     * 
     * @param lastUser
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 设置
     * 
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Integer getStatusId() {
        return this.statusId;
    }

    /**
     * 设置
     * 
     * @param statusId
     */
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    /**
     * 获取创建日期
     * 
     * @return 创建日期
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建日期
     * 
     * @param createTime
     *          创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getPartId() {
        return this.partId;
    }

    /**
     * 设置
     * 
     * @param partId
     */
    public void setPartId(String partId) {
        this.partId = partId;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "StatusHistory [id=" + id + ", applyId=" + applyId + ", lastTime=" + lastTime + ", operateTime="
				+ operateTime + ", lastUser=" + lastUser + ", memo=" + memo + ", statusId=" + statusId + ", createTime="
				+ createTime + ", partId=" + partId + ", type=" + type + "]";
	}
    
    
    
}