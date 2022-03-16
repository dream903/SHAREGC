package org.ilh.gcabint.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class hd {
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private Long heure;
	private String dispo;
}
