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
     * �̶��Ŀͻ��˲���ֵ
     */

    //�������
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_OASERVICE = "oaservice";//OA����
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_MAILSERVICE = "mailservice";  //�������
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_WEBSERVICE = "webservice";//�Ż���վ
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_UPDATESERVICE = "updateservice";//���·���
    public static final String URL_PARAMS_VALUE_BUSINESSTYPE_LOGIN = "login"; //ͳһ��¼����
    public static  final String URL_PARAMS_VALUE_REQUEST_UPLOADBUGFILESERVICE="uploadbugfileservice";//����ʽ Ĭ��Ϊmethod

    //web
    public static final String URL_PARAMS_VALUE_TYPE_LIST = "list";//�Ż����ŵ��б�
    public static final String URL_PARAMS_VALUE_TYPE_CONTENT = "content";//�Ż���������

    //��¼
    public static final String URL_PARAMS_VALUE_TYPE_OALOGIN = "oalogin";//OAϵͳ�û���¼
    public static final String URL_PARAMS_VALUE_TYPE_MAILLOGIN = "maillogin";//�����û�ϵͳ��¼

    //����
    public static final String URL_PARAMS_VALUE_MAILTYPE_ATTACHMENY = "attachment"; //��������

    // ����
    public static final String URL_PARAMS_VALUE_MAILTYPE_INBOX = "inbox";//�ռ���
    public static final String URL_PARAMS_VALUE_MAILTYPE_MAILDETAIL = "maildetail";//�ʼ�����
    public static final String URL_PARAMS_VALUE_MAILTYPE_REPIY = "1"; //д��
    public static final String URL_PARAMS_VALUE_MAILTYPE_SEND = "2";  //�ظ�
    public static final String URL_PARAMS_VALUE_MAILTYPE_TRANSMIT = "3";//ת��

    //OA

    //�ͻ��˸���
    public static  final String URL_PARAMS_VALUE_UPDATE_CLIENT="updateapk";//��ȡ���°汾��Ϣ
    public static  final String URL_PARAMS_VALUE_DOWNlOAD_CLIENT="downloadapk";//�������°汾


    //��ȡsessionid
    public static  final String URL_PARAMS_VALUE_TYPE_GETSESSIONID="getsessionid";//��ȡOAϵͳ��sessionid

    //����ʽ
    public static  final String URL_PARAMS_VALUE_REQUEST_METHOD="post";//����ʽ Ĭ��Ϊmethod



}
