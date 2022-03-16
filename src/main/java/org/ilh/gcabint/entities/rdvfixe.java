package org.ilh.gcabint.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class rdvfixe  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3443801866161157790L;
	@Id
	@GeneratedValue
	private Long idRDV;
	@Temporal(TemporalType.DATE)
	private Date dateRdv;
	@Column(name="fait")
	private boolean fait=true;
	
	@OneToOne
	@JoinColumn(name="idClient")
	private Client client;
	
	@OneToOne
	@JoinColumn(name="codePrest")
	private Prestation prestation;
	
	@OneToOne
	@JoinColumn(name="numH")
	private Heure heure;

	//private Long heure
}
