package com.zcf.world.common.layui.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
/**
 * @author 袁
 * @date 2018/11/21 0021
 */
@Data
public class ParentMenu {
    @JsonIgnore
    private Integer id;
    @JsonIgnore
    private Integer parentId;
    private String name;
    private String title;
    private String icon;

    private List<SubMenu> list;


}