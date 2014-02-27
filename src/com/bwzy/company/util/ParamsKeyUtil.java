package com.bwzy.company.util;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 13-2-25
 * Time: ????11:29
 * To change this template use File | Settings | File Templates.
 */
public interface ParamsKeyUtil {
    /**
     * 固定的客户端参数
     */

    //基本参数
	public static final String URL_PARAMS_KEY_BUSINESSNUM = "businessNUM";
    public static final String URL_PARAMS_KEY_BUSINESSTYPE = "businesstype";
    public static final String URL_PARAMS_KEY_TYPE = "type";
    public static final String URL_PARAMS_KEY_URL = "url";
    public static final String URL_PARAMS_KEY_JSESSIONID ="jsessionid";
    public static final String URL_PARAMS_KEY_HTTPRESQUEST ="httpresquest";
    public static final String URL_PARAMS_KEY_HTTPRESPONSE ="httpresponse";

    //OA用户名、密码
    public static final String URL_PARAMS_KEY_USERNAME = "yhm"; //根据系统要求改变
    public static final String URL_PARAMS_KEY_PASSWORD = "dlmm"; //更具系统要求改变

    //邮箱

    public static final String URL_PARAMS_KEY_MAILID = "mailid";
    public static final String URL_PARAMS_KEY_MAILPASS = "mailpass";
    public static final String URL_PARAMS_KEY_FROMID = "fromid";
    public static final String URL_PARAMS_KEY_ENTITYID = "entityid";
    public static final String URL_PARAMS_KEY_PAGENOW = "pagenow";
    public static final String URL_PARAMS_KEY_USERID = "userid";
    public static final String URL_PARAMS_KEY_TITLE = "title";
    public static final String URL_PARAMS_KEY_CONTENT="content";
    public static final String URL_PARAMS_KEY_TO="to";
    public static final String URL_PARAMS_KEY_CC="cc";
    public static final String URL_PARAMS_KEY_BCC="bcc";
    public static final String URL_PARAMS_KEY_FILENAME="filename";
    public static final String URL_PARAMS_KEY_FILEPATH="filepath";
    public static final String URL_PARAMS_KEY_FILETYPE="filetype";

    //客户端更新
    public static final String URL_PARAMS_KEY_VERNAME="vername";
    public static final String URL_PARAMS_KEY_VERCODE="vercode";

    //请求方式
    public static final String URL_PARAMS_KEY_REQUEST_METHOD="method";

}
