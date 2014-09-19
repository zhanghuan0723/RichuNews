package com.twentyfirstcbh.richunews.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.util.Log;

import com.twentyfirstcbh.richunews.dao.ChannelDao;
import com.twentyfirstcbh.richunews.db.SQLHelper;

public class ChannelManage {
	public static ChannelManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<Category> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<Category> defaultOtherChannels;
	private ChannelDao channelDao;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;
	static {
		defaultUserChannels = new ArrayList<Category>();
		defaultOtherChannels = new ArrayList<Category>();
		defaultUserChannels.add(new Category(1, "头条", 1, 1));
		defaultUserChannels.add(new Category(2, "热点", 2, 1));
		defaultUserChannels.add(new Category(3, "娱乐", 3, 1));
		defaultUserChannels.add(new Category(4, "时尚", 4, 1));
		defaultUserChannels.add(new Category(5, "科技", 5, 1));
		defaultUserChannels.add(new Category(6, "体育", 6, 1));
		defaultUserChannels.add(new Category(7, "军事", 7, 1));
		defaultOtherChannels.add(new Category(8, "财经", 1, 0));
		defaultOtherChannels.add(new Category(9, "汽车", 2, 0));
		defaultOtherChannels.add(new Category(10, "房产", 3, 0));
		defaultOtherChannels.add(new Category(11, "社会", 4, 0));
		defaultOtherChannels.add(new Category(12, "情感", 5, 0));
		defaultOtherChannels.add(new Category(13, "女人", 6, 0));
		defaultOtherChannels.add(new Category(14, "旅游", 7, 0));
		defaultOtherChannels.add(new Category(15, "健康", 8, 0));
		defaultOtherChannels.add(new Category(16, "美女", 9, 0));
		defaultOtherChannels.add(new Category(17, "游戏", 10, 0));
		defaultOtherChannels.add(new Category(18, "数码", 11, 0));
	}

	private ChannelManage(SQLHelper paramDBHelper) throws SQLException {
		if (channelDao == null)
			channelDao = new ChannelDao(paramDBHelper.getContext());
		// NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));
		return;
	}

	/**
	 * 初始化频道管理类
	 * @param paramDBHelper
	 * @throws SQLException
	 */
	public static ChannelManage getManage(SQLHelper dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new ChannelManage(dbHelper);
		return channelManage;
	}

	/**
	 * 清除所有的频道
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<Category> getUserChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?",new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty()) {
			userExist = true;
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			List<Category> list = new ArrayList<Category>();
			for (int i = 0; i < count; i++) {
				Category navigate = new Category();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}
	
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<Category> getOtherChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?" ,new String[] { "0" });
		List<Category> list = new ArrayList<Category>();
		if (cacheList != null && !((List) cacheList).isEmpty()){
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++) {
				Category navigate= new Category();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		if(userExist){
			return list;
		}
		cacheList = defaultOtherChannels;
		return (List<Category>) cacheList;
	}
	
	/**
	 * 保存用户频道到数据库
	 * @param userList
	 */
	public void saveUserChannel(List<Category> userList) {
		for (int i = 0; i < userList.size(); i++) {
			Category channelItem = (Category) userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * 保存其他频道到数据库
	 * @param otherList
	 */
	public void saveOtherChannel(List<Category> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			Category channelItem = (Category) otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * 初始化数据库内的频道数据
	 */
	private void initDefaultChannel(){
		Log.d("deleteAll", "deleteAll");
		deleteAllChannel();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
