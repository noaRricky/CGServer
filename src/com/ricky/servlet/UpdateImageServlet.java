package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.util.ImageHelper;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UpdateImageServlet extends HttpServlet {

    private static final String IMAGE_BASE_PATH = "D:\\Project\\apache-tomcat-9.0.0.M26\\webapps\\image\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建文件工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置文件上传路径
        File file = new File(IMAGE_BASE_PATH);

        String tempPath = req.getSession().getServletContext().getRealPath("\\WEB-INF\\temp");

        //设置缓冲区大小为1M
        factory.setSizeThreshold(1024 * 1024);
        //设置临时文件夹为temp
        factory.setRepository(new File(tempPath));
        // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析结果放在list
        try {
            List<FileItem> list = upload.parseRequest(new ServletRequestContext(req));

            for (FileItem item : list) {
                String name = item.getFieldName();
                InputStream is = item.getInputStream();

                if (name.contains("content")) {
                    inputStream2String(is);
                } else if (name.contains("file")) {
                    try {
                        inputStream2File(is, upload + "\\" + item.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", true);
        resp.getWriter().println(jsonObj.toString());
    }

    private String inputStream2String(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

    private void inputStream2File(InputStream is, String savePath)
    throws FileNotFoundException, IOException{
        System.out.println("文件保存路径为：" + savePath);
        File file = new File(savePath);
        InputStream inputStream = is;
        BufferedInputStream fis = new BufferedInputStream(inputStream);
        FileOutputStream fos = new FileOutputStream(file);
        int f;
        while ((f = fis.read()) != -1) {
            fos.write(f);
        }
        fos.flush();
        fos.close();
        fis.close();
        inputStream.close();
    }
}
