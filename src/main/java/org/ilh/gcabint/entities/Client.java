package org.ilh.gcabint.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long 	idClient;
	private String name;
	private String email;
	private String numTel;
	@Override
	public String toString() {
		
		return "Client [idClient=" + idClient + ", name=" + name + ", email=" + email + ", numTel=" + numTel + "]";
	}

	

}
