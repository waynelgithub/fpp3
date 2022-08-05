package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.internal.util.io.StreamCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.impl.HouseDao;
import com.example.demo.impl.HousepicDao;

import com.example.demo.model.House;
import com.example.demo.model.Housepic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HousepicController {

	private static final String IMAGE_DIRECTORY = "houseimages";
	// 設定一個virtual path，是用在網址上的
	
	@Autowired
	HousepicDao hpdao;
	
	@Autowired
	HouseDao houseDao;
	
	//圖片數量上限
	private Integer upperLimit = 15;
	
	
	public HousepicController() {

	}
	
//	@RequestMapping(value = "/responsetest",method = RequestMethod.GET)
//	public String responseTest() {
//		return "file saved.";
//	}
	
//	@RequestMapping(value = "/getimage/{no}",method = RequestMethod.GET)
//	public ModelAndView getImage1(@PathVariable String no) {
//		return new ModelAndView("redirect:/houseimages/"+no+".jpg");
//	}
	
//	@GetMapping (value="/gethousejsonall")
//	public  /*ResponseEntity<List<House>> */ List<House> getHouseJson(ModelMap model) {
//
//		List<House> houses = houseDao.findAll();
////		House house2 = new House();
////		house2.setHouseId(2);
////		List<Housepic> hps =hpdao.findByHouse(house2);
//		//hps.forEach(System.out::println);
//		
//		//return new ResponseEntity<List<Housepic>>(hps,HttpStatus.OK);
//
//		return houses;
//	}
	
	@GetMapping (value="/getHousepicByHouseId/{houseId}")
	public  /*ResponseEntity<List<Housepic>> */ List<Housepic> getImageByHouseId(@PathVariable Integer houseId) throws JsonProcessingException {
		House houseTmp = houseDao.findByHouseId(houseId);
		List<Housepic> hps =hpdao.findByHouse(houseTmp);
		//String result = new ObjectMapper().writeValueAsString(hps);
		//System.out.println(result); // trace 用
		
//		List<Housepic> hps2= hpdao.findByHouse_HouseCity("台北市");
//		System.out.println(hps2.get(0));

		//return new ResponseEntity<List<Housepic>>(hps,HttpStatus.OK);

		return hps;
	}
	
	@GetMapping (value="/getHousepicByHouseCity/{houseCity}")
	public  /*ResponseEntity<List<Housepic>> */ List<Housepic> getImageByHouseId(@PathVariable String houseCity) throws JsonProcessingException {
		List<Housepic> hps= hpdao.findByHouse_HouseCity("台北市");
		System.out.println(hps.get(0));

		//return new ResponseEntity<List<Housepic>>(hps,HttpStatus.OK);

		return hps;
	}
	
	@GetMapping (value="/getHousepicAll")
	public  /*ResponseEntity<List<Housepic>> */ List<Housepic> getImageJson(ModelMap model) {

		List<Housepic> hps =hpdao.findAll();

		//hps.forEach(System.out::println); // trace用
		
		//return new ResponseEntity<List<Housepic>>(hps,HttpStatus.OK); 

		return hps;
	}
	
	@GetMapping (value="/getHousepicAllth")
	public  ModelAndView getImageth() {

		List<Housepic> hps =hpdao.findAll();
		//hps.forEach(System.out::println); // trace用		
		
		return new ModelAndView("templates/imageList", "imageList", hps);
		
	}
	
	@GetMapping (value="/getHousepicth/{houseId}")
	public  ModelAndView getImagethByHouseid(@PathVariable Integer houseId) {
		House houseTmp = houseDao.findByHouseId(houseId);
		List<Housepic> hps =hpdao.findByHouse(houseTmp);
		//hps.forEach(System.out::println); // trace用		
		
		return new ModelAndView("templates/imageListbyHouseId", "imageList", hps);
		
	}
	
	@RequestMapping(value = "/housepicUpload", method = RequestMethod.POST)
	//@PostMapping(value = "/savefile", method = RequestMethod.POST)
	//public ModelAndView saveimage(@RequestParam CommonsMultipartFile file, HttpSession session)
	//public ModelAndView saveimage(@RequestParam MultipartFile file, @RequestParam Integer houseid,HttpSession session)
	public String saveimage(@RequestParam MultipartFile file, @RequestParam Integer houseid,HttpSession session)
			throws Exception {
	//get ServletContext
		ServletContext context = session.getServletContext();
	//get server real path related to webapp's virtual path 
		String path = context.getRealPath("/"+IMAGE_DIRECTORY);
		
	// create path if real path didn't exist 
		File _path = new File(path);
		if (!_path.exists()) {
			_path.mkdir();
		}
		String filename = file.getOriginalFilename();
		
		

	//改名程序開始
		//取得houseid
		Integer houseId= houseid;

	//根據找出HouseId找出House, null 要報錯
		House houseTmp = houseDao.findByHouseId(houseId);
			//先暫時用空House 物件新增
			//House houseTmp = new House();
			//houseTmp.setHouseId(houseId);

		//確認不是null才繼續改名程序		
		if (!(houseTmp==null)) {
	
			
			//取得picListNo
				//做個假的取得一個Integer array, 代表從db取得的該houseId 圖片的 picListNo array			
				//Integer[] listNoTemp = {0,1,2,4,5,6,8,9,10,11,12,13}; //要從DB取得[] 
				//List<Integer> piclistNos= new ArrayList<Integer>(Arrays.asList(listNoTemp));
			
			//建立一個從db取得的該houseId 圖片的 picListNo list<Integer>
			List<Housepic> hps =hpdao.findByHouse(houseTmp);
			Function<Housepic, Integer> funVal = (Housepic s)->s.getPicListNo();
			List<Integer> piclistNos= hps.stream().map(funVal).collect(Collectors.toList());
			
	
	
			Integer picListNo=-1;
			//list listNos size() < 上限(15)，則可以增加圖片		
			if (piclistNos.size()<upperLimit)
			{
				//比對picListNo, 找到還未使用的picListNo來使用	
				for (Integer no =0; no <upperLimit; no++)			
					{
						/*1.迴圈法
							int flag=0;
							for(Integer pNo: piclistNos)
							{
								if (no == pNo) {
									flag=1;
									break;						
								}
							}
							if (flag==1) continue;
							
							picListNo= no;
							break;
						*/
							
						//2.list方法				
							if (!piclistNos.contains(no))
							{
								picListNo=no;
								break;
							}
						
					}
					System.out.println("picListNo:"+picListNo);	
					
					String fileBaseName = FilenameUtils.getBaseName(filename);
					String fileExtension = FilenameUtils.getExtension(filename);
					System.out.println("file base name without extension:"+ fileBaseName);
					System.out.println("file extension:"+ fileExtension);
					//建立新檔名
					String newFileName = houseId+"_"+picListNo+"."+fileExtension;
					System.out.println("new file name:"+ newFileName);
					//建立完整路徑
					String newPathName = path+File.separator+ newFileName;
				//改名完成
					
					//先存檔，再存DB
					//存圖檔開始
					System.out.println("new path:"+newPathName);
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(newPathName)));
					stream.write(bytes);
					stream.flush();
					stream.close();
					//存圖檔完成
		
				//存路徑資訊到DB ->找出House的步驟在整個新增步驟的第一層
					//存圖檔資訊到DB開始
					Housepic housepicTmp = new Housepic(houseTmp, IMAGE_DIRECTORY, newFileName, picListNo, fileExtension);
					hpdao.save(housepicTmp);
					//存圖檔資訊到DB完成
	
				//存圖檔&DB都完成，回報成功
					//jsp or thymeleaf用
					//return new ModelAndView("success", "filesuccess", path + File.separator + filename);
					
					//response.statusText only
					//return  new ResponseEntity(HttpStatus.CREATED);
					return "Image saved.";
					
					
				
			}
			else {	//超過圖片量上限，不存檔，不存DB		
					System.out.println("Exceed image limitation.");
					//return new ModelAndView("fail", "fail", "Exceed image limitation.");
					//return  new ResponseEntity(HttpStatus.CONFLICT);
					return "Exceed image limitation.";
					}
	

		}else {//houseId不存在，不存檔，不存DB	
			System.out.println("HouseId doesn't exist.");
			//return new ModelAndView("fail", "fail", "Image saving failed.");
			//return  new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
			return "Image saving failed.";
		}
		


		
		
	}
	
	
//	@RequestMapping(value = "/savefile2", method = RequestMethod.POST)
//	//public ModelAndView saveimage2(@RequestParam CommonsMultipartFile file, HttpSession session)
//	// HttpSession session 也就沒必要
//	public ModelAndView saveimage2(@RequestParam CommonsMultipartFile file)
//			throws Exception {
//		
////		ServletContext context = session.getServletContext();
////		String path = context.getRealPath(VirtualPath);
////		System.out.println("Real Path:"+path);
////			File tmpFile = new File(path);
////			if (!tmpFile.exists()) {
////				tmpFile.mkdir();
////			}
//		//以上這些可以不用，只是為了demo
//		//但以下這些都變成只在真實路徑上作業，其實有安全疑慮
//		String path2= "C:\\Users\\Administrator\\Documents";
//
//		String filename = file.getOriginalFilename();
////		System.out.println("path & file:"+path + File.separator + filename);
//		byte[] bytes = file.getBytes();
//		BufferedOutputStream stream = new BufferedOutputStream(
//				//new FileOutputStream(new File(path + File.separator + filename)));
//				new FileOutputStream(new File(path2 + File.separator + filename)));
//		stream.write(bytes);
//		stream.flush();
//		stream.close();
//
//		return new ModelAndView("success", "filesuccess", path2 + File.separator + filename);
//	}
}
