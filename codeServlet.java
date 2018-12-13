package com.big3.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet("/getCode")
public class codeServlet extends HttpServlet {
//    序列化
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        调用工具生成的验证码和图片
        Map<String,Object> codeMap = codeUtil.generateCodeAndPic();
//        将验证码保存在session中
        HttpSession session = request.getSession();
        session.setAttribute("code",codeMap.get("code".toString()));

//        禁止缓存图像
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",-1);

        response.setContentType("image/jpg");

//        将验证码图像输出到servlet输出流中
        ServletOutputStream servletOutputStream;
        servletOutputStream = response.getOutputStream();
        ImageIO.write((RenderedImage)codeMap.get("codeImage"),"jpg",servletOutputStream);
        servletOutputStream.close();

    }

}
