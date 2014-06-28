package com.photofolio.DTO;

import java.sql.Timestamp;
import java.util.Date;

public class Reply {
private int reply_idx;
private int idx;
private String id;
private String nickname;
private String profileimg;
private Timestamp writedate;
private String content;
private int report;

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
public Timestamp getWritedate() {
	return writedate;
}
public void setWritedate(Timestamp writedate) {
	this.writedate = writedate;
}
public int getReport() {
	return report;
}
public void setReport(int report) {
	this.report = report;
}
public int getIdx() {
	return idx;
}
public void setIdx(int idx) {
	this.idx = idx;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getProfileimg() {
	return profileimg;
}
public void setProfileimg(String profileimg) {
	this.profileimg = profileimg;
}
public int getReply_idx() {
	return reply_idx;
}
public void setReply_idx(int reply_idx) {
	this.reply_idx = reply_idx;
}

}
