package org.ilh.gcabint.cont;


import java.util.Arrays;
import java.util.List;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.Prestation;
import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.clientRepository;
import org.ilh.gcabint.repo.prestationRepository;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class RDVCont {
	@Autowired
	private rdvRepo rdvrepo;
	@Autowired
	private prestationRepository prepo;
	
	@GetMapping(path="/registerRdv")
	 public  String showForm(@Validated@ModelAttribute("rdv") rdv  rdv,Model model) throws Exception {
		

		model.addAttribute("rdv",rdv);
		List<Long> listCodePrest=Arrays.asList(411L,412L,413L,414L,415L);
		List<Prestation> listPrest=prepo.findAll();
//		hashmap

		model.addAttribute("listCodePrest", listCodePrest);
		model.addAttribute("listPrest", listPrest);
		return "rdv_form";
	}
	
	
	@PostMapping(path="/registerRdv")
	 public  String submitForm(@ModelAttribute("rdv") rdv rdv) throws Exception {
		System.out.println(rdv);
		rdvrepo.save(rdv);
		return "rdv_success";
	}
}
