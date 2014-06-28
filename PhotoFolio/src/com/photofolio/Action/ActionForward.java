package com.photofolio.Action;


//처리를 담당하는 메서드 가지는 클래스
//servlet 처리하는 방법
//ex) view 바로처리 , 데이터를 가지고 처리하는 경우 ...

public class ActionForward {
	private boolean isRedirect = false;
	private String path = null;

	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
