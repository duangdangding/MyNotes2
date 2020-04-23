?package com.zcq.qiaosoft.Servlet;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/*
 * ?,2008.6.19 
 * 
 */


public class ToHtml extends HttpServlet
{
     private static final String CONTENT_TYPE = "text/html; charset=gbk";

//     Initialize global variables
     public void init() throws ServletException {
     }


//     Process the HTTP Get request
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws
     ServletException, IOException {
     response.setContentType(CONTENT_TYPE);
     service(request,response);
     }

 
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws
     ServletException, IOException {
     doGet(request, response);
     }
     public void destroy() {
     }
     public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
    	 response.setContentType(this.CONTENT_TYPE);
         String url="";
         String name="";
         ServletContext sc = getServletContext();
         
         String file_name=request.getParameter("file_name");
         String realName=request.getParameter("realName"); //?jsp?¦Ë
         //??jsp?,index.jsp
         //servlet??.http://localhost/toHtml?file_name=index
         url = "/"+realName+"/"+file_name+".jsp";
         //?HTMLjsp?,
         //http://localhost/index.jsp?§ß.
          String path=request.getParameter("path");
         String fileame = request.getRealPath("/")+path+"/";//?html??
         
         String wjname=request.getParameter("realPath");  //??
         
         name=fileame+wjname+".htm";
         System.out.println("file_name=="+file_name);
         System.out.println("name=="+name);
         System.out.println("url=="+url);
         //?html?,index.htm.
         RequestDispatcher rd = sc.getRequestDispatcher(url);
         final ByteArrayOutputStream os = new ByteArrayOutputStream();
         final ServletOutputStream stream = new ServletOutputStream()
         {
             public void write(byte[] data, int offset, int length)
             {
                 os.write(data, offset, length);
             }
             public void write(int b) throws IOException
             {
                 os.write(b);
             }
         };
         final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
         HttpServletResponse rep = new HttpServletResponseWrapper(response)
         {
             public ServletOutputStream getOutputStream()
             {
                 return stream;
             }
             public PrintWriter getWriter()
             {
                 return pw;
             }
         };
         rep.setContentType("text/html; charset=gbk");
         rd.include(request, rep);
         pw.flush();       
         FileOutputStream fos = new FileOutputStream(name);
         //jsp§Õxxx.htm
         os.writeTo(fos);
         fos.close();
         PrintWriter out=response.getWriter();
         out.print(" cheng gong ");
         out.println(fileame);
         out.close(); 
     }
