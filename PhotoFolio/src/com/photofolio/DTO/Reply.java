package com.photofolio.DTO;

import java.util.Date;

public class Reply {
private int reply_index;
private int index;
private String id;
private String content;
private Date writedate;
private int report;
public int getReply_index() {
	return reply_index;
}
public void setReply_index(int reply_index) {
	this.reply_index = reply_index;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public int getReport() {
	return report;
}
public void setReport(int report) {
	this.report = report;
}

}
