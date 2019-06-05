package com.zcf.world.service.layui;

import com.zcf.world.common.layui.menu.ParentMenu;
import com.zcf.world.common.layui.menu.SubMenu;
import com.zcf.world.mapper.layui.ParentMenuMapper;
import com.zcf.world.mapper.layui.SubMenuMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yuan
 * @date 2018/10/23 0023
 */
@Service
public class MenuService {
    @Resource
    private ParentMenuMapper menuMapper;

    @Resource
    private SubMenuMapper subMenuMapper;

    public List<ParentMenu> menu() {
        List<ParentMenu> parentMenus = this.menuMapper.selectAll();
        if (parentMenus.size() != 0) {
            for (ParentMenu parentMenu : parentMenus) {
                Integer parentId = parentMenu.getParentId();
                Example example = new Example(SubMenu.class);
                example.createCriteria().andEqualTo("subMenuId", parentId);
                List<SubMenu> layupSubMenus = this.subMenuMapper.selectByExample(example);
                parentMenu.setList(layupSubMenus);
            }
        }
        return parentMenus;
    }

}
