package com.example.enterprise.controller;




import ch.qos.logback.core.net.SMTPAppenderBase;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.example.enterprise.dto.LoginInput;
import com.example.enterprise.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //声明类UserController是一个控制器类，接收前端发来的数据
@RequestMapping("/user")
public class UserController {

    /*
     * 在控制器中定义业务逻辑层的UserService引用userService
     * 并让Spring注入子类型(UserServiceImpl)对象
     * 实现类UserServiceImpl的对象的实例化我们不管了，对象之间的关系我们不负责维护了，全部交给Spring来负责，这叫控制反转(IoC)
     * 引用userService需要指向接口UserService的子类型UserServiceImpl对象，此对象由Spring通过@Resource注解方式注入，这叫依赖注入(DI)
     * 即我们只需要定义引用userService引用，而其需要指向的子类型对象由Spring负责实例化和注入，这称为IoC+DI
     * */
    @Resource
    private UserService userService;


    @RequestMapping("/login") //形成【/user/login】的uri
    public String login(@Validated LoginInput loginInput) {
        //对前端传来的数据进行合法性检查：不能为null值
       //if (username == null || password == null) {
       //     return "用户名和密码是必须的，不能为null";
        //}
        //对前端传来的数据进行合法性检查：不能为空串值
        //if (username.equals("") || password.equals("")) {
          //  return "用户名和密码是必须的，不能为空";
        //}
        //其他规则的验证可以继续编写，此处省略...
        //在所有的数据合法性检查都完成后，调用业务逻辑层功能完成具体的登录业务逻辑
        boolean isValidUser = userService.login(loginInput.getUsername(), loginInput.getPassword());
        if (isValidUser == true) {



            StpUtil.login(loginInput.getUsername());
            return "合法用户，成功登录了！";
        }
        return "登录信息错误，登录失败！";
    }

    @GetMapping("/getdata")
    @SaCheckLogin //此注解表示需要登录后才能访问下面的函数
    public String getData() {
        return "data";
    }

    @GetMapping("/logout")
    @SaCheckLogin
    public void logout() {
        Object loginId = StpUtil.getLoginId();
        StpUtil.logout(loginId);
    }
}