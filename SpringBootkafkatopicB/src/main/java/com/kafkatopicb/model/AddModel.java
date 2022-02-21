package com.kafkatopicb.model;

public class AddModel {

	private Integer fieldA;
    private Integer fieldB;
    
    
	public Integer getFieldA() {
		return fieldA;
	}
	public void setFieldA(Integer fieldA) {
		this.fieldA = fieldA;
	}
	public Integer getFieldB() {
		return fieldB;
	}
	public void setFieldB(Integer fieldB) {
		this.fieldB = fieldB;
	}
	
	@Override
	public String toString() {
		return "AddModel [fieldA=" + fieldA + ", fieldB=" + fieldB + "]";
	}
    
    
    
}
