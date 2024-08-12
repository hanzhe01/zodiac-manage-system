package com.youlintech.zodiac.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_constellation_pairing
 */
@TableName(value ="tb_constellation_pairing")
@Data
public class ConstellationPairing implements Serializable {
    /**
     * 素材配对id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 星座被配对id
     */
    private Long bePairedId;

    /**
     * 星座配对ID
     */
    private Long pairingId;

    /**
     * 星级
     */
    private BigDecimal starLevel;

    /**
     * 建议
     */
    private String suggestion;

    /**
     * 素材更新时间
     */
    private Date dateUpdated;

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
        ConstellationPairing other = (ConstellationPairing) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBePairedId() == null ? other.getBePairedId() == null : this.getBePairedId().equals(other.getBePairedId()))
            && (this.getPairingId() == null ? other.getPairingId() == null : this.getPairingId().equals(other.getPairingId()))
            && (this.getStarLevel() == null ? other.getStarLevel() == null : this.getStarLevel().equals(other.getStarLevel()))
            && (this.getSuggestion() == null ? other.getSuggestion() == null : this.getSuggestion().equals(other.getSuggestion()))
            && (this.getDateUpdated() == null ? other.getDateUpdated() == null : this.getDateUpdated().equals(other.getDateUpdated()))
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
        result = prime * result + ((getBePairedId() == null) ? 0 : getBePairedId().hashCode());
        result = prime * result + ((getPairingId() == null) ? 0 : getPairingId().hashCode());
        result = prime * result + ((getStarLevel() == null) ? 0 : getStarLevel().hashCode());
        result = prime * result + ((getSuggestion() == null) ? 0 : getSuggestion().hashCode());
        result = prime * result + ((getDateUpdated() == null) ? 0 : getDateUpdated().hashCode());
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
        sb.append(", bePairedId=").append(bePairedId);
        sb.append(", pairingId=").append(pairingId);
        sb.append(", starLevel=").append(starLevel);
        sb.append(", suggestion=").append(suggestion);
        sb.append(", dateUpdated=").append(dateUpdated);
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