package com.multi.newmember;

//control-shift-y : 소문자로 변환
//control-shift-x : 대문자로 변환

public class MemberDto {
	
	private String member_id;
	private String userid;
	private String password;
	private String username;
	private String schoolnum;
	private String schoolsub;
	private String email;
	private String zipcode;
	private String address1;
	private String address2;
	private String phone;
	private String regdate;
	private int level;
	private int delFlg;
	private String enteryear="";
	
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSchoolnum() {
		return schoolnum;
	}
	public void setSchoolnum(String schoolnum) {
		this.schoolnum = schoolnum;
	}
	public String getSchoolsub() {
		return schoolsub;
	}
	public void setSchoolsub(String schoolsub) {
		this.schoolsub = schoolsub;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getDelflg() {
		return delFlg;
	}
	public void setDelFlg(int delFlg) {
		this.delFlg = delFlg;
	}
	public String getEnteryear() {
		return enteryear;
	}
	public void setEnteryear(String enteryear) {
		this.enteryear = enteryear;
	}
	
	
}
