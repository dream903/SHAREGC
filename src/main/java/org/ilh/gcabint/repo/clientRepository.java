package org.ilh.gcabint.repo;

import java.util.List;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.rdvfixe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface clientRepository extends JpaRepository<Client, Long>{

	
	
	@Query(value="select * from Client c where name like %:x% ", nativeQuery=true)
	Client findByname(@Param("x") String mc);

	@Query(value="select * from Client c where name like %:x% ", nativeQuery=true)
	List<Client> searchClient(@Param("x")String search);
	
	
}

