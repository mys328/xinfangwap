package com.bwzy.company.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.bwzy.company.service.VerificationCodeService;
import com.bwzy.company.tostring.TestOCR;
import com.bwzy.mag.proces.config.WirelessConfig;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-7-24
 * Time: ����12:16
 * To change this template use File | Settings | File Templates.
 */
public class VerificationCodeServiceImpl implements VerificationCodeService {
    /**
     *  ��ȡ��ҳ��֤�벢����֤��ʶ��Ϊ�ַ���
     * @param httpClient
     * @param params
     * @return  ��֤��
     * @throws IOException
     */
	private String  verificationCodeImagesPath="";
    @Override
    public String getVerificationCode(HttpClient httpClient,String sessionid){
        //������֤��ͼƬ������
        String verificationCodeURL = WirelessConfig.getString("verificationCode.url");
        HttpMethod method = new GetMethod(verificationCodeURL);
        File file=null;
        try {
        	httpClient.executeMethod(method);
            InputStream is = method.getResponseBodyAsStream();
            //��ȡ�����֤·��
             verificationCodeImagesPath=WirelessConfig.getString("verificationCodeImagesPath")
                    +sessionid+".jpg";
            file=new File(verificationCodeImagesPath);
            OutputStream os=new FileOutputStream(file);
            byte buffer[]=new byte[4*1024];
            while((is.read(buffer))!=-1){
                os.write(buffer);
            }
            os.flush();
            Thread.sleep(1000);
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            //htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT;
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            //htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_READTIMEOUT;
        } catch (IOException e) {
            e.printStackTrace();
            //htmlSource= ResponseStrUtil.RESPONSE_RETURN_HTML_FAILURE;
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            method.releaseConnection();
        } 
        //��ͼƬת��String
        TestOCR testOCR=new TestOCR();
        String img=testOCR.toString(verificationCodeImagesPath);
        /*//���꽫��֤��ͼƬɾ��
        if(file.exists()){
            file.delete();
        }*/
        //������֤���������
        if(img.indexOf("U")!=-1){
            img=img.replaceAll("U", "0");
        }
        if(img.indexOf("D")!=-1){
            img=img.replaceAll("D", "0");
        }
        if(img.indexOf("i")!=-1){
            img=img.replaceAll("i", "1");
        }
        if(img.indexOf("_")!=-1){
            img=img.replaceAll("_", "");
        }
        if(img.indexOf("-")!=-1){
            img=img.replaceAll("-", "");
        }
        if(img.indexOf(" ")!=-1){
            img=img.replaceAll(" ", "");
        }

        if(img.indexOf("S")!=-1){
            img=img.replaceAll("S", "5");
        }
        if(img.indexOf(".")!=-1){
            img=img.replaceAll(".","");
        }
        if(img.indexOf("[")!=-1){
            img=img.replaceAll("\\[","1");
        }
        if(img.indexOf("Z")!=-1){
        	img=img.replaceAll("Z", "2");
        }
        
        return img;
    }
}
