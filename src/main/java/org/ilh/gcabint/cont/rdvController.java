package org.ilh.gcabint.cont;

import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class rdvController {
	@Autowired
	private rdvRepo rdvr;
	
	@RequestMapping("/")
public String Index(){
	return "index";
}
	
	
	@RequestMapping(value="/saverdv",method=RequestMethod.POST)
		public rdv save(@ModelAttribute rdv rdv,Model model) {
		rdv=new rdv();
		model.addAttribute("rdv",rdv);
		return rdvr.save(rdv);
	}
	
}
