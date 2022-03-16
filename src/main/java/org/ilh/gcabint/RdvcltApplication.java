package org.ilh.gcabint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.Prestation;
import org.ilh.gcabint.entities.h2;
import org.ilh.gcabint.entities.hd;
import org.ilh.gcabint.entities.rdvfixe;
import org.ilh.gcabint.repo.clientRepository;
import org.ilh.gcabint.repo.dispoRepo;
import org.ilh.gcabint.repo.h2Repo;
import org.ilh.gcabint.repo.prestationRepository;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RdvcltApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdvcltApplication.class, args);
	}
	@Bean
	CommandLineRunner start(clientRepository cltrep,prestationRepository prep,rdvRepo rdvrepo,dispoRepo dispr,
			h2Repo h2r
){

	return args->{
//		cltrep.save(new Client(null,"adam","","51519852"));
//
//		cltrep.save(new Client(null,"ahmed","","24030099"));
//
//		cltrep.save(new Client(null,"sousou","","52553169"));
//		---------------------------------------------------------------
//		prep.save(new Prestation(null,"P1",100.0));
//		prep.save(new Prestation(null,"P2",80.0));
//
//		prep.save(new Prestation(null,"P3",60.0));
//		prep.save(new Prestation(null,"P4",70.0));
//		-------------------------------------------------------------
		Client clt2=cltrep.getOne(775L);
		Client clt1=cltrep.getOne(776L);
//------------------------------------------------
		Prestation prest2=prep.getOne(411L);
//		-------------------------------------------------
		
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//		rdvrepo.save(new rdvfixe(null,df.parse("2021-11-9"),false,clt2,prest2));
//		rdvrepo.save(new rdvfixe(null,new Date(),true,clt1,prest2));
//		
//		dispr.save(new hd(null,new Date(),1L,"dispo"));
//		dispr.save(new hd(null,df.parse("2021-11-8"),2L,"dispo"));
//		dispr.save(new hd(null,new Date(),4L,"dispo"));
//
//		h2r.save(new h2(null,"2021-11-8",1L,"dispo"));
//		h2r.save(new h2(null,"2021-11-8",2L,"dispo"));
////		h2r.save(new h2(null,"2021-11-8",4L,"dispo"));
//		List<rdvfixe> rdvs=rdvrepo.findAll();
//		for(rdvfixe r:rdvs)
//			{
//			System.out.println("rendez vous :"+r.getClient().getName()+"  "+r.getPrestation().getLib()+"date"+r.getDateRdv())	;
//			}
	};

	}


}
