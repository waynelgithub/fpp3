package com.example.demo.controller;
import java.util.*;

import com.example.demo.model.House;
import com.example.demo.model.HouseDAO;
import com.example.demo.model.HouseListVO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HouseDBController {
	@RequestMapping(value="/h2",method = RequestMethod.GET)
	public String mainHouse(ModelMap model) {	
		return "mainHouse";
	}
	@RequestMapping(value="/house2",method = RequestMethod.GET)
	public @ResponseBody HouseListVO getAllHouse(ModelMap model) {
	
		HouseDAO dao=new HouseDAO();
		return dao.getList();
	}
	 @RequestMapping(value = "/house2", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	 //新增功能
	  public ResponseEntity<String> createHouse(@RequestBody House house) 
	  {
		HouseDAO dao=new HouseDAO();
	    house.setHouseId(dao.getAll().size() + 1);
	    //取得list size，但如果有刪除的會有問題，應該取得最後一筆資料的id，透過select語法取得最後一個id
	    try {
	        dao.add(house);
	        dao.getList().getHouse().add(house);
	        return new ResponseEntity<String>(HttpStatus.CREATED);
	    }catch(Exception ex) {
	    	return new ResponseEntity<String>(HttpStatus.CONFLICT);
	    }
	    
	  }
	 @RequestMapping(value = "/house2/{houseId}",consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	  public ResponseEntity<House> updateHouse(@PathVariable("houseId") int houseId, @RequestBody House house) 
	  {
		 HouseDAO dao=new HouseDAO();
	    try {
	        dao.update(house);
	       
	        return new ResponseEntity<House>(house, HttpStatus.OK);
	    }catch(Exception ex) {
	    	return new ResponseEntity<House>(house, HttpStatus.NOT_FOUND);
	    }
	    
	  }
	 @RequestMapping(value = "/house2/{houseId}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> deleteHouse(@PathVariable("houseId") int houseId) 
	  {
		 HouseDAO dao=new HouseDAO();
		  List<House>  data=dao.getAll();
	      int index=-1;
	      for(int i=0;i<data.size();i++) {
	    	  if(data.get(i).getHouseId()==houseId) {
	    		  index=i;
	    		  break;
	    	  }
	      }
		 
	    if(index>=0){
	    	dao.remove(dao.getAll().get(index));	        
	        return new ResponseEntity<String>(HttpStatus.OK);
	    }
	    return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	  }
}
