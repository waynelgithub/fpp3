package com.example.demo.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.House;
import com.example.demo.model.Housepic;

public interface HousepicDao extends JpaRepository<Housepic, Integer> {
	
	
	List<Housepic> findByHouse(House house);
	
	List<Housepic> findByHouse_HouseCity(String houseCity);
	
	List<Housepic> findByHouseAndPicListNo(House house, Integer picListNo);
}
