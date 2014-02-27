package com.bwzy.company.service.oaservice.impl;

import com.bwzy.company.service.oaservice.NextRemote;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: lvyangjun Date: 13-4-22 Time: ÏÂÎç3:40 To
 * change this template use File | Settings | File Templates.
 */
public class NextRemoteImpl implements NextRemote {
	@Override
	public String getNextRemoteHtmlSource(String url) {
		HttpClient httpclient = new HttpClient();
		HttpMethod method = new GetMethod(url);
		String nextRemoteStr = "";
		try {
			httpclient.executeMethod(method);
			nextRemoteStr = method.getResponseBodyAsString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return nextRemoteStr;
	}
}
