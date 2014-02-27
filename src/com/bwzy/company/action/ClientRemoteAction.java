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
 * android�ͻ��˷��ʵ�action
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
	private File image; // �ϴ����ļ�
	private String imageFileName; // �ļ�����
	private String imageContentType; // �ļ�����
	private Map<String, Object> map = null;

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */

	public void get() {
		map = getHttpCleint(true);
		MapToJson(map);
	}

	/**
	 * ע��
	 * 
	 * @return logout
	 */
	public void logout() {
		request = ServletActionContext.getRequest();
		session = request.getSession();
		session.removeAttribute(session.getId());
		session.invalidate();
		map=new HashMap<String, Object>();
		map.put("msg", "ע���ɹ�");
		MapToJson(map);
		
	}

	/**
	 * �ύ����
	 * 
	 * @return
	 */
	public void elter() {
		map = getHttpCleint(true);
		MapToJson(map);
	}

	/**
	 * δ��¼ҵ��
	 * 
	 * @return
	 */
	public void nologin() {
		map = getHttpCleint(false);
		MapToJson(map);
		
	}

	/**
	 * �ϴ��ļ�
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
		// ҵ����
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
		// ��ȡclient
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (uploadFile(filepath)) {
			if (client == null) {

				this.addActionMessage("��¼�ѳ�ʱ,�����µ�¼");
			} else {
				// ��ȡURL
				URLService urlService = new URLServiceImpl();
				String url = urlService.getURL(request, businessNUM);
				// ����
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
	 * ajax���� ��ȡ��ַ��Ϣ
	 */
	public void ajax() {

		String htmlSource = "";
		request = ServletActionContext.getRequest();
		// ҵ����
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);

		// ��ȡclient
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (client == null) {
			this.addActionMessage("��¼�ѳ�ʱ,�����µ�¼");

		} else {
			// ��ȡURL
			URLService urlService = new URLServiceImpl();
			String url = urlService.getURL(request, businessNUM);

			// ����
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
	 * ajax���� ��ȡ��ַ��Ϣ
	 */
	public void noLoginAjax() {
		String htmlSource = "";
		request = ServletActionContext.getRequest();
		// ҵ����
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);

		// ��ȡclient
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = httpRequestSender.getHttpClient(request);
		if (client == null) {
			this.addActionMessage("��¼�ѳ�ʱ,�����µ�¼");

		} else {
			// ��ȡURL
			URLService urlService = new URLServiceImpl();
			String url = urlService.getURL(request, businessNUM);

			// ����
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
			map.put("msg", "�͑��˳��r");
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
	 * ���ϴ��ļ����汾��
	 * 
	 * @param filepath
	 *            �ļ�����·��
	 *
	 *            �ļ�����
	 * @return �Ƿ񱣴�ɹ�
	 */
	private boolean uploadFile(String filepath) {
		boolean isupload = false;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			// �����ļ������
			fos = new FileOutputStream(filepath);
			// �����ļ��ϴ���
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
		// ҵ����
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
		// ��ȡclient
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		HttpClient client = null;
		client = httpRequestSender.getHttpClient(request);
		if (client == null) {

			this.addActionMessage("��¼�ѳ�ʱ,�����µ�¼");
		} else {
			map = remote(businessNUM, client);
		}
		return map;
	}

	// ����
	private Map<String,Object> remote(String businessNUM, HttpClient client) {
		// ��ȡURL
		URLService urlService = new URLServiceImpl();
		String url = urlService.getURL(request, businessNUM);
		// ����
		HttpRequestSender httpRequestSender = new HttpRequestSenderImpl();
		String htmlsource = httpRequestSender.getMethodResuest(client, url);
		// ��ȡ���ݲ���ת
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
