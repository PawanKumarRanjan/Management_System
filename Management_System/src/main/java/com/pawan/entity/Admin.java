package com.pawan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_name")
    private String name;
    
    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob;

    @Column(name = "email", unique = true)
    @Email(message="Email address is not valid")
    private String email;

    @Column(name = "mobile_number")
    @Size(min=10, message ="Mobile Number must be atleast of 10 digits")
    private String mobile;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "password")
    @Size(min=5, message ="Password must be atleast of 5 characters")
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin(Long id, String name, String gender, String dob,
			@Email(message = "Email address is not valid") String email,
			@Size(min = 10, message = "Mobile Number must be atleast of 10 digits") String mobile, String city,
			@Size(min = 5, message = "Password must be atleast of 5 characters") String password) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
		this.city = city;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", email=" + email
				+ ", mobile=" + mobile + ", city=" + city + ", password=" + password + "]";
	}
    
    public Admin()
    {
    	
    }
	
}
