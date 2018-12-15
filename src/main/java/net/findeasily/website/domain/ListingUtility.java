package net.findeasily.website.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * info of utilities for renting
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Getter
@Setter
@ToString
public class ListingUtility implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "listing_id", type = IdType.INPUT)
    private String listingId;
    private Boolean ac;
    private Boolean electricity;
    private Boolean water;
    private Boolean hotWater;
    private Boolean heater;
    private Boolean internet;
    private Boolean parking;
}
