package com.query.manual.fileupload;

import com.query.utils.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "UpLoadServlet", urlPatterns = "/uploadServlet")
public class UpLoadServlet extends HttpServlet {
    private String sessionId = "";
    private ServletContext application;
    private String uri;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        application = request.getSession().getServletContext();
        uri = application.getRealPath(request.getRequestURI());
        sessionId = request.getSession().getId();
        String fileName = uploadProcessing(request, response);
        System.out.println("filename===="+fileName);
        List<EmailRow> list = new ArrayList();
        try {
            list = ReadExcel.readExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                EmailRow emailRow = list.get(i);
                if (emailRow.getStatus() != null && emailRow.getStatus().equals("-1")) {
                    emailRow.setStatus("异常");
                    continue;
                }
                int result = CrawlerUtils.Net

                        (emailRow.getEmail());
                if (result > 3) {
                    emailRow.setStatus(String.valueOf(EmailStatus.正常));
                    System.out.println(emailRow.getEmail());
                } else {
                    list.get(i).setStatus(String.valueOf(EmailStatus.异常));
                }
                emailRow.setResultCount(result);
            }

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        //写入缓存
        WriteExcel.emailStatusWriteToExcel(list, uri.substring(0, uri.length() - 26) + "\\" + sessionId + ".xlsx");

        PrintWriter out = response.getWriter();
        out.print(jsonProcessing(list));
        out.close();

    }

    /**
     * json字符串处理
     *
     * @param list
     * @return
     */

    public String jsonProcessing(List<EmailRow> list) {
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("[");
        for (int i = 0; i < list.size(); i++) {
            jsonBuffer.append("{\"name\":\"");
            jsonBuffer.append(list.get(i).getEmail());
            jsonBuffer.append("\",\"number\":\"");
            jsonBuffer.append(list.get(i).getResultCount());
            jsonBuffer.append("\",\"safety\":\"");
            jsonBuffer.append(list.get(i).getStatus());
            jsonBuffer.append("\"},");

        }
        jsonBuffer.append("]");
        jsonBuffer.deleteCharAt(jsonBuffer.length() - 2);
        String jsonStr = jsonBuffer.toString();
        System.out.println(jsonStr);
        return jsonStr;
    }

    /**
     * 上传处理
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String uploadProcessing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "";
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1000);
        File file = new File("C:\\tempDirectory");
        factory.setRepository(file);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(1024 * 1024 * 5);
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                //  若是一般的表单域 打印信息
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString();
                    System.out.println(name + ": " + value);
                } else {
                    //若是文件域 把文件存储到d:\\files下
                    String filedName = item.getFieldName();// 表单名
                    fileName = item.getName(); //文件名字
                    String contentType = item.getContentType();
                    long sizeInBytes = item.getSize();//文件大小
                    InputStream in = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;

                    fileName = uri.substring(0, uri.length() - 26) + "\\" + sessionId + fileName;


                    System.out.println(fileName);
                    OutputStream out = new FileOutputStream(fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }

                    out.close();
                    in.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
