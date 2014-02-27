package com.bwzy.company.action;

import com.bwzy.company.service.URLService;
import com.bwzy.company.service.impl.LoginServicesImpl;
import com.bwzy.company.service.impl.URLServiceImpl;
import com.bwzy.company.service.oaservice.HttpRequestSender;
import com.bwzy.company.service.oaservice.impl.HttpRequestSenderImpl;
import com.bwzy.company.util.FilePathConfigUtil;
import com.bwzy.company.util.ParamsKeyUtil;
import com.bwzy.company.util.ResponseStrUtil;
import com.bwzy.mag.proces.config.WirelessConfig;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author lvyangjun
 *
 */
public class CheckCodeAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
    private String  verificationCodeImagesPath="";

	public void getCheckCode() {

		String msg = "";
		request = ServletActionContext.getRequest();
        response=ServletActionContext.getResponse();
        response.setContentType("image/jpeg");
		session = request.getSession();

        // ��ȡclient
        HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
        HttpClient client = httpRequestSender.getHttpClient(request);


        String verificationCodeURL = FilePathConfigUtil.getValue("verificationCode.url");
        HttpMethod method = new GetMethod(verificationCodeURL);
        File file=null;
        try {
            client.executeMethod(method);
            InputStream is = method.getResponseBodyAsStream();
            //��ȡ�����֤·��
            verificationCodeImagesPath=FilePathConfigUtil.getValue("verificationCodeImagesPath")
                    +session.getId()+".jpg";
            file=new File(verificationCodeImagesPath);
            OutputStream os=new FileOutputStream(file);
            byte buffer[]=new byte[4*1024];
            while((is.read(buffer))!=-1){
                os.write(buffer);
            }
            os.flush();
            if(!verificationCodeImagesPath.equals("")){
                ServletOutputStream out = null;
                InputStream in = null;
                byte []bytes = null;
                try {
                    response.setContentType( "multipart/form-data" );
                    //�õ������
                    out = response.getOutputStream();
                    in=new FileInputStream(file);
                    //����������ȡ���ݵ������
                    bytes = new byte [ 1024 ];
                    while ( -1 != in.read( bytes ) ) {
                        out.write( bytes );
                    }
                    //ǿ��ˢ�������
                    out.flush();
                } catch ( IOException e ) {
                    e.printStackTrace();
                } catch ( Exception e ) {
                    e.printStackTrace();
                } finally {
                    if ( in != null ) {

                        try {
                            in.close();
                        } catch ( IOException e ) {
                            e.printStackTrace();
                        }
                    }
                    if ( out != null ) try {
                        out.close();
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                    bytes = null;
                }
            }
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();

        } catch (SocketTimeoutException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            method.releaseConnection();
        }

	}
}
