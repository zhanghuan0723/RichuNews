id：文章，图集或者视频的id
catname：对应的分类
type：用于区分是文章，图集还是视频，其中article代表文章，image代表图集，video代表视频
title：标题
thumb：缩略图
thumbdesc:只有当type=video时才有值，代表视频的注释说明
desc：摘要
origurl：源自哪个url
inputtime：录入时间
photo：只有当type=image才有值，表示图集的意思，是一个数组。里面的参数中，thumb代表图片，desc代表图片对应的文字解释

RichuHttpClient.get(Constant.RICHU_CONFIG, new BaseJsonHttpResponseHandler<Headers>() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Headers response) {
				// if (response != null)
				// titleTV.setText(response.getXRequestId());
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Headers errorResponse) {

			}

			@Override
			protected Headers parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
				JSONObject resJO = new JSONObject(rawJsonData);
				String headers = resJO.get("headers").toString();
				Gson gson = new Gson();
				return gson.fromJson(headers, Headers.class);
			}
		});