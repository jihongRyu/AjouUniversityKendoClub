package com.multi.dto;

public class CalendarDto extends BaseDto{
	
	private String id="";
	private String title="";
	private String contents="";
	private String imageNm="";
	private String writer="";
	private String wdate="";

	private String calendarmemoYear="";
	private String calendarmemoMonth="";
	private String calendarmemoDay="";
	private String calendarmemoContents="";
	
	private String Year;
	private String Month;
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getImageNm() {
		return imageNm;
	}
	public void setImageNm(String imageNm) {
		this.imageNm = imageNm;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	public String getCalendarmemoYear() {
		return calendarmemoYear;
	}
	public void setCalendarmemoYear(String calendarmemoYear) {
		this.calendarmemoYear = calendarmemoYear;
	}
	public String getCalendarmemoMonth() {
		return calendarmemoMonth;
	}
	public void setCalendarmemoMonth(String calendarmemoMonth) {
		this.calendarmemoMonth = calendarmemoMonth;
	}
	public String getCalendarmemoDay() {
		return calendarmemoDay;
	}
	public void setCalendarmemoDay(String calendarmemoDay) {
		this.calendarmemoDay = calendarmemoDay;
	}
	public String getCalendarmemoContents() {
		return calendarmemoContents;
	}
	public void setCalendarmemoContents(String calendarmemoContents) {
		this.calendarmemoContents = calendarmemoContents;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getMonth() {
		return Month;
	}
	public void setMonth(String month) {
		Month = month;
	}
	
	
	
	
}
