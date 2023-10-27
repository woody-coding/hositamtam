package model;

import java.util.Date;

<<<<<<< HEAD
	private int mNo ;
	private String mName;
	private String mType;
	private double mLat;
	private double mLng;
	
	public int getMNo() {
		return mNo;
	}
	public void setMNo(int mNo) {
		this.mNo = mNo;
	}
	public String getMName() {
		return mName;
	}
	public void setMName(String mName) {
		this.mName = mName;
=======
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
>>>>>>> 80c5b608be4ab2be372614acbb8d3c5496d5129a
	}
	public String getMType() {
		return mType;
	}
	public void setMType(String mType) {
		this.mType = mType;
	}
<<<<<<< HEAD
	public double getMLat() {
		return mLat;
	}
	public void setMLat(double mLat) {
		this.mLat = mLat;
	}
	public double getMLng() {
		return mLng;
	}
	public void setMLng(double mLng) {
		this.mLng = mLng;
=======
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
>>>>>>> 80c5b608be4ab2be372614acbb8d3c5496d5129a
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
