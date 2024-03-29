package cn.wjqixige.travel.web.servlet;

import cn.wjqixige.travel.domain.ResultInfo;
import cn.wjqixige.travel.domain.User;
import cn.wjqixige.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        System.out.println("map=" + map);

        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        UserServiceImpl service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        //判断用户对象是否为null
        if (u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }

        //判断用户对象是否激活
        if (u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请登录邮箱进行激活！");
        }

        //判断登录成功
        if (u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u); //登记成功标记
            info.setFlag(true);
        }

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
