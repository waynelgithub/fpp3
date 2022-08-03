package com.example.demo.model;
import javax.persistence.*;


@Entity
@Table(name="house")
public class House {
	@Id
	int houseId;
    String description, houseType, housePattern, houseCity, houseArea, address, createdDT, updatedDT, gmapURL;
    int price,houseOwner;
    boolean houseStatus;
    double houseSize;

	public House() {}
	
	public House(int houseId, String description, String houseType, String housePattern, String houseCity,
			String houseArea, String address, int price, int houseOwner, boolean houseStatus, double houseSize,
			String createdDT, String updatedDT, String gmapURL) {

		this.houseId = houseId;
		this.description = description;
		this.houseType = houseType;
		this.housePattern = housePattern;
		this.houseCity = houseCity;
		this.houseArea = houseArea;
		this.address = address;
		this.price = price;
		this.houseOwner = houseOwner;
		this.houseStatus = houseStatus;
		this.houseSize = houseSize;
		this.createdDT = createdDT;
		this.updatedDT = updatedDT;
		this.gmapURL= gmapURL;
	}


	public int getHouseId() {
		return houseId;
	}


	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getHouseType() {
		return houseType;
	}


	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}


	public String getHousePattern() {
		return housePattern;
	}


	public void setHousePattern(String housePattern) {
		this.housePattern = housePattern;
	}


	public String getHouseCity() {
		return houseCity;
	}


	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}


	public String getHouseArea() {
		return houseArea;
	}


	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getHouseOwner() {
		return houseOwner;
	}


	public void setHouseOwner(int houseOwner) {
		this.houseOwner = houseOwner;
	}


	public boolean isHouseStatus() {
		return houseStatus;
	}


	public void setHouseStatus(boolean houseStatus) {
		this.houseStatus = houseStatus;
	}


	public double getHouseSize() {
		return houseSize;
	}


	public void setHouseSize(double houseSize) {
		this.houseSize = houseSize;
	}


	public String getCreatedDT() {
		return createdDT;
	}


	public void setCreatedDT(String createdDT) {
		this.createdDT = createdDT;
	}


	public String getUpdatedDT() {
		return updatedDT;
	}


	public void setUpdatedDT(String updatedDT) {
		this.updatedDT = updatedDT;
	}
	public String getgmapURL() {
		return gmapURL;
	}
	
	public void setgmapURL(String gmapURL) {
		this.gmapURL = gmapURL;
	}
    
    
    
}
