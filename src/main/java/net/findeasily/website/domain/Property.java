package net.findeasily.website.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */

@Getter
@Setter
@ToString
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String ownerId;
    private Date addedTime;
    private Boolean active;

}
