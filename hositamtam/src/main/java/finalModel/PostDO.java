package finalModel;

import java.util.Date;

public class PostDO {

	private int pno;
	private int mno;
	private String id;
	private String nickname;
	private String pregdate;
	private String ptitle;
	private String pcontent;
	private String pphoto;
	private int plikecount;
	private String pcategory;
	private int countcomments;
	
    private int cno;         // 댓글 번호
    private String ccontent; // 댓글 내용
    private Date cregdate;   // 댓글 작성 시간
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPregdate() {
		return pregdate;
	}
	public void setPregdate(String pregdate) {
		this.pregdate = pregdate;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPphoto() {
		return pphoto;
	}
	public void setPphoto(String pphoto) {
		this.pphoto = pphoto;
	}
	public int getPlikecount() {
		return plikecount;
	}
	public void setPlikecount(int plikecount) {
		this.plikecount = plikecount;
	}
	public String getPcategory() {
		return pcategory;
	}
	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public Date getCregdate() {
		return cregdate;
	}
	public void setCregdate(Date cregdate) {
		this.cregdate = cregdate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getCountcomments() {
		return countcomments;
	}
	public void setCountcomments(int countcomments) {
		this.countcomments = countcomments;
	}
	
}
