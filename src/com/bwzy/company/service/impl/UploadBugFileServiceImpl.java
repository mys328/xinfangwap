package com.bwzy.company.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.bwzy.company.util.FilePathConfigUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import com.broadwave.core.log.LogForJpa;
import com.bwzy.company.service.BaseService;
import com.bwzy.company.util.ParamsKeyUtil;
import com.bwzy.company.util.ResponseStrUtil;
import com.bwzy.mag.proces.config.WirelessConfig;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-8-22
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "uploadbugfileservice")
public class UploadBugFileServiceImpl implements BaseService {
    /**
     * 客户端Bug文件上传
     * @param params
     *
     * @return    上传是否成功
     */
    @Override
    public String process(Map<String,Object> params) {
        LogForJpa log=new LogForJpa();
        //文件名、文件类型、文件、文件存放路径
        String filename = (String) params.get("filename");
        String filetype = (String) params.get("suffix");
        String file = filename + "." + filetype;

        String filepath = FilePathConfigUtil.getValue("oa.dugFilePath");

        String responseStr="";
        HttpServletRequest req = (HttpServletRequest) params.get(ParamsKeyUtil.URL_PARAMS_KEY_HTTPRESQUEST);
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);

        boolean iscreate=false;
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = upload.parseRequest(req);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField()) {
                        //普通文本信息处理
                        String paramName = item.getFieldName();
                        String paramValue = item.getString();


                    } else {
                        //上传文件信息处理
                        byte[] data = item.get();
                        String Message=new String(data);
                        log.setId(UUID.randomUUID().toString());
                        log.setMessage(Message);
                        log.setHostname(req.getLocalAddr());
                        log.setTime(new Timestamp(new Date().getTime()));
                        log.setType("user_android");
                        Short serverity=10;
                        log.setServerity(serverity);
                        log.setFacility("安卓客户端");
                        String filePath =filepath+file;
                        FileOutputStream fos = new FileOutputStream(filePath);
                        fos.write(data);
                        fos.close();

                        iscreate=true;

                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(iscreate){
            responseStr= ResponseStrUtil.RESPONSE_RETURN_DUG_UPLOAD_SUCCESS;

        }else {
            responseStr=ResponseStrUtil.RESPONSE_RETURN_DUG_UPLOAD_FAILURE;
        }
        return responseStr;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
