package org.ilh.gcabint.repo;

import java.util.List;

import org.ilh.gcabint.entities.Heure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface heureRepo  extends JpaRepository<Heure, Integer>{
	@Query("select r from h2 r where r.date  like :x")
	List<Heure> findByClient2date(@Param("x")String d1);
}
