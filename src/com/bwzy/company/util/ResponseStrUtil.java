package com.bwzy.company.util;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 13-2-25
 * Time: 下午12:12
 * To change this template use File | Settings | File Templates.
 */
public interface ResponseStrUtil {

    public static final String RESPONSE_RETURN_CLIENT_RELOGIN = "登陆超时，请重新登录";
    public static final String RESPONSE_RETURN_CLIENT_BUSINESS_CLOSE = "该模块接口已关闭";
    public static final String RESPONSE_RETURN_CLIENT_BUSINESS_CHECK= "该模块接口在审核中";
    public static final String REQUEST_LOG_DB_BUSINESS_FAILURE= "请求失败，业务接口未开放";
    public static final String REQUEST_LOG_DB_BUSINESS_SUCCESS= "请求成功";
    public static final String  RESPONSE_RETURN_CLIENT_FAILURE="请求业务失败,请检查原系统是否正常";
    public static final String  RESPONSE_RETURN_HTML_FAILURE="请求业务失败,查看网络连接或原系统是否正常";
    public static final String  RESPONSE_RETURN_HTML_CHECKCODE="验证码解析错误，请重试！";
    
    public static final String  RESPONSE_RETURN_HTML_TIMEOUT="原系统响应超时";
    public static final String  RESPONSE_RETURN_HTML_READTIMEOUT="读取文件超时";
    public static final String  RESPONSE_RETURN_MESSAGE_COMMITSUCCESS="注册成功";
    public static final String  RESPONSE_RETURN_MESSAGE_COMMITFAIlURE="注册失败，证件编号已被注册";
    public static final String  RESPONSE_RETURN_LOGIN_FAILURE="登录失败,请输入正确的用户名密码";
    public static final String  RESPONSE_RETURN_LOGIN_SUCCESS="登录成功";
    public static final String  RESPONSE_RETURN_LOGIN_SYSTEMERROR="登录时出错，请检查原系统是否运行正常";

    public static final String RESPONSE_RETURN_DUG_UPLOAD_SUCCESS="错误文件上传成功";

    public static final String RESPONSE_RETURN_DUG_UPLOAD_FAILURE="错误文件上传失败";





}
