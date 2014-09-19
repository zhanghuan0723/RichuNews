package com.twentyfirstcbh.richunews.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RichuHttpClient {

	private static AsyncHttpClient client = new AsyncHttpClient();

	static {
		client.setTimeout(30000); // 设置链接超时，如果不设置，默认为10s
	}

	public static void get(String url, AsyncHttpResponseHandler responseHandler) {
		client.get(url, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}

}
