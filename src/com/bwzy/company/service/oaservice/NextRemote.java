package com.bwzy.company.service.oaservice;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-4-22
 * Time: ����3:39
 * To change this template use File | Settings | File Templates.
 */
public interface NextRemote {
	/**
	 * �������������һ�����󣬻�ȡ���ݡ�
	 * @param url
	 * @return HtmlSource
	 */
    public String getNextRemoteHtmlSource(String url);
}
