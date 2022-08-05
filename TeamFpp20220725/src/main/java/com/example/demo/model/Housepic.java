package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



/**
 * The persistent class for the housepic database table.
 * 
 */
@Entity
@Table(name="housepic")
@NamedQuery(name="Housepic.findAll", query="SELECT h FROM Housepic h")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "pictureId")
public class Housepic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pictureId;
	
	//bi-directional many-to-one association to House
	@ManyToOne
	@JoinColumn(name="houseId")
	//@JsonBackReference
	private House house;

	private String picVirtualPath;

	private String picFileName;

	private int picListNo;

	private String fileExtension;


	public Housepic() {
	}
	

	public Housepic(House house, String picVirtualPath, String picFileName, int picListNo,
			String fileExtension) {
		super();

		this.house = house;
		this.picVirtualPath = picVirtualPath;
		this.picFileName = picFileName;
		this.picListNo = picListNo;
		this.fileExtension = fileExtension;
	}


	public int getPictureId() {
		return this.pictureId;
	}

	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	public String getFileExtension() {
		return this.fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getPicFileName() {
		return this.picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public int getPicListNo() {
		return this.picListNo;
	}

	public void setPicListNo(int picListNo) {
		this.picListNo = picListNo;
	}

	public String getPicVirtualPath() {
		return this.picVirtualPath;
	}

	public void setPicVirtualPath(String picVirtualPath) {
		this.picVirtualPath = picVirtualPath;
	}

	public House getHouse() {
		return this.house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	@Override
	public String toString() {
		return "Housepic [pictureId=" + pictureId + ", house=" + house.getHouseId() + ", picVirtualPath=" + picVirtualPath
				+ ", picFileName=" + picFileName + ", picListNo=" + picListNo + ", fileExtension=" + fileExtension
				+ "]";
	}
	

}