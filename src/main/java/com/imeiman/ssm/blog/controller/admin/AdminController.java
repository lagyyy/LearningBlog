package com.imeiman.ssm.blog.controller.admin;

import com.imeiman.ssm.blog.domain.dto.UserLoginDTO;
import com.imeiman.ssm.blog.domain.entity.User;
import com.imeiman.ssm.blog.service.UserService;
import com.imeiman.ssm.blog.util.BeanCopyUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.imeiman.ssm.blog.util.MyUtils.getIpAddr;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/23
 * Time: 21:13
 * Description:
 */

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String
    loginPage() {
        return "Admin/login";
    }

    @RequestMapping("/admin")
    public String indexPage() {
        return "Admin/index";
    }


    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST, produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("__________________________________Login___________________________________________");
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User userByNameOrEmail = userService.getUserByNameOrEmail(username);
        System.out.println(userByNameOrEmail);
        if (userByNameOrEmail==null){
            map.put("code", 0);
            map.put("msg", "用户名无效！");
        } else if (!userByNameOrEmail.getUserPass().equals(password)) {
            map.put("code", 0);
            map.put("msg", "账号或密码错误！");
        }else if(userByNameOrEmail.getUserStatus() == 0){
            map.put("code", 0);
            map.put("msg", "账号已被禁用！");
        }else {
            //登录成功
            map.put("code", 1);
            map.put("msg", "登录成功");
            //添加session
            request.getSession().setAttribute("user", userByNameOrEmail);
            //添加cookie
            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            userByNameOrEmail.setUserLastLoginTime(new Date());
            userByNameOrEmail.setUserLastLoginIp(getIpAddr(request));

            UserLoginDTO userLoginDTO = new UserLoginDTO();
            userLoginDTO.setUserLastLoginTime(new Date());
            userLoginDTO.setUserLastLoginIp(getIpAddr(request));
            userLoginDTO.setUserId(userByNameOrEmail.getUserId());

            User user = BeanCopyUtils.copyBean(userLoginDTO, User.class);
            Integer integer = userService.updateUser(user);
            System.out.println(integer);
            System.out.println(map.get("code"));
        }
        String result = new JSONObject(map).toString();
        return result;
    }

}
