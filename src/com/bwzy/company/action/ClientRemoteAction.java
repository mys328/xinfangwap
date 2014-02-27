package com.bwzy.company.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bwzy.company.util.FilePathConfigUtil;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.struts2.ServletActionContext;

import com.bwzy.company.service.URLService;
import com.bwzy.company.service.impl.URLServiceImpl;
import com.bwzy.company.service.oaservice.HttpRequestSender;
import com.bwzy.company.service.oaservice.ParserHtmlSourceService;
import com.bwzy.company.service.oaservice.impl.HttpRequestSenderImpl;
import com.bwzy.company.util.ParamsKeyUtil;
import com.bwzy.company.util.ServiceUtil;
import com.bwzy.company.util.XmlAndJsonUtil;
import com.bwzy.mag.proces.config.WirelessConfig;
import com.opensymphony.xwork2.ActionSupport;

/**
 * android客户端访问的action
 * 
 * @author lvyangjun
 *
 */
public class ClientRemoteAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpSession session;
	HttpServletResponse response;  
	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型
	private Map<String, Object> map = null;

	/**
	 * 获取数据
	 * 
	 * @return
	 */

	public void get() {
		map = getHttpCleint(true);
		MapToJson(map);
	}

	/**
	 * 注销
	 * 
	 * @return logout
	 */
	public void logout() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		session.removeAttribute(session.getId());
		session.invalidate();
		map=new HashMap<String, Object>();
		map.put("msg", "注销成功");
		MapToJson(map);
		
	}

	/**
	 * 提交数据
	 * 
	 * @return
	 */
	public void elter() {
		map = getHttpCleint(true);
		MapToJson(map);
	}

	/**
	 * 未登录业务
	 * 
	 * @return
	 */
	public void nologin() {
		map = getHttpCleint(false);
		MapToJson(map);
		
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
		String json=XmlAndJsonUtil.xml2JSON(htmlSource);
		JsonResponse(json);
		
	}
	private void JsonResponse(String json){
		response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");
		try {
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.getOutputStream().write(jsonBytes);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	/**
	 * ajax请求 获取地址信息
	 */
	public void noLoginAjax() {
		String htmlSource = "";
		request = ServletActionContext.getRequest();
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
		String json=XmlAndJsonUtil.xml2JSON(htmlSource);
		JsonResponse(json);
	}

	
	private void MapToJson(Map<String,Object> map){
		response=ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");
		if(map==null){
			map = new HashMap<String, Object>();
			map.put("msg", "客戶端超時");
		}
		String json=JSONObject.fromObject(map).toString();
		try {
			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.getOutputStream().write(jsonBytes);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
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
	private Map<String, Object> getHttpCleint(boolean islogin) {
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
		return map = parser.parserSource(htmlsource);

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
	
}
