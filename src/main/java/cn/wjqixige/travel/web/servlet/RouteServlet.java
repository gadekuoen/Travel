package cn.wjqixige.travel.web.servlet;

import cn.wjqixige.travel.domain.PageBean;
import cn.wjqixige.travel.domain.Route;
import cn.wjqixige.travel.service.RouteService;
import cn.wjqixige.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;//类别id
        //2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0; //当前页码，如果不传递，默认第一页
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        int pageSize = 0; //每页显示条数，如果不传递，默认显示5条数据
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //3.调用service查询pageBean对象
        PageBean<Route> pb = service.pageQuery(cid, currentPage, pageSize, rname);

        //4.将pageBean对象序列化，返回
        writeValue(pb, response);
    }
}
