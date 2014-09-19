package com.twentyfirstcbh.richunews.object;

import java.io.Serializable;

public class BaseData implements Serializable {

	private static final long serialVersionUID = 8840987247330074313L;
	private static final long UPDATE_INTERVAL = 600000; // 更新时间间隔
	private long lastUpdateTime;
	
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public void setDataUpdated() {
		setLastUpdateTime(System.currentTimeMillis());
	}
	
	public boolean isNeed2Update() {
		if (System.currentTimeMillis() - lastUpdateTime > UPDATE_INTERVAL) {
			return true;
		}
		return false;
	}
}
