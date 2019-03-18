package com.andreearosu.HibernateSpringApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size; 

@Entity
@Table(name="categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Cat_id; 
 
	@NotNull
	@Size(max=100)
	@Column(unique=true)
	private String name;
	
	@NotNull
	@Size(max=250)
	private String description;

	public long getId() {
		return Cat_id;
	}

	public void setId(long id) {
		this.Cat_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}