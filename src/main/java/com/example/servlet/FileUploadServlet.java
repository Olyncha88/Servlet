package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "fileUploadServlet", value = "/fileUpload")
public class FileUploadServlet extends HttpServlet {
    static final int FILE_MAX_SIZE = 100 * 1024;
    static final int MEM_MAX_SIZE = 100 * 1024;
    static int COUNTER = 0;

    private String filePath = "C:\\Users\\HP\\IdeaProjects\\Servlet\\files\\";
    private File file;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String docType = "<!DOCTYPE html>";
        String title = "File Uploading Demo";
        PrintWriter writer = response.getWriter();
        COUNTER++;
        request.getSession().setAttribute("Counter", COUNTER);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(MEM_MAX_SIZE);
        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(FILE_MAX_SIZE);
        try {
            List <FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
            Iterator <FileItem> iterator = fileItems.iterator();
            writer.println(docType +
                    "<html>" +
                    "<head>" +
                    "<title>" + title + "</title>" +
                    "</head>" +
                    "<body>");
            while (iterator.hasNext()) {
                FileItem fileItem = iterator.next();
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(file);
                    writer.println(fileName + " is uploaded.<br>");
                }
            }
            writer.println("</body>" +
                    "</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


