package org.ilh.gcabint.cont;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.clientRepository;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/api/ajax")
public class AjaxRestCont {
@Autowired
private rdvRepo rdvr;

@Autowired
private clientRepository cltrepo;





@RequestMapping(value="/demo2/{mc}",
method=RequestMethod.GET,
produces= {MimeTypeUtils.TEXT_PLAIN_VALUE}
)
public ResponseEntity<String> demo2(@PathVariable ("mc")String mc){
	
	
	
try {
	
		
		ResponseEntity<String> responseEntity=
				new ResponseEntity <String>("hi"+mc,HttpStatus.OK);
	
		
		return responseEntity;
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	



}














	
@RequestMapping(value="/demo3/{fullname}",
method=RequestMethod.GET,
produces= {MimeTypeUtils.APPLICATION_JSON_VALUE}
)
public ResponseEntity<List<rdv>> demo3(@PathVariable String fullname){
	
	
	
try {
	
		List<rdv> rdvs=
//				rdvr.findAll();
//				rdvs.forEach(r->System.out.println(r.getDateRdv()));
				rdvr.findByClientnonfait('%'+fullname+'%');
		
		ResponseEntity<List<rdv>> responseEntity=
				new ResponseEntity <List<rdv>>(rdvs,HttpStatus.OK);
	
		
		return responseEntity;
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
	return new ResponseEntity<List<rdv>>(HttpStatus.BAD_REQUEST);
	}
	



}




@RequestMapping(value="/demo4/{fullnam}",
method=RequestMethod.GET,
produces= {MimeTypeUtils.APPLICATION_JSON_VALUE}
)
public ResponseEntity<rdv> demo4(@PathVariable String fullnam){



try {


	ResponseEntity<rdv> responseEntity=
			new ResponseEntity <rdv>(rdvr.findAll().get(1),HttpStatus.OK);

	
	return responseEntity;
	
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
return new ResponseEntity<rdv>(HttpStatus.BAD_REQUEST);
}




}




@RequestMapping(value="/findClientByname/{fullnam}",
method=RequestMethod.GET,
produces= {MimeTypeUtils.APPLICATION_JSON_VALUE}
)
public ResponseEntity<Client> findClientByname(@PathVariable String fullnam) throws Exception{



try {

	ResponseEntity<Client> responseEntity=
			new ResponseEntity <Client>(cltrepo.findByname("%"+fullnam+"%"),HttpStatus.OK);

	
	return responseEntity;
	
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
}


}

@RequestMapping(value="/findByClientdate/{dateD}",
method=RequestMethod.GET,
produces= {MimeTypeUtils.APPLICATION_JSON_VALUE}
)
public ResponseEntity<List<rdv>> findByClientdate(@PathVariable Date dateD) throws Exception{



try {

	ResponseEntity<List<rdv>> responseEntity=
			new ResponseEntity <List<rdv>>(rdvr.findByClientdate(dateD),HttpStatus.OK);

	
	return responseEntity;
	
	
	
} catch (Exception e) {
	// TODO Auto-generated catch block
return new ResponseEntity<List<rdv>>(HttpStatus.BAD_REQUEST);
}




}


}



