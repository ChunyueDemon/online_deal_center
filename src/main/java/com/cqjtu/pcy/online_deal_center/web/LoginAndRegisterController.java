package com.cqjtu.pcy.online_deal_center.web;

import com.cqjtu.pcy.online_deal_center.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginAndRegisterController {
    @Autowired
    LoginAndRegisterService loginAndRegisterService;

    @RequestMapping("register")
    public String register(String userName,String password,String email){
        boolean b = loginAndRegisterService.addUser(userName, password, email);
        if (b)
            return "redirect:login.html";
        else
            return "redirect:register.html";
    }

    @RequestMapping("existsByUserName")
    @ResponseBody
    public String existsByUserName(String userName){
        System.out.println(userName);
        if (loginAndRegisterService.existsByUserName(userName))
            return "true";
        else
            return "false";
    }

    @RequestMapping("existsByUserMail")
    @ResponseBody
    public String existsByUserMail(String userMail){
        System.out.println(userMail);
        if (loginAndRegisterService.existsByUserEmail(userMail))
            return "true";
        else
            return "false";
    }

    @RequestMapping("login")
    public String login(String userName, String password, ModelMap modelMap, HttpSession session){
        if (loginAndRegisterService.userNameMatchPassword(userName,password)){
            session.setAttribute("userName",userName);
            return "redirect:index.html";
        }
        else{
            modelMap.put("userName",userName);
            modelMap.put("info","用户名和密码不匹配");
            return "login";
        }

    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        try {
            session.removeAttribute("userName");
        }catch (Exception e){
            return "redirect:login.html";
        }
        return "redirect:login.html";
    }
}
