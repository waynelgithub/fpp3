package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	private static final String VirtualPath = "/upload";
	private static final String UPLOAD_DIRECTORY = "/images";
	// 上傳路徑一定是 virtual path!
	
//	@RequestMapping(value = "/upload",method = RequestMethod.GET)
//	public ModelAndView uploadForm() {
//		return new ModelAndView("fileUpload");
//	}
	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	//public ModelAndView saveimage(@RequestParam CommonsMultipartFile file, HttpSession session)
	public ModelAndView saveimage(@RequestParam MultipartFile file, HttpSession session)
			throws Exception {
		ServletContext context = session.getServletContext();
		String path = context.getRealPath(UPLOAD_DIRECTORY);
		File tmpFile = new File(path);
		if (!tmpFile.exists()) {
			tmpFile.mkdir();
		}
		String filename = file.getOriginalFilename();
		System.out.println(path + File.separator + filename);
		byte[] bytes = file.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(path + File.separator + filename)));
		stream.write(bytes);
		stream.flush();
		stream.close();

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
