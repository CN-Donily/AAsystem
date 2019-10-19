package cn.com.liud.aa.controller;


import cn.com.liud.aa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Donily
 * @since 2019-09-23
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 前往表单页面提交账单信息
     * @param model
     * @return
     */
    @RequestMapping("/sub")
    public String toLogin(Model model){
        List<String> userList=userService.getAllUserName();
        model.addAttribute("userList",userList);
        return "sub";
    }

}

