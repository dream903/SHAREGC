package org.ilh.gcabint.cont;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ilh.gcabint.entities.h2;
import org.ilh.gcabint.entities.hd;
import org.ilh.gcabint.entities.rdvfixe;
import org.ilh.gcabint.repo.dispoRepo;
import org.ilh.gcabint.repo.h2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/h")

public class dispController {
	@Autowired
	dispoRepo dispr;
	@Autowired
	h2Repo h2r;


@RequestMapping(value="/hdispo/{mc}")
public String dispo(Model model,@PathVariable String mc){

//		dispr.deleteAll();
			
List<h2> lst3=h2r.findByClient2date("%"+mc+"%");
model.addAttribute("hdispo",lst3);
return "hdispo";
}

}



























//ArrayList<Integer> lst1 = new ArrayList<Integer>( Arrays.asList(4,3,8));
//int[] tableau = {4,5,6,7};	
//for (Integer elt:lst1) {
//
//		int pos=java.util.Arrays.binarySearch(tableau, elt);
//		if (pos<0) {
//			System.out.println(elt+" disponible");
//		} else {
//			System
//			.out.println(elt+"non dispo");
//		
//		}
//}