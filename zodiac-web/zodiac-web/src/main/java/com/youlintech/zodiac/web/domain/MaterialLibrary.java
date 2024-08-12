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
 * @TableName tb_material_library
 */
@TableName(value ="tb_material_library")
@Data
public class MaterialLibrary implements Serializable {
    /**
     * 素材id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 星座id
     */
    private Long constellationId;

    /**
     * 总体运势
     */
    private String horoscopeFortune;

    /**
     * 总体运势星级
     */
    private BigDecimal horoscopeFortuneStar;

    /**
     * 幸运色
     */
    private String luckyColor;

    /**
     * 幸运数字
     */
    private Long luckyNumbers;

    /**
     * 幸运时间
     */
    private String luckyTime;

    /**
     * 幸运物品
     */
    private String luckyItems;

    /**
     * 购物链接
     */
    private String purchaseLink;

    /**
     * 运势标题一名称
     */
    private String firstTitleName;

    /**
     * 运势标题一运势内容
     */
    private String firstTitleContent;

    /**
     * 运势标题一运势内容星级
     */
    private BigDecimal firstTitleStar;

    /**
     * 运势标题二名称
     */
    private String secondTitleName;

    /**
     * 运势标题二内容
     */
    private String secondTitleContent;

    /**
     * 运势标题二星级
     */
    private BigDecimal secondTitleStar;

    /**
     * 运势标题三名称
     */
    private String thirdTitleName;

    /**
     * 运势标题三内容


     */
    private String thirdTitleContent;

    /**
     * 运势标题三星级
     */
    private BigDecimal thirdTitleStar;

    /**
     * 是否是本周运势0-是 1-否 
     */
    private Long isWeekFortune;

    /**
     * 生成日期时间
     */
    private Date generationDate;

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
        MaterialLibrary other = (MaterialLibrary) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getConstellationId() == null ? other.getConstellationId() == null : this.getConstellationId().equals(other.getConstellationId()))
            && (this.getHoroscopeFortune() == null ? other.getHoroscopeFortune() == null : this.getHoroscopeFortune().equals(other.getHoroscopeFortune()))
            && (this.getHoroscopeFortuneStar() == null ? other.getHoroscopeFortuneStar() == null : this.getHoroscopeFortuneStar().equals(other.getHoroscopeFortuneStar()))
            && (this.getLuckyColor() == null ? other.getLuckyColor() == null : this.getLuckyColor().equals(other.getLuckyColor()))
            && (this.getLuckyNumbers() == null ? other.getLuckyNumbers() == null : this.getLuckyNumbers().equals(other.getLuckyNumbers()))
            && (this.getLuckyTime() == null ? other.getLuckyTime() == null : this.getLuckyTime().equals(other.getLuckyTime()))
            && (this.getLuckyItems() == null ? other.getLuckyItems() == null : this.getLuckyItems().equals(other.getLuckyItems()))
            && (this.getPurchaseLink() == null ? other.getPurchaseLink() == null : this.getPurchaseLink().equals(other.getPurchaseLink()))
            && (this.getFirstTitleName() == null ? other.getFirstTitleName() == null : this.getFirstTitleName().equals(other.getFirstTitleName()))
            && (this.getFirstTitleContent() == null ? other.getFirstTitleContent() == null : this.getFirstTitleContent().equals(other.getFirstTitleContent()))
            && (this.getFirstTitleStar() == null ? other.getFirstTitleStar() == null : this.getFirstTitleStar().equals(other.getFirstTitleStar()))
            && (this.getSecondTitleName() == null ? other.getSecondTitleName() == null : this.getSecondTitleName().equals(other.getSecondTitleName()))
            && (this.getSecondTitleContent() == null ? other.getSecondTitleContent() == null : this.getSecondTitleContent().equals(other.getSecondTitleContent()))
            && (this.getSecondTitleStar() == null ? other.getSecondTitleStar() == null : this.getSecondTitleStar().equals(other.getSecondTitleStar()))
            && (this.getThirdTitleName() == null ? other.getThirdTitleName() == null : this.getThirdTitleName().equals(other.getThirdTitleName()))
            && (this.getThirdTitleContent() == null ? other.getThirdTitleContent() == null : this.getThirdTitleContent().equals(other.getThirdTitleContent()))
            && (this.getThirdTitleStar() == null ? other.getThirdTitleStar() == null : this.getThirdTitleStar().equals(other.getThirdTitleStar()))
            && (this.getIsWeekFortune() == null ? other.getIsWeekFortune() == null : this.getIsWeekFortune().equals(other.getIsWeekFortune()))
            && (this.getGenerationDate() == null ? other.getGenerationDate() == null : this.getGenerationDate().equals(other.getGenerationDate()))
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
        result = prime * result + ((getConstellationId() == null) ? 0 : getConstellationId().hashCode());
        result = prime * result + ((getHoroscopeFortune() == null) ? 0 : getHoroscopeFortune().hashCode());
        result = prime * result + ((getHoroscopeFortuneStar() == null) ? 0 : getHoroscopeFortuneStar().hashCode());
        result = prime * result + ((getLuckyColor() == null) ? 0 : getLuckyColor().hashCode());
        result = prime * result + ((getLuckyNumbers() == null) ? 0 : getLuckyNumbers().hashCode());
        result = prime * result + ((getLuckyTime() == null) ? 0 : getLuckyTime().hashCode());
        result = prime * result + ((getLuckyItems() == null) ? 0 : getLuckyItems().hashCode());
        result = prime * result + ((getPurchaseLink() == null) ? 0 : getPurchaseLink().hashCode());
        result = prime * result + ((getFirstTitleName() == null) ? 0 : getFirstTitleName().hashCode());
        result = prime * result + ((getFirstTitleContent() == null) ? 0 : getFirstTitleContent().hashCode());
        result = prime * result + ((getFirstTitleStar() == null) ? 0 : getFirstTitleStar().hashCode());
        result = prime * result + ((getSecondTitleName() == null) ? 0 : getSecondTitleName().hashCode());
        result = prime * result + ((getSecondTitleContent() == null) ? 0 : getSecondTitleContent().hashCode());
        result = prime * result + ((getSecondTitleStar() == null) ? 0 : getSecondTitleStar().hashCode());
        result = prime * result + ((getThirdTitleName() == null) ? 0 : getThirdTitleName().hashCode());
        result = prime * result + ((getThirdTitleContent() == null) ? 0 : getThirdTitleContent().hashCode());
        result = prime * result + ((getThirdTitleStar() == null) ? 0 : getThirdTitleStar().hashCode());
        result = prime * result + ((getIsWeekFortune() == null) ? 0 : getIsWeekFortune().hashCode());
        result = prime * result + ((getGenerationDate() == null) ? 0 : getGenerationDate().hashCode());
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
        sb.append(", constellationId=").append(constellationId);
        sb.append(", horoscopeFortune=").append(horoscopeFortune);
        sb.append(", horoscopeFortuneStar=").append(horoscopeFortuneStar);
        sb.append(", luckyColor=").append(luckyColor);
        sb.append(", luckyNumbers=").append(luckyNumbers);
        sb.append(", luckyTime=").append(luckyTime);
        sb.append(", luckyItems=").append(luckyItems);
        sb.append(", purchaseLink=").append(purchaseLink);
        sb.append(", firstTitleName=").append(firstTitleName);
        sb.append(", firstTitleContent=").append(firstTitleContent);
        sb.append(", firstTitleStar=").append(firstTitleStar);
        sb.append(", secondTitleName=").append(secondTitleName);
        sb.append(", secondTitleContent=").append(secondTitleContent);
        sb.append(", secondTitleStar=").append(secondTitleStar);
        sb.append(", thirdTitleName=").append(thirdTitleName);
        sb.append(", thirdTitleContent=").append(thirdTitleContent);
        sb.append(", thirdTitleStar=").append(thirdTitleStar);
        sb.append(", isWeekFortune=").append(isWeekFortune);
        sb.append(", generationDate=").append(generationDate);
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