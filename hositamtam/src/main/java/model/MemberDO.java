package model;

public class MemberDO {
    private String id;
    private String nickname;
    private String passwd;
    private String birthdate;
    private String gender;
    private int exp;
    private int grade;
    private String newPasswd;
    private String newNickname;
    private int exist;
    
    public MemberDO() {
    	
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getNewNickname() {
		return newNickname;
	}

	public void setNewNickname(String newNickname) {
		this.newNickname = newNickname;
	}
	public int getExist() {
		return exist;
	}
	public void setExist(int exist) {
		this.exist = exist;
	}
}
