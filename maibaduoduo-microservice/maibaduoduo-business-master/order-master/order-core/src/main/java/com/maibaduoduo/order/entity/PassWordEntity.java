package com.maibaduoduo.order.entity;

import com.maibaduoduo.configuration.utils.validator.Password;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PassWordEntity  implements Serializable {
    @Password
    private String pasword;
    @NotNull
    private Long id;
    @NotEmpty
    private String name;

}
