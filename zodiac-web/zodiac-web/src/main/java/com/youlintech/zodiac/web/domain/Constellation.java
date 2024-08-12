package com.youlintech.zodiac.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_constellation
 */
@TableName(value ="tb_constellation")
@Data
public class Constellation implements Serializable {
    /**
     * 星座id
     */
    @TableId
    private Long id;

    /**
     * 星座名称
     */
    private String constellationName;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 更新日期
     */
    private Date updataDate;

    /**
     * 网址
     */
    private String webUrl;

    /**
     * 素材id
     */
    private Long meterialLibraryId;

    /**
     * 周素材ID
     */
    private Long weekMeterialLibraryId;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Constellation other = (Constellation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getConstellationName() == null ? other.getConstellationName() == null : this.getConstellationName().equals(other.getConstellationName()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getUpdataDate() == null ? other.getUpdataDate() == null : this.getUpdataDate().equals(other.getUpdataDate()))
            && (this.getWebUrl() == null ? other.getWebUrl() == null : this.getWebUrl().equals(other.getWebUrl()))
            && (this.getMeterialLibraryId() == null ? other.getMeterialLibraryId() == null : this.getMeterialLibraryId().equals(other.getMeterialLibraryId()))
            && (this.getWeekMeterialLibraryId() == null ? other.getWeekMeterialLibraryId() == null : this.getWeekMeterialLibraryId().equals(other.getWeekMeterialLibraryId()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getConstellationName() == null) ? 0 : getConstellationName().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getUpdataDate() == null) ? 0 : getUpdataDate().hashCode());
        result = prime * result + ((getWebUrl() == null) ? 0 : getWebUrl().hashCode());
        result = prime * result + ((getMeterialLibraryId() == null) ? 0 : getMeterialLibraryId().hashCode());
        result = prime * result + ((getWeekMeterialLibraryId() == null) ? 0 : getWeekMeterialLibraryId().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", constellationName=").append(constellationName);
        sb.append(", birthday=").append(birthday);
        sb.append(", updataDate=").append(updataDate);
        sb.append(", webUrl=").append(webUrl);
        sb.append(", meterialLibraryId=").append(meterialLibraryId);
        sb.append(", weekMeterialLibraryId=").append(weekMeterialLibraryId);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}