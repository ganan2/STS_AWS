package com.devopsbuddy.backend.persistence.domain.backend;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.devopsbuddy.enums.PlansEnum;

@Entity
public class Plan implements Serializable {
	
	private static final long serialVersionID = 1L;
	
	@Id
	private int id;

	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/** Default constructor 
	 * Known default constructor isn't avilable to the JVM
	 * Hence, for entities, this will cause an error 
	 * Hence, more than one constructor is recommended */
	public Plan() {
		
	}
	
	public Plan(PlansEnum plansEnum) {
		this.id = plansEnum.getId();
		this.name = plansEnum.getPlanName();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Plan plan = (Plan) o;
		
		return id == plan.id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

}
