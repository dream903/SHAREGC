package org.ilh.gcabint.repo;

import java.util.Date;
import java.util.List;

import org.ilh.gcabint.entities.h2;
import org.ilh.gcabint.entities.rdvfixe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface h2Repo extends JpaRepository<h2, Long>{
	
		@Query("select r from h2 r where r.date  like :x")
	List<h2> findByClient2date(@Param("x")String d1);
	

}
