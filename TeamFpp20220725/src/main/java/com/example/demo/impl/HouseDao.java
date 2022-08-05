package com.example.demo.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.House;
import com.example.demo.model.Housepic;

public interface HouseDao extends JpaRepository<House, Integer>{
	
	House findByHouseId(Integer houseId);
	

}
