package com.photofolio.DTO;

import java.util.Date;

public class Article {

		private int index;
		private String id;
		private String subject;
		private String content;
		private Date writedate;
		private int count;
		private int like;
		private int report;
		private String tag;
		private int secret;
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
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public int getLike() {
			return like;
		}
		public void setLike(int like) {
			this.like = like;
		}
		public int getReport() {
			return report;
		}
		public void setReport(int report) {
			this.report = report;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public int getSecret() {
			return secret;
		}
		public void setSecret(int secret) {
			this.secret = secret;
		}
		
}
