package com.carlosguilherme.dscommerce.dto;

public class FieldMessage {
	
	private String fildName;
	private String menssage;
	
	public FieldMessage(String fildName, String menssage) {
		this.fildName = fildName;
		this.menssage = menssage;
	}

	public String getFildName() {
		return fildName;
	}

	public void setFildName(String fildName) {
		this.fildName = fildName;
	}

	public String getMenssage() {
		return menssage;
	}

	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}
	
	
	

}
