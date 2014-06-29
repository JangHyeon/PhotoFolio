package com.photofolio.DTO;

public class Member {
		private String id ;
		private int level;
		private String nickname;
		private String pwd;
		private String email;
		private String phone;
		private String address;
		private String profileimg;
		private String memo;
		private String homepage;
		private String checkintreset;
		public String getCheckintreset() {
			return checkintreset;
		}
		public void setCheckintreset(String checkintreset) {
			this.checkintreset = checkintreset;
		}
		private int like;  //★ 추가함
		
		public String getHomepage() {
			return homepage;
		}
		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}
		public String getCmemo() {
			return cmemo;
		}
		public void setCmemo(String cmemo) {
			this.cmemo = cmemo;
		}
		public String getHistory() {
			return history;
		}
		public void setHistory(String history) {
			this.history = history;
		}
		private String cmemo;
		private String history;
		
		
		
		public String getId() {
			return id; 
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getProfileimg() {
			return profileimg;
		}
		public void setProfileimg(String profileimg) {
			this.profileimg = profileimg;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public int getLike() {
			return like;
		}
		public void setLike(int like) {
			this.like = like;
		}
}
