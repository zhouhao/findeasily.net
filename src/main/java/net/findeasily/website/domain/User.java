package net.findeasily.website.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author Hao Zhou
 * @since 2018-05-23
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String username;
    private String email;
    private String password;
    private Date registerTime;
    private Boolean activated;
    private Integer lockStatus;
    private String role;

}
