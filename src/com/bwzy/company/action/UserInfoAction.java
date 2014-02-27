package com.bwzy.company.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwzy.company.util.FilePathConfigUtil;
import com.bwzy.company.util.RemoteUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.bwzy.company.service.URLService;
import com.bwzy.company.service.impl.URLServiceImpl;
import com.bwzy.company.service.oaservice.HttpRequestSender;
import com.bwzy.company.service.oaservice.ParserHtmlSourceService;
import com.bwzy.company.service.oaservice.impl.HttpRequestSenderImpl;
import com.bwzy.company.util.ParamsKeyUtil;
import com.bwzy.company.util.ServiceUtil;
import com.bwzy.mag.proces.config.WirelessConfig;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpSession session;
	String msg = "";
	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型
	private Map<String, Object> map = null;
    private String filepath;

    /**
	 * 获取数据
	 * 
	 * @return
	 */

	public String get() {
		map = getHttpCleint();
		// 登录超时
		if (map != null) {
			msg = (String) map.get("msg");
		}

		return msg;
	}
    /**
     *       下载文件
     *
     * @return
     */

    public void download() {
        request = ServletActionContext.getRequest();
        HttpServletResponse response=ServletActionContext.getResponse();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        session = request.getSession();
        // 业务编号
        String businessNUM = request
                .getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
        // 获取client
        HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
        HttpClient client = null;
        client = httpRequestSender.getHttpClient(request);
        if (client == null) {
            this.addActionMessage("登录已超时,请重新登录");
            msg = "timeout";
        } else {
            // 获取URL
            URLService urlService = new URLServiceImpl();
            String url = urlService.getURL(request, businessNUM);
            String filename=request.getParameter("fileName");
            String filepath = FilePathConfigUtil.getValue("oa.filePath")
                    +filename;
            if(httpRequestSender.fromRemoteDownloadFile(client,url,filepath)){
                File downloadFile=new File(filepath);
                if (downloadFile.exists()) {
                    response.setContentType("application/octet-stream");
                    Long length=downloadFile.length();
                    response.setContentLength(length.intValue());
                    response.addHeader("Content-Disposition", "attachment; filename=" + filename);
                    try {
                        FileInputStream inputStream = new FileInputStream(filepath);
                        ServletOutputStream sopt=response.getOutputStream();
                        int b = 0;
                        byte[] buffer = new byte[1024];
                        while (b != -1){
                            b = inputStream.read(buffer);
                            //4.写到输出流(out)中
                            sopt.write(buffer,0,b);
                        }
                        inputStream.close();
                        sopt.close();
                        sopt.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {

                }
            }
        }
    }
	/**
	 * 注销
	 * 
	 * @return logout
	 */
	public String logout() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		session.removeAttribute(session.getId());
		session.invalidate();
		this.addActionMessage("注销成功");
		return "logout";
	}

    /**
     *       写信
     *
     *       * @return
     */
    public String edit() {
        request = ServletActionContext.getRequest();
        session = request.getSession();
        String sessionId=session.getId();
       Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute(sessionId);
       Boolean islogin= (Boolean) sessionMap.get("islogin");
       if (islogin){
           return "editletter";
       }else {
           return "timeout";
       }

    }
    /**
     *      修改密码
     *
     */
    public String password() {
        request = ServletActionContext.getRequest();
        session = request.getSession();
        String sessionId=session.getId();
        Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute(sessionId);
        Boolean islogin= (Boolean) sessionMap.get("islogin");
        if (islogin){
            return "password";
        }else {
            return "timeout";
        }

    }
	/**
	 * 提交数据
	 * 
	 * @return
	 */
	public String elter() {

		map = getHttpCleint();
		if (map != null) {
			if (map.get("responseStr") != null) {
				msg = (String) map.get("msg");
				this.addActionMessage((String) map.get("responseStr"));
			} else {
				msg = (String) map.get("msg");
			}
		}

		return msg;
	}

	/**
	 * 未登录业务
	 * 
	 * @return
	 */
	public String nologin() {
		map = getHttpCleint();
		if (map != null) {
			if (map.get("responseStr") != null) {
				msg = (String) map.get("msg");
				this.addActionMessage((String) map.get("responseStr"));
			} else {
				msg = (String) map.get("msg");
			}
		}

		return msg;
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	public void upload() {

		request = ServletActionContext.getRequest();
		session = request.getSession();
		String filepath = FilePathConfigUtil.getValue("oa.filePath")
				+ getImageFileName();
		String filename = getImageFileName();
		String index = request.getParameter("index");
		// 业务编号
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
		// 获取client
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (uploadFile(filepath)) {
			if (client == null) {
				this.addActionMessage("登录已超时,请重新登录");
				msg = "timeout";
			} else {
				// 获取URL
				URLService urlService = new URLServiceImpl();
				String url = urlService.getURL(request, businessNUM);
				// 请求
				String htmlsource = httpRequestSender.uploadFileToRemote(
						client, url, filepath, filename, index);

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/xml;charset=utf-8");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println(htmlsource);
				out.flush();
				out.close();

			}
		}
	}

	/**
	 * ajax请求 获取地址信息
	 */
	public void ajax() {

		String htmlSource = "";
		request = ServletActionContext.getRequest();
		session = request.getSession();
		// 业务编号
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);

		// 获取client
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (client == null) {
			this.addActionMessage("登录已超时,请重新登录");

		} else {
			// 获取URL
			URLService urlService = new URLServiceImpl();
			String url = urlService.getURL(request, businessNUM);

			// 请求
			htmlSource = httpRequestSender.getMethodResuest(client, url);
		}
		ajaxResponse(htmlSource);

	}

	/**
	 * ajax请求 获取地址信息
	 */
	public void noLoginAjax() {

		String htmlSource = "";
		request = ServletActionContext.getRequest();
		session = request.getSession();
		// 业务编号
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);

		// 获取client
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (client == null) {
			this.addActionMessage("登录已超时,请重新登录");

		} else {
			// 获取URL
			URLService urlService = new URLServiceImpl();
			String url = urlService.getURL(request, businessNUM);

			// 请求
			htmlSource = httpRequestSender.getMethodResuest(client, url);
		}
		ajaxResponse(htmlSource);

	}

	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * 将上传文件保存本地
	 * 
	 * @param filepath
	 *            文件保存路径
	 *
	 *            文件名称
	 * @return 是否保存成功
	 */
	private boolean uploadFile(String filepath) {
		boolean isupload = false;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			// 建立文件输出流
			fos = new FileOutputStream(filepath);
			// 建立文件上传流
			fis = new FileInputStream(getImage());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			isupload = true;
		} catch (Exception e) {

			isupload = false;
			e.printStackTrace();
		} finally {
			close(fos, fis);
		}
		return isupload;
	}

	private void ajaxResponse(String htmlSource) {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-control", "no-cache");
		response.setHeader("pragma", "no-cache");
		// 拿到一个输出的对象
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 格式化输出
			OutputFormat oFormat = new OutputFormat();
			oFormat.setEncoding("utf-8");
			XMLWriter xmlWriter = new XMLWriter(out);
			xmlWriter.write(htmlSource);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			out.flush();
			out.close();
		}

	}

	private Map<String, Object> getHttpCleint() {
		request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session = request.getSession();
		// 业务编号
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
		// 获取client
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = null;
		client = httpRequestSender.getHttpClient(request);
		if (client == null) {

			this.addActionMessage("登录已超时,请重新登录");
			msg = "timeout";
		} else {
			map = remote(businessNUM, client);
		}
		return map;
	}

	// 访问
	private Map<String,Object> remote(String businessNUM, HttpClient client) {
		// 获取URL
		URLService urlService = new URLServiceImpl();
		String url = urlService.getURL(request, businessNUM);
		// 请求
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		String htmlsource = httpRequestSender.getMethodResuest(client, url);
		// 获取数据并跳转
		ServiceUtil serviceUtil = new ServiceUtil();
		ParserHtmlSourceService parser = serviceUtil
				.getPraserService(businessNUM);
        RemoteUtil remoteUtil=new RemoteUtil();
        boolean isremote=remoteUtil.isRemoteSucc(htmlsource);

        if(remoteUtil.isRemoteSucc(htmlsource)==false){
            map=new HashMap<String, Object>();
            map.put("msg","timeout");

        }else {

            map = parser.parserSource(htmlsource);
        }
		return map;

	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
