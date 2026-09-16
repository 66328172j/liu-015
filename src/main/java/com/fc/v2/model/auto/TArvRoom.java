package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 库房档案对象 t_arv_room
 *
 * @author fuce
 * @date 2026-09-12
 */
@TableName("t_arv_room")
@ApiModel(value = "TArvRoom", description = "库房档案")
public class TArvRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 库房档案编号 */
    @TableField("room_no")
    @ApiModelProperty(value = "库房档案编号")
    private String roomNo;

    /** 库房名称 */
    @TableField("room_name")
    @ApiModelProperty(value = "库房名称")
    private String roomName;

    /** 库房类型 纸档/胶片/特藏/综合 */
    @TableField("room_type")
    @ApiModelProperty(value = "库房类型 纸档/胶片/特藏/综合")
    private String roomType;

    /** 所属档案馆ID */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("depot_id")
    @ApiModelProperty(value = "所属档案馆ID")
    private Long depotId;

    /** 所属档案馆编号(冗余，以档案为准) */
    @TableField("depot_code")
    @ApiModelProperty(value = "所属档案馆编号(冗余，以档案为准)")
    private String depotCode;

    /** 库房面积(平方米) */
    @TableField("area")
    @ApiModelProperty(value = "库房面积(平方米)")
    private BigDecimal area;

    /** 复核时限 */
    @TableField("review_due")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "复核时限")
    private Date reviewDue;

    /** 距复核时限剩余天数 */
    @TableField("remain_days")
    @ApiModelProperty(value = "距复核时限剩余天数")
    private Integer remainDays;

    /** 库房管理员 */
    @TableField("keeper")
    @ApiModelProperty(value = "库房管理员")
    private String keeper;

    /** 库房状态 0在用 1已停用 */
    @TableField("room_status")
    @ApiModelProperty(value = "库房状态 0在用 1已停用")
    private Integer roomStatus;

    /** 删除标记 0正常 1删除 */
    @TableField("del_flag")
    @ApiModelProperty(value = "删除标记 0正常 1删除")
    private Integer delFlag;

    /** 创建者 */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /** 创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /** 更新者 */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /** 更新时间 */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 备注 */
    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Date getReviewDue() {
        return reviewDue;
    }

    public void setReviewDue(Date reviewDue) {
        this.reviewDue = reviewDue;
    }

    public Integer getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(Integer remainDays) {
        this.remainDays = remainDays;
    }

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    public Integer getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(Integer roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
