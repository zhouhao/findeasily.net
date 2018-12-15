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
public class Listing implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String propertyId;
    private String ownerId;
    private Integer rentPrice;
    private Integer securityDeposit;
    private Date availableDate;
    private Integer bedroomCount;
    private Integer bathroomCount;
    private Integer duration;
    private String contactType;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private Boolean showEmail;
    private Boolean showPhone;
    private String description;
    private String type;


}
