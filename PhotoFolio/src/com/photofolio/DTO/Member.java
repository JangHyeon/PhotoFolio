package com.photofolio.DTO;

public class Member {
		private String id ;
		private int level;
		private String nickname;
		private String email;
		private int phone;
		private String address;
		private String profileimg;
		private String memo;
		private int like;  //★ 추가함
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public int getPhone() {
			return phone;
		}
		public void setPhone(int phone) {
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
