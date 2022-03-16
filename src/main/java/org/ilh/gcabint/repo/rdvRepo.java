package org.ilh.gcabint.repo;

import java.util.Date;
import java.util.List;

import org.ilh.gcabint.entities.rdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface rdvRepo extends JpaRepository<rdv, Long>{
	@Query("select r from rdv r where r.client.name like :x and r.fait=1")
	List<rdv> findByClient(@Param("x") String mc);
	
	
	@Query("select r from rdv r where r.client.name like :x and r.fait=0")
	List<rdv> findByClientnonfait(@Param("x") String mc);
	
	
	@Query("select r from rdv r where r.dateRdv >=:x")
	List<rdv> findByClientdate(@Param("x")Date date);
	
	@Query("select r from rdv r where r.dateRdv like  :x")
	List<rdv> findByClientstr(@Param("x")String  date);
	
	@Query("select r from rdv r where r.dateRdv>  :x and r.dateRdv < :y")
	List<rdv> findByClient2date(@Param("x")Date d1,@Param("y")Date d2);

	@Query("select r from rdv r where r.client.idClient= :x ")
	rdv findByIdClient(@Param("x") Long id);
	
	
	
}