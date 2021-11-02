package com.multi.newmember;

public class boardDto {
	private String id;
	private String writer;
	private String title;
	private String contents;
	private String regdate;
	private String delyn;
	private String hit;
	private String ip;
	
	public boardDto() {
		id="liujihong";
		writer ="유지홍";
		title = "검도이론";
		regdate="2019-03-20";
	}
	
	public boardDto(String id, String writer, String title, String contents, String regdate) {
		this.id =id;
		this.writer = writer;
		this.title =title;
		this.contents =contents;
		this.regdate =regdate;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	

}
