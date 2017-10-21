package com.query.manual.download;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "downloadServlet",urlPatterns = "/downloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getSession().getId();
		response.setContentType("application/x-msdownload");
		String fileName = "outputFile.xlsx";
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		OutputStream out = response.getOutputStream();
		System.out.println("download:"+sessionId);

		ServletContext application = request.getSession().getServletContext();
		String uri = application.getRealPath(request.getRequestURI()) ;
		System.out.println("--->"+uri);
		String uriR =uri.substring(0,uri.length()-28);

		String downloadFileName = uriR+"\\"+sessionId+".xlsx";
		System.out.println(downloadFileName);
		InputStream in = new FileInputStream(downloadFileName);
		byte [] buffer = new byte[1024];
		int len = 0;
		while((len = in.read(buffer)) != -1){
			out.write(buffer, 0, len);
		}

		in.close();
		File file = new File(uriR+"\\"+sessionId+".xlsx");
		if (file.exists())
			file.delete();

	}

}
