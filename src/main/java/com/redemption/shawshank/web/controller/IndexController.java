package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.Constants;
import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.utils.annotation.CurrentUser;
import com.redemption.shawshank.web.service.inter.ResourceService;
import com.redemption.shawshank.web.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * author : xingshukui
 * date : 2017/7/6
 * desc :
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(HttpServletRequest req, Model model) {
        User loginUser = (User) req.getAttribute(Constants.CURRENT_USER);
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<SysResource> menus = resourceService.findmenus(permissions);
        model.addAttribute("menus", menus);
        model.addAttribute("user",loginUser);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


}
