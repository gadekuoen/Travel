package cn.wjqixige.travel.web.servlet;

import cn.wjqixige.travel.domain.Category;
import cn.wjqixige.travel.service.CategoryService;
import cn.wjqixige.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Category> cs = service.findAll();
        writeValue(cs,response);
    }
}
