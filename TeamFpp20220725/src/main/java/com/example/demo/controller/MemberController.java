package com.example.demo.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.example.demo.model.MemberDAO;
import com.example.demo.model.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	  @Autowired
	   MemberDAO dao;
	  // 後台 for 管理者 
	  @RequestMapping(value = "/memberCreate", method = RequestMethod.GET)
	    public ModelAndView openFormCreate() {
	       ModelAndView model = new ModelAndView("memberCreate");
	       return model;
	    }
	    @RequestMapping(value = "/memberCreate", method = RequestMethod.POST)
	    public ModelAndView processFormCreate(@ModelAttribute Member mem) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/memberRetrieveAll");
	       dao.save(mem);
	       return model;
	    }
	    
	 @RequestMapping(value = {"/memberRetrieveAll", "/member" }, method = RequestMethod.GET)
	    public ModelAndView retrievemembers() throws SQLException{
	       Iterable<Member> members = dao.findAll();

	       ModelAndView model = new ModelAndView("memberList");
	       model.addObject("members",members);
	       return model;
	    }
	 
	   @RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
	    public ModelAndView openFormUpdate(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("memberUpdate");
	       //Member member = dao.findById(id).get();
	       Member member =dao.findOne(id);
	       model.addObject("member",member);
	       return model;
	    }
	    @RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	    public ModelAndView processFormUpdate(@ModelAttribute Member mem) throws SQLException {
	       ModelAndView model = new ModelAndView("redirect:/memberRetrieveAll");
	       dao.save(mem);  
	       return model;
	    }    

	 @RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
	    public ModelAndView deletemember(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
	       ModelAndView model = new ModelAndView("redirect:/memberRetrieveAll");
	       dao.deleteById(id);
	       return model;
	    }
	 
	 // 前台
	 @RequestMapping(value = "/acctlogin", method = RequestMethod.GET)
	    public ModelAndView openlogin() {
	       ModelAndView model = new ModelAndView("acctlogin");
	       return model;
	    }

	  @RequestMapping(value = "/acctlogin", method = RequestMethod.POST)
	    public ModelAndView processlogin(@RequestParam("username") String user,@RequestParam("password") String pwd, HttpSession ses) throws SQLException {
	    	Member member =dao.findbyUsername(user);
	    	if (member != null && (member.getPassword().equals(pwd)) ) {
	    		System.out.println("login success!");
	    		ModelAndView model = new ModelAndView("loginSuccess");
	    		ses.setAttribute("M", member);
	    		return model;
	    	} 
		    System.out.println("login fail!");
	    	ModelAndView model = new ModelAndView("acctlogin");
		    return model;
	    }   
	  
	   @RequestMapping(value = "/acctUpdate", method = RequestMethod.GET)
	    public ModelAndView AcctUpdate(HttpSession ses) {
	       ModelAndView model = new ModelAndView("acctUpdate");
	       Member m1=(Member)ses.getAttribute("M");
	       Member member =dao.findOne(m1.getMemberid());
	       model.addObject("member",member);
	       return model;
	    }
	    @RequestMapping(value = "/acctUpdate", method = RequestMethod.POST)
	    public ModelAndView AcctUpdate(@ModelAttribute Member mem) throws SQLException {
	       ModelAndView model = new ModelAndView("loginSuccess");
	       dao.save(mem);  
	       return model;
	    }    
}
