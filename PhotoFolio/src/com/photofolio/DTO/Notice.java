package com.photofolio.DTO;

import java.util.Date;

public class Notice {
private String id;
private int index;
private int status;
private Date updatedate;
private int check;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Date getUpdatedate() {
	return updatedate;
}
public void setUpdatedate(Date updatedate) {
	this.updatedate = updatedate;
}
public int getCheck() {
	return check;
}
public void setCheck(int check) {
	this.check = check;
}

}
