package model;

public class ReviewDO {
    private int rno;
    private int sno;
    private String id;
    private String rregdate;
    private String rcontent;
    private double rrating;
    private String nickname;
    private int reviewcount;
    private String sname;
    
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRregdate() {
		return rregdate;
	}
	public void setRregdate(String rregdate) {
		this.rregdate = rregdate;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public double getRrating() {
		return rrating;
	}
	public void setRrating(double rrating) {
		this.rrating = rrating;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getReviewcount() {
		return reviewcount;
	}
	public void setReviewcount(int reviewcount) {
		this.reviewcount = reviewcount;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}

}