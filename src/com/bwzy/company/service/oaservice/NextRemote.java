package com.bwzy.company.service.oaservice;

/**
 * Created with IntelliJ IDEA.
 * User: lvyangjun
 * Date: 13-4-22
 * Time: 下午3:39
 * To change this template use File | Settings | File Templates.
 */
public interface NextRemote {
	/**
	 * 解析后还需访问下一个请求，获取数据。
	 * @param url
	 * @return HtmlSource
	 */
    public String getNextRemoteHtmlSource(String url);
}
