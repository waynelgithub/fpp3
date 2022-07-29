package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.EmployeeListVO;
import com.example.demo.model.HousePic;

@RestController
public class FileUploadController {

	private static final String IMAGE_DIRECTORY = "houseimages";
	// 上傳路徑一定是個 virtual path!
	

	List<HousePic> hps = new ArrayList<>();

	
	
	
	public FileUploadController() {
		HousePic hp1 = new HousePic(1, 1, "houseimages", "1_1.jpg", 1, "jpg");
		HousePic hp2 = new HousePic(2, 2, "houseimages", "2_1.jpg", 1, "jpg");
		HousePic hp3 = new HousePic(3, 2, "houseimages", "2_6.jpg", 6, "jpg");
		hps.add(hp1);
		hps.add(hp2);
		hps.add(hp3);

	}
	
	@RequestMapping(value = "/getimage/{no}",method = RequestMethod.GET)
	public ModelAndView getImage1(@PathVariable String no) {
		return new ModelAndView("redirect:/houseimages/"+no+".jpg");
	}
	@GetMapping (value="/getimage")
	public  List<HousePic> getImage2(ModelMap model) {

		return hps;
	}
	
	
	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	//public ModelAndView saveimage(@RequestParam CommonsMultipartFile file, HttpSession session)
	public ModelAndView saveimage(@RequestParam MultipartFile file, @RequestParam String houseid,HttpSession session)
			throws Exception {
		//get ServletContext
		ServletContext context = session.getServletContext();
		//get server real path related to webapp's virtual path 
		String path = context.getRealPath("/"+IMAGE_DIRECTORY);
		
		File _path = new File(path);
		if (!_path.exists()) {
			_path.mkdir();
		}
		String filename = file.getOriginalFilename();

	//增加改名程序
		//取得houseid
		String houseId= houseid;
	//取得picListNo
		//取得一個Integer array, 代表從db取得的該houseId 圖片的 picListNo array
		Integer[] listNoTemp = {0,1,2,3,4,5,6,8,9,10,11,12,13}; //要從DB取得[] 
		List<Integer> piclistNos= new ArrayList<Integer>(Arrays.asList(listNoTemp));
		
		//array listNos size() < 上限(15)，則可以增加圖片
		Integer upperLimit = 15;
		Integer picListNo=-1;
		
		if (piclistNos.size()<upperLimit)
		{
			//比對picListNo, 找到還未使用的picListNo來使用
				
				//1.迴圈法
				for (Integer no =0; no <upperLimit; no++)			
				{
//					int flag=0;
//					for(Integer pNo: piclistNos)
//					{
//						if (no == pNo) {
//							flag=1;
//							break;						
//						}
//					}
//					if (flag==1) continue;
//					
//					picListNo= no;
//					break;

					
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
		//改名程序完
			
			
			
			//System.out.println(path + File.separator + filename);
			System.out.println("new path:"+newPathName);
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(newPathName)));
			stream.write(bytes);
			stream.flush();
			stream.close();


		}
		else {
			
			System.out.println("Exceed image limitation.");
		}

		
		
		return new ModelAndView("success", "filesuccess", path + File.separator + filename);
	}
	
	
	@RequestMapping(value = "/savefile2", method = RequestMethod.POST)
	//public ModelAndView saveimage2(@RequestParam CommonsMultipartFile file, HttpSession session)
	// HttpSession session 也就沒必要
	public ModelAndView saveimage2(@RequestParam CommonsMultipartFile file)
			throws Exception {
		
//		ServletContext context = session.getServletContext();
//		String path = context.getRealPath(VirtualPath);
//		System.out.println("Real Path:"+path);
//			File tmpFile = new File(path);
//			if (!tmpFile.exists()) {
//				tmpFile.mkdir();
//			}
		//以上這些可以不用，只是為了demo
		//但以下這些都變成只在真實路徑上作業，其實有安全疑慮
		String path2= "C:\\Users\\Administrator\\Documents";

		String filename = file.getOriginalFilename();
//		System.out.println("path & file:"+path + File.separator + filename);
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				//new FileOutputStream(new File(path + File.separator + filename)));
				new FileOutputStream(new File(path2 + File.separator + filename)));
		stream.write(bytes);
		stream.flush();
		stream.close();

		return new ModelAndView("success", "filesuccess", path2 + File.separator + filename);
	}
}
