
package com.zcf.world.common.layui.menu;

import lombok.Data;

import java.util.List;

/**
 * @author 袁
 * @date 2018/11/21 0021
 */
@Data
public class MenuResult {
    private int code;
    private String msg;
    private List<ParentMenu> data;

}