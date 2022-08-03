package com.example.demo.model;

import java.util.*;

public class HouseListVO {
	 List<House>   house;
	    
	    public HouseListVO() {}
		public HouseListVO(List<House> house) {		
			this.house = house;
		}

		public List<House> getHouse() {
			return house;
		}

		public void setHouse(List<House> house) {
			this.house = house;
		}
}
