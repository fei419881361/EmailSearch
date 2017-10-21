package com.query.manual.servelt;

import com.query.utils.CrawlerUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "manualQueryServlet", urlPatterns = "/manualQueryServlet")
public class ManualQueryServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //searchBox jsp页面传的邮箱号
        String searchBox = request.getParameter("searchBox");

        String name = searchBox;
        int num = CrawlerUtils.Net(searchBox);
        String number = String.valueOf(num);
        String safety = "";
        if (num > 3)
            safety = "正常";
        else
            safety = "异常";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\"name\":\"" + name + "\"," +
                "\"number\":\"" + number + "\"," +
                "\"safety\":\"" + safety + "\"}";
        PrintWriter out = response.getWriter();
       // System.out.println(jsonStr);
        out.print(jsonStr);
        out.close();


    }
}
