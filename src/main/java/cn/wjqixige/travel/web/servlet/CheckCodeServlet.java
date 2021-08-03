package cn.wjqixige.travel.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 */
@WebServlet("/checkCode")
public class CheckCodeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //告知浏览器不缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //内存中创建一个80x30的图片，默认黑色背景
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0,width,height);

        //产生四个随机验证码
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体大小
        g.setFont(new Font("黑体", Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);
        //将内存中的图片输出到浏览器
        ImageIO.write(image,"PNG",response.getOutputStream());
    }


    /**
     * 产生四位随机字符串
     */
    private String getCheckCode(){
        String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int size = base.length();
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = random.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            buffer.append(c);
        }

        return buffer.toString();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }
}
