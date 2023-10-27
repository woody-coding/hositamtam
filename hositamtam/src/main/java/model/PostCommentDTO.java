package model;

import java.util.Date;

public class PostCommentDTO {
	
    private int pno;         // 글 번호
    private String id;       // 아이디
    private Date pregdate;   // 글 작성 시간
    private String ptitle;   // 제목
    private String pcontent; // 내용
    private String pphoto;   // 글 사진
    private int plikecount;  // 좋아요 수
    private String pcategory; // 글 카테고리
    private int cno;         // 댓글 번호
    private String ccontent; // 댓글 내용
    private Date cregdate;   // 댓글 작성 시간
    
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPregdate() {
		return pregdate;
	}
	public void setPregdate(Date pregdate) {
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
}
