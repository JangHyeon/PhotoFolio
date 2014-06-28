package com.photofolio.DTO;

import java.util.Date;

public class Challenge {
private int chall_index;
private int emblem_no ;
private String subject;
private String content;
private Date writedate;
private Date startdate;
private Date enddate;
private int activation;
private String emblem;// ★추가함


public String getEmblem() {
	return emblem;
}
public void setEmblem(String emblem) {
	this.emblem = emblem;
}
public int getChall_index() {
	return chall_index;
}
public void setChall_index(int chall_index) {
	this.chall_index = chall_index;
}
public int getEmblem_no() {
	return emblem_no;
}
public void setEmblem_no(int emblem_no) {
	this.emblem_no = emblem_no;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getWritedate() {
	return writedate;
}
public void setWritedate(Date writedate) {
	this.writedate = writedate;
}
public Date getStartdate() {
	return startdate;
}
public void setStartdate(Date startdate) {
	this.startdate = startdate;
}
public Date getEnddate() {
	return enddate;
}
public void setEnddate(Date enddate) {
	this.enddate = enddate;
}
public int getActivation() {
	return activation;
}
public void setActivation(int activation) {
	this.activation = activation;
}

}
