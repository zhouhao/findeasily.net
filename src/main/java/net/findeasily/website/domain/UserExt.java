package net.findeasily.website.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author findeasily
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserExt extends Model<UserExt> {

    private static final long serialVersionUID = 1L;

    @TableId
    private String userId;

    private String description;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
