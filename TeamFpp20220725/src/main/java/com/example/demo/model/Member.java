package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Member")
public class Member {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long memberid;
	 
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String mobile;
    private String ownerflag;
    private int	points;
   
	public Member() {
    }
    
	public Member(String username, String password, String email, String name, String phone, String mobile,
			String ownerflag, int points) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.mobile = mobile;
		this.ownerflag = ownerflag;
		this.points = points;
	}

	public Long getMemberid() {
		return memberid;
	}

	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	 
    public String getOwnerflag() {
		return ownerflag;
	}

	public void setOwnerflag(String ownerflag) {
		this.ownerflag = ownerflag;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", phone=" + phone + ", mobile=" + mobile + ", ownerflag=" + ownerflag
				+ ", points=" + points + "]";
	}    
    
}
