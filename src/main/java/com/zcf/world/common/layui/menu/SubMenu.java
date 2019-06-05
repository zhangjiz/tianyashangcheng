package com.zcf.world.common.layui.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author 袁
 * @date 2018/11/21 0021
 */
@Data
public class SubMenu {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer subMenuId;
    private String name;
    private String title;
    private String jump;

}