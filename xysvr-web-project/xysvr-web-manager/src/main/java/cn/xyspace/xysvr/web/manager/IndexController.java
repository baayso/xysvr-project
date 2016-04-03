/*
 * Copyright (c) 2014 Nanjing Xiaoyou Information Technology Co.,Ltd. All rights reserved.
 */

package cn.xyspace.xysvr.web.manager;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xyspace.xysvr.function.manager.user.entity.MgrMenu;
import cn.xyspace.xysvr.function.manager.user.entity.MgrUser;
import cn.xyspace.xysvr.function.manager.user.service.IMgrPermissionService;
import cn.xyspace.xysvr.function.manager.user.service.IMgrUserService;
import cn.xyspace.xysvr.function.manager.user.shiro.MgrShiroUser;
import cn.xyspace.xysvr.function.manager.user.web.bind.annotation.CurrentUser;

/**
 * 主页面控制器。
 * 
 * @author CaoZhongsheng
 * 
 * @since 1.0.0
 * 
 * @version 1.0.0
 *
 */
@Controller
public class IndexController {

    @Inject
    private IMgrPermissionService mgrPermissionService;

    @Inject
    private IMgrUserService mgrUserService;

    /**
     * 后台框架主页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/index")
    public String index(@CurrentUser MgrUser loginUser, Model model) {
        MgrShiroUser mgrShiroUser = (MgrShiroUser) SecurityUtils.getSubject().getPrincipal();

        Set<String> permissions = this.mgrUserService.findPermissions(mgrShiroUser.getUsername());

        List<MgrMenu> mymenus = this.mgrPermissionService.findMenus(permissions);

        model.addAttribute("mymenus", mymenus);
        model.addAttribute("admin", mgrShiroUser);

        return "index";
    }

    /**
     * iframe主页面。
     * 
     * @since 1.0.0
     * @version 1.0.0
     */
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

}
