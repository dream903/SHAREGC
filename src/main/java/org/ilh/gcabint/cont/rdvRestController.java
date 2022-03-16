package org.ilh.gcabint.cont;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.ilh.gcabint.entities.h2;
import org.ilh.gcabint.entities.hd;
import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.dispoRepo;
import org.ilh.gcabint.repo.h2Repo;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rdv")
public class rdvRestController {
@Autowired
	private rdvRepo rdvr;
@Autowired
dispoRepo dispr;

@Autowired
h2Repo h2r;






@GetMapping(path="/h2/{mc}")
public List<h2> h2(@PathVariable String mc){

//	dispr.deleteAll();
		
List<h2> lst3=h2r.findByClient2date("%"+mc+"%");
return lst3;
}

@PostMapping(path="/save")
	public rdv saveRdv(@RequestBody rdv r){
	
	return rdvr.save(r);
}



   @GetMapping(path="/rdvs")
 	public List<rdv> findRdv(){
		return rdvr.findAll();
	}
 	
 	
 	
 	
	
	@GetMapping(path="/rdvs/{id}")
	public Optional<rdv> findRdvById(@PathVariable Long id){
		return rdvr.findById(id);}
	
	
	@GetMapping(path="/rdvscltnc/{mc}")
	public List<rdv> findRdvByClient(@PathVariable String mc){
		
		return rdvr.findByClient("%"+mc+"%");}
	
	
	
	@GetMapping(path="/rdvscltnf/{mc}")
	public List<rdv> findRdvByClientnf(@PathVariable String mc){
		
		return rdvr.findByClientnonfait("%"+mc+"%");}
	
	
	@GetMapping(path="/total/{mc}")
	public Double Total (@PathVariable String mc){
		double total=0;

		List<rdv> listerdv=rdvr.findByClient("%"+mc+"%");
		for(rdv r:listerdv) {
		total=total+	r.getPrestation().getTarif();
		}
		return total;
	}
	
	
	
	
	@GetMapping(path="/rdvscltbydate/{d}")
		public List<rdv> findByClientdate(@PathVariable Date d){
			return rdvr.findByClientdate(d);
		}
	
//	@GetMapping(path="/rdvsclt/{d}")

//	public List<rdv> findRdvByClienstr(@PathVariable String d){
//		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			return rdvr.findByClientdate(df.parse(d));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}}
	@GetMapping(path="/test1")
	public void heuredispo(){
	Map<Integer, String> HOSTING = new HashMap<>();
    HOSTING.put(1, "linode.com");
    HOSTING.put(2, "heroku.com");
    HOSTING.put(3, "digitalocean.com");
    HOSTING.put(4, "aws.amazon.com");

    // Before Java 8
    String result = "";
	result = HOSTING.entrySet().stream()
            .filter(map -> "aws.amazon.com".equals(map.getValue()))
            .map(map -> map.getValue())
            .collect(Collectors.joining());

    System.out.println("With Java 8 : " + result);

}
@GetMapping(path="/test2")
public void test (){
Map<Integer, String> HOSTING = new HashMap<>();
HOSTING.put(1, "linode.com");
HOSTING.put(2, "heroku.com");
HOSTING.put(3, "digitalocean.com");
HOSTING.put(4, "aws.amazon.com");

// Before Java 8
String result = "";


result = HOSTING.entrySet().stream()
        .filter(x -> {
            if (!x.getValue().contains("amazon") && !x.getValue().contains("digital")) {
                return true;
            }
            return false;
        })
        .map(map -> map.getValue())
        .collect(Collectors.joining(","));

System.out.println("With Java 8 : " + result);


//chercher a partir de la date fixe les disponiblites dans la table des rdv
//	datei heures.heures..heures...heures...heures..heures...heures.
//	datei+1 heures......
}


}
