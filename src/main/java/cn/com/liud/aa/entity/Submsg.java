package cn.com.liud.aa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("aa_submsg")
public class Submsg extends Model<Submsg> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 付钱者
     */
    private String subUser;

    /**
     * 金额
     */
    private Double subMoney;

    /**
     * 时间
     */
    private LocalDateTime subTime;

    /**
     * 图片
     */
    private String subPicture;

    /**
     * aa参与者
     */
    private String aaPeople;

    /**
     * 是否被审核
     */
    private Integer isChecked;

    /**
     * 备注
     */
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
