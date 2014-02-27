package com.bwzy.company.util;

/**
 * Created with IntelliJ IDEA.
 * User: LVYANGJUN
 * Date: 13-2-25
 * Time: ????11:37
 * To change this template use File | Settings | File Templates.
 */
public interface ParamsValueUtil {
    /**
     * 固定的客户端参数值
     */

    //服务分类
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_OASERVICE = "oaservice";//OA服务
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_MAILSERVICE = "mailservice";  //邮箱服务
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_WEBSERVICE = "webservice";//门户网站
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_UPDATESERVICE = "updateservice";//更新服务
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_LOGIN = "login"; //统一登录服务
    public static  final String URL_PARAMS_VALUE_REQUEST_UPLOADBUGFILESERVICE="uploadbugfileservice";//请求方式 默认为method

    //web
    public static final String URL_PARAMS_VALUE_TYPE_LIST = "list";//门户新闻的列表
    public static final String URL_PARAMS_VALUE_TYPE_CONTENT = "content";//门户新闻内容

    //登录
    public static final String URL_PARAMS_VALUE_TYPE_OALOGIN = "oalogin";//OA系统用户登录
    public static final String URL_PARAMS_VALUE_TYPE_MAILLOGIN = "maillogin";//邮箱用户系统登录

    //附件
    public static final String URL_PARAMS_VALUE_MAILTYPE_ATTACHMENY = "attachment"; //附件类型

    // 邮箱
    public static final String URL_PARAMS_VALUE_MAILTYPE_INBOX = "inbox";//收件箱
    public static final String URL_PARAMS_VALUE_MAILTYPE_MAILDETAIL = "maildetail";//邮件详情
    public static final String URL_PARAMS_VALUE_MAILTYPE_REPIY = "1"; //写信
    public static final String URL_PARAMS_VALUE_MAILTYPE_SEND = "2";  //回复
    public static final String URL_PARAMS_VALUE_MAILTYPE_TRANSMIT = "3";//转发

    //OA

    //客户端更新
    public static  final String URL_PARAMS_VALUE_UPDATE_CLIENT="updateapk";//获取最新版本信息
    public static  final String URL_PARAMS_VALUE_DOWNlOAD_CLIENT="downloadapk";//下载最新版本


    //获取sessionid
    public static  final String URL_PARAMS_VALUE_TYPE_GETSESSIONID="getsessionid";//获取OA系统的sessionid

    //请求方式
    public static  final String URL_PARAMS_VALUE_REQUEST_METHOD="post";//请求方式 默认为method



}
