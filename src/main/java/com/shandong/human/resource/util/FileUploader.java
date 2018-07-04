package com.shandong.human.resource.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传工具类
 * 主要包括：
 * 1.实现单文件上传
 * 2.实现多个文件的上传
 *
 * @author tyee.noprom@qq.com
 * @time 2/11/16 8:14 AM.
 */
public class FileUploader {

    private String allowSuffix = "jpg,png,gif,jpeg";//允许文件格式
    private long allowSize = 2L;//允许文件大小
    private String fileName;//文件名
    private String[] fileNames;//文件名

    public String getAllowSuffix() {
        return allowSuffix;
    }

    public void setAllowSuffix(String allowSuffix) {
        this.allowSuffix = allowSuffix;
    }

    public long getAllowSize() {
        return allowSize * 1024 * 1024;
    }

    public void setAllowSize(long allowSize) {
        this.allowSize = allowSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    /**
     * 获得文件保存的名字
     *
     * @return 新的文件名
     */
    private String getNewFileName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(new Date());
    }

    /**
     * 多文件上传
     *
     * @param files   多个文件
     * @param destDir 目标存放路径
     * @param request request
     * @throws Exception Exception
     */
    public boolean uploads(MultipartFile[] files, String destDir, HttpServletRequest request) throws Exception {
        boolean success = true;
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            fileNames = new String[files.length];
            int index = 0;
            for (MultipartFile file : files) {
                // 获得文件后缀名
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = getAllowSuffix().indexOf(suffix);
                // 检测上传的文件的文件类型
                if (length == -1) {
                    throw new Exception("请上传允许格式的文件");
                }
                // 检测上传的文件的文件大小
                if (file.getSize() > getAllowSize()) {
                    throw new Exception("请上传的文件的大小已经超出范围");
                }
                // 根目录
                String rootDir = request.getSession().getServletContext().getRealPath("/");
                File destFile = new File(rootDir + destDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String newFileName = getNewFileName() + "." + suffix;
                File f = new File(destFile.getAbsoluteFile() + File.separator + newFileName);
                file.transferTo(f);
                f.createNewFile();
                fileNames[index++] = newFileName;
            }
        } catch (Exception e) {
            success = false;
            throw e;
        }
        return success;
    }

    /**
     * 上传文件
     *
     * @param file    要上传的文件
     * @param destDir 目标上传地址
     * @param request request
     * @throws Exception
     */
    public boolean upload(MultipartFile file, String destDir, HttpServletRequest request) throws Exception {
        boolean success = true;
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        try {
            // 获得文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = getAllowSuffix().indexOf(suffix);
            // 检测上传的文件的文件类型
            if (length == -1) {
                throw new Exception("请上传允许格式的文件");
            }
            // 检测上传的文件的文件大小
            if (file.getSize() > getAllowSize()) {
                throw new Exception("您上传的文件大小已经超出范围");
            }

            // 根目录
            String rootDir = request.getSession().getServletContext().getRealPath("/");
            File destFile = new File(rootDir + destDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String newFileName = getNewFileName() + "." + suffix;
            File f = new File(destFile.getAbsolutePath() + File.separator + newFileName);
            file.transferTo(f);
            f.createNewFile();
            fileName = newFileName;
        } catch (Exception e) {
            success = false;
            throw e;
        }
        return success;
    }
}
