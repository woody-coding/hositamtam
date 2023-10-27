package finalModel;

import java.util.Date;

public class MarketDO {
    private int mno;
    private String mname;
    private String mtype;
    private String maddr;
    private String mlat;
    private String mlng;
    private String mtoilet;
    private String mparking;
    private String mtel;
    private Date mupdateday;
    
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getMaddr() {
		return maddr;
	}
	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}
	public String getMlat() {
		return mlat;
	}
	public void setMlat(String mlat) {
		this.mlat = mlat;
	}
	public String getMlng() {
		return mlng;
	}
	public void setMlng(String mlng) {
		this.mlng = mlng;
	}
	public String getMtoilet() {
		return mtoilet;
	}
	public void setMtoilet(String mtoilet) {
		this.mtoilet = mtoilet;
	}
	public String getMparking() {
		return mparking;
	}
	public void setMparking(String mparking) {
		this.mparking = mparking;
	}
	public String getMtel() {
		return mtel;
	}
	public void setMtel(String mtel) {
		this.mtel = mtel;
	}
	public Date getMupdateday() {
		return mupdateday;
	}
	public void setMupdateday(Date mupdateday) {
		this.mupdateday = mupdateday;
	}
}
