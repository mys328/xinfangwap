package com.bwzy.company.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.struts2.ServletActionContext;

import com.bwzy.company.service.URLService;
import com.bwzy.company.service.impl.LoginServicesImpl;
import com.bwzy.company.service.impl.URLServiceImpl;
import com.bwzy.company.util.ParamsKeyUtil;
import com.bwzy.company.util.ResponseStrUtil;
import com.opensymphony.xwork2.ActionSupport;

/***
 * 
 * @author lvyangjun
 *
 */
public class LoginValidatorAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletResponse response;
	public String login() {
		String msg = "";
		request = ServletActionContext.getRequest();
		session = request.getSession();
		// ҵ����
		String businessNUM = request
				.getParameter(ParamsKeyUtil.URL_PARAMS_KEY_BUSINESSNUM);
        Map<String,Object> sessionMap= (Map<String, Object>) session.getAttribute(session.getId());
		HttpClient client =null;
		client= (HttpClient) sessionMap.get("client");
		// ������Ӧ��ʱ
		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();
		// �������ӳ�ʱʱ��
		managerParams.setConnectionTimeout(8000);
		// �������ݶ�ȡ��ʱʱ��
		managerParams.setSoTimeout(12000);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("client",client);
		// ��ȡURL
		URLService urlService = new URLServiceImpl();
		String url = urlService.getURL(request, businessNUM);
		// ��¼����
		LoginServicesImpl loginServices = new LoginServicesImpl();
		String responseStr = loginServices.OAlogin(client, url);
        String cleintType=request.getParameter("client");
		// �жϲ���ת
		if (responseStr.equals(ResponseStrUtil.RESPONSE_RETURN_LOGIN_SUCCESS)) {
            map.put("islogin",true);
            session.setAttribute(session.getId(), map);
			msg = "login_success";
			this.addActionMessage("��¼�ɹ�");
		} else {
            if(cleintType.equals("pad")){
                msg = "login_failure_pad";
            }else if(cleintType.equals("mobile")){
                msg = "login_failure_mobile";
            }else if(cleintType.equals("pc")){
                msg = "login_failure_pc";
            }
            map.put("islogin",false);
            session.setAttribute(session.getId(), map);

			this.addActionMessage("�û��������벻ƥ��");
		}

		return msg;
	}
	public void clientRemote(){
		String msg=login();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		MapToJson(map);
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
	
}
