package com.zcf.world.controller.console;


import com.zcf.world.common.layui.menu.MenuResult;
import com.zcf.world.common.layui.menu.ParentMenu;
import com.zcf.world.common.utils.FileUploadUtils;
import com.zcf.world.service.layui.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author 袁
 * @date 2018/10/23 0023
 */
@Controller
public class LayUiMenu {
    @Autowired
    private MenuService service;

    @GetMapping("LayUiMenus")
    @ResponseBody
    public MenuResult menu() {
        List<ParentMenu> menu = this.service.menu();
        MenuResult menuResult = new MenuResult();
        menuResult.setCode(0);
        menuResult.setMsg("返回成功");
        menuResult.setData(menu);
        return menuResult;
    }


    @GetMapping("/index")
    public String index() {
        return "login";
    }

    @GetMapping("/test")
    public String Test() {
        return "Test";
    }

    @GetMapping("/webSocket")
    public String webSocket() {
        return "websocket";
    }

    @GetMapping("/map")
    public String map() {
        return "map";
    }
}
