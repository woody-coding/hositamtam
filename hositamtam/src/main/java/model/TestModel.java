package model;

public class TestModel {

	String controllerMsg;
	private int No ;
	private String Name;
	private String Type;
	private String storeName;
	
	
	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getControllerMsg() {
		return controllerMsg;
	}

	public void setControllerMsg(String controllerMsg) {
		this.controllerMsg = controllerMsg;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
}
