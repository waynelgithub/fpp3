package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="housepic")
public class HousePic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int pictureId;
	
	int houseId;
	String picVirtualPath;
	String picFileName;
	int picListNo;
	String fileExtension;
	
	public HousePic() {
		
	}

	public HousePic(int pictureId, int houseId, String picVirtualPath, String picFileName, int picListNo,
			String fileExtension) {
		this.pictureId = pictureId;
		this.houseId = houseId;
		this.picVirtualPath = picVirtualPath;
		this.picFileName = picFileName;
		this.picListNo = picListNo;
		this.fileExtension = fileExtension;
	}

	public int getPictureId() {
		return pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public String getPicVirtualPath() {
		return picVirtualPath;
	}

	public void setPicVirtualPath(String picVirtualPath) {
		this.picVirtualPath = picVirtualPath;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public int getPicListNo() {
		return picListNo;
	}

	public void setPicListNo(int picListNo) {
		this.picListNo = picListNo;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	

}
