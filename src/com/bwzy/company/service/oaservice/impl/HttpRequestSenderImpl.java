package com.bwzy.company.service.oaservice.impl;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.bwzy.company.service.oaservice.HttpRequestSender;
import com.bwzy.company.util.ResponseStrUtil;
import com.bwzy.company.util.UrlConfigUtil;

/**
 * Created with IntelliJ IDEA. User: lvyangjun Date: 13-4-19 Time: 下午5:15 To
 * change this template use File | Settings | File Templates.
 */
public class HttpRequestSenderImpl implements HttpRequestSender {
	private String htmlSource = "";
	private HttpClient client;

	// 返回登录后的httpclient
	@Override
	public HttpClient getHttpClient(HttpServletRequest request) {
        HttpSession session=request.getSession();
		String sessionid = request.getSession().getId();
        Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute(sessionid);

        if(sessionMap==null){
            client = new HttpClient();
            HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
            httpRequestSender.getMethodResuest(client,
                        UrlConfigUtil.getValue("0000"));
            Map<String,Object> newMap=new HashMap<String, Object>();
            newMap.put("client",client);
            newMap.put("islogin",false);
            request.getSession().setAttribute(sessionid, newMap);
        }else{
            Boolean islogin= (Boolean) sessionMap.get("islogin");
            if (islogin) {
                client = (HttpClient)sessionMap.get("client");
            } else {
                client = (HttpClient)sessionMap.get("client");
                if (client == null) {
                    client = new HttpClient();
                    HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
                    httpRequestSender.getMethodResuest(client,
                            UrlConfigUtil.getValue("0000"));
                }
                Map<String,Object> newMap=new HashMap<String, Object>();
                newMap.put("client",client);
                newMap.put("islogin",false);
                request.getSession().setAttribute(sessionid, newMap);
            }
        }
		if (client != null) {
			// 设置响应超时
			HttpConnectionManagerParams managerParams = client
					.getHttpConnectionManager().getParams();
			// 设置连接超时时间
			managerParams.setConnectionTimeout(8000);
			// 设置数据读取超时时间
			managerParams.setSoTimeout(12000);
		}
		return client;
	}

	// 发送post请求，返回Html代码
	@Override
	public String postMethodRequest(HttpClient httpClient, String url,
			HttpServletRequest req) {
		return "";
	}

	// 发送get请求，发挥html代码
	@Override
	public String getMethodResuest(HttpClient httpclient, String url) {
        System.out.println(url);
        // 设置响应超时
		HttpConnectionManagerParams managerParams = httpclient
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间
		managerParams.setConnectionTimeout(8000);
		// 设置数据读取超时时间
		managerParams.setSoTimeout(12000);
		PostMethod method = new PostMethod(url);
        int status=0;
		try {
			status= httpclient.executeMethod(method);
			// 登陆后返回的HTMl
			htmlSource = method.getResponseBodyAsString();

		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_READTIMEOUT;
		} catch (IOException e) {
			e.printStackTrace();
			htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_FAILURE;
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_CHECKCODE;
		} finally {
			method.releaseConnection();
		}

        if(htmlSource.indexOf("<P>文档已经转移到 <A HREF=\"http://www.xfsx.gov.cn/timeout.jsp\">这里</A>.</P>")!=-1){
            htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT;
        }else if(htmlSource.indexOf("<P>文档已经转移到 <A HREF=\"http://www.xfsx.gov.cn/404.jsp\">这里</A>.</P>")!=-1){
            htmlSource = ResponseStrUtil.RESPONSE_RETURN_HTML_TIMEOUT;
        }
        return htmlSource; // To change body of implemented methods use File |
							// Settings | File Templates.
	}

	/**
	 * 将本地文件 上传至服务器
	 */
	@Override
	public String uploadFileToRemote(HttpClient httpClient, String url,
			String filepath, String filename, String index) {
		String responseStr = "";
		int status = 0;
		try {
			PostMethod filePost = new PostMethod(url);
			Part[] parts = { new FilePart("file1", new File(filepath)),
					new StringPart("file_txt", filepath),
					new StringPart("file1", filename) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts,
					filePost.getParams()));
			filePost.setRequestHeader("Content-type",
					"multipart/form-data; boundary=---------------------------7dd3d921087c");
			status = httpClient.executeMethod(filePost);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					filePost.getResponseBodyAsStream(), "UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("接受到的流是："
					+ new String(
							stringBuffer.toString().getBytes("ISO-8859-1"),
							"utf-8") + "—-" + status);
			int statuscode = filePost.getStatusCode();
			if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
					|| (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
					|| (statuscode == HttpStatus.SC_SEE_OTHER)
					|| (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
				// 读取新的 URL 地址
				Header[] header = filePost.getResponseHeaders();
				for (int i = 0; i < header.length; i++) {

					if (header[i] != null && header[i].getName().equals("url")) {
						responseStr = "<File Title='" + filename + "' Name='"
							+ header[i].getValue()
							+ "' State='add' FileTempPath='"
							+ header[i].getValue() + "' Index='" + index
							+ "'/>";
					}
				}
			} else {

				responseStr = "上传文件失败，可能该文件不支持上传";
			}
		} catch (Exception e) {
			throw new RuntimeException("error", e);

		}
		return responseStr;
	}
    /**
     * 将本地文件 上传至服务器
     */
    @Override
    public boolean  fromRemoteDownloadFile(HttpClient httpclient,String url,String filePath) {

        GetMethod httpGet = new GetMethod(url);
        try {
            client.executeMethod(httpGet);
            InputStream in = httpGet.getResponseBodyAsStream();
            FileOutputStream out = new FileOutputStream(new File(filePath));
            int BUFFER = 1024;
            byte[] b = new byte[BUFFER];
            int len = 0;
            while((len=in.read(b))!= -1){
                out.write(b,0,len);
            }
            in.close();
            out.close();

        }catch (HttpException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            httpGet.releaseConnection();
        }
        return true;
    }

}
