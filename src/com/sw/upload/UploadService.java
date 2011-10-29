package com.sw.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.*;


public class UploadService extends javax.servlet.http.HttpServlet implements
        javax.servlet.Servlet {

    public static final String UPLOAD_DIR = "/upload";
    public static final String DEFAULT_UPLOAD_FAILURE_URL = "page/uploadResult.jsp";

    public UploadService() {
        super();
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    /**
     * ���ļ�·����ȡ���ļ���
     */
    private String takeOutFileName(String filePath) {
        int pos = filePath.lastIndexOf(File.separator);
        if (pos > 0) {
            return filePath.substring(pos + 1);
        } else {
            return filePath;
        }
    }

    /**
     * ��request��ȡ��FileUploadStatus Bean
     */
    public static FileUploadStatus getStatusBean(
            HttpServletRequest request) {
        BeanControler beanCtrl = BeanControler.getInstance();
        return beanCtrl.getUploadStatus(request.getRemoteAddr());
    }

    /**
     * ��FileUploadStatus Bean���浽�������BeanControler
     */
    public static void saveStatusBean(
            HttpServletRequest request,
            FileUploadStatus statusBean) {
        statusBean.setUploadAddr(request.getRemoteAddr());
        BeanControler beanCtrl = BeanControler.getInstance();
        beanCtrl.setUploadStatus(statusBean);
    }

    /**
     * ɾ���Ѿ��ϴ����ļ�
     */
    private void deleteUploadedFile(HttpServletRequest request) {
        FileUploadStatus satusBean = getStatusBean(request);
        for (int i = 0; i < satusBean.getUploadFileUrlList().size(); i++) {
            File uploadedFile = new File(request.getRealPath(UPLOAD_DIR) +
                                         File.separator +
                                         satusBean.getUploadFileUrlList().
                                         get(i));
            uploadedFile.delete();
        }
        satusBean.getUploadFileUrlList().clear();
        satusBean.setStatus("ɾ�����ϴ����ļ�");
        saveStatusBean(request, satusBean);
    }

    /**
     * �ϴ�����г��?��
     */
    private void uploadExceptionHandle(
            HttpServletRequest request,
            String errMsg) throws ServletException, IOException {
        //����ɾ���Ѿ��ϴ����ļ�
        deleteUploadedFile(request);
        FileUploadStatus satusBean = getStatusBean(request);
        satusBean.setStatus(errMsg);
        saveStatusBean(request, satusBean);
    }

    /**
     * ��ʼ���ļ��ϴ�״̬Bean
     */
    private FileUploadStatus initStatusBean(HttpServletRequest
            request) {
        FileUploadStatus satusBean = new FileUploadStatus();
        satusBean.setStatus("����׼������");
        satusBean.setUploadTotalSize(request.getContentLength());
        satusBean.setProcessStartTime(System.currentTimeMillis());
        satusBean.setBaseDir(request.getContextPath() + UPLOAD_DIR);
        return satusBean;
    }

    /**
     * �����ļ��ϴ�
     */
    private void processFileUpload(HttpServletRequest request,
                                   HttpServletResponse response) throws
            ServletException, IOException {
    	String togetherName="";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //�����ڴ滺�������д����ʱ�ļ�
        factory.setSizeThreshold(10240000);
        //������ʱ�ļ��洢λ��
        factory.setRepository(new File(request.getRealPath("/upload/temp")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        //���õ����ļ�������ϴ�ֵ
        upload.setFileSizeMax(409600000);
        //�������request�����ֵ
        upload.setSizeMax(409600000);
        upload.setProgressListener(new FileUploadListener(request));
        //�����ʼ�����FileUploadStatus Bean
        saveStatusBean(request, initStatusBean(request));

        String forwardURL = "";
        try {
            List items = upload.parseRequest(request);
            //��÷���url
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                if (item.isFormField()) {
                    forwardURL = item.getString();
                    break;
                }
            }
            //�����ļ��ϴ�
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);

                //ȡ���ϴ�
                if (getStatusBean(request).getCancel()) {
                    deleteUploadedFile(request);
                    break;
                }
                //�����ļ�
                else if (!item.isFormField() && item.getName().length() > 0) {
                    String fileName = takeOutFileName(item.getName());
                    
                    togetherName  = togetherName+fileName+";";
                    File uploadedFile = new File(request.getRealPath(UPLOAD_DIR) +
                                                 File.separator + fileName);
                    item.write(uploadedFile);
                    //�����ϴ��ļ��б�
                    FileUploadStatus satusBean =
                            getStatusBean(request);
                    satusBean.getUploadFileUrlList().add(fileName);
                    saveStatusBean(request, satusBean);
                    Thread.sleep(500);
                }
            }

        } catch (FileUploadException e) {
            uploadExceptionHandle(request, "�ϴ��ļ�ʱ�������:" + e.getMessage());
        } catch (Exception e) {
            uploadExceptionHandle(request, "�����ϴ��ļ�ʱ�������:" + e.getMessage());
        }
        if (forwardURL.length() == 0) {
            forwardURL = DEFAULT_UPLOAD_FAILURE_URL;
        }
        
        if(togetherName !=null && togetherName.length()>0 )
        		togetherName = togetherName.substring(0, togetherName.length()-1);
        //response.getWriter().write("{fileName:togetherName}");
        request.setAttribute("fileName", togetherName);
        request.getRequestDispatcher(forwardURL).forward(request, response);
    }

    /**
     * ��Ӧ�ϴ�״̬��ѯ
     */
    private void responseStatusQuery(HttpServletRequest request,
                                              HttpServletResponse response) throws
            IOException {
        response.setContentType("text/xml");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Type","text/html;charset=utf-8");
        FileUploadStatus satusBean = getStatusBean(request);
        response.getWriter().write(satusBean.toJSon());
    }

    /**
     * ����ȡ���ļ��ϴ�
     */
    private void processCancelFileUpload(HttpServletRequest request,
                                         HttpServletResponse response) throws
            IOException {
        FileUploadStatus satusBean = getStatusBean(request);
        satusBean.setCancel(true);
        saveStatusBean(request, satusBean);
        responseStatusQuery(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,
            IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //System.out.println(isMultipart);
        if (isMultipart) {
            processFileUpload(request, response);
        } else {
            request.setCharacterEncoding("GBK");

            if (request.getParameter("uploadStatus") != null) {
                responseStatusQuery(request, response);
            }
            if (request.getParameter("cancelUpload") != null) {
                processCancelFileUpload(request, response);
            }

        }
    }
}
