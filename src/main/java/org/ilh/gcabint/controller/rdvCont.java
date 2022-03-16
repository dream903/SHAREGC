package org.ilh.gcabint.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.Heure;
import org.ilh.gcabint.entities.Prestation;
import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.clientRepository;
import org.ilh.gcabint.repo.heureRepo;
import org.ilh.gcabint.repo.prestationRepository;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rdvs")
public class rdvCont {
	@Autowired
	private rdvRepo rdvrepo;
	@Autowired
	private prestationRepository prepo;
	@Autowired
	private clientRepository cltrep;
	@Autowired
	private heureRepo hrepo;
	
	
    private String list_template="/rdv/list-rdv";
	private String add_edit_template="/rdv/add-edit-rdv";
    private String list_redirectrdv="redirect:/rdvs/index";



	private Map<String, String> findHD() {
		 LocalDateTime myDateObj = LocalDateTime.now();  
		    System.out.println("Before Formatting: " + myDateObj);  
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		    
		    String formattedDate = myDateObj.format(myFormatObj);  
		    System.out.println("After Formatting: " + formattedDate);  
		    String d1=formattedDate;
		    Date d2=new Date();
		 List<rdv> lrdvs =rdvrepo.findByClientdate(d2);
		 ArrayList<Integer> listehp=new ArrayList<Integer>();
		 HashMap< String,Date> listehpr= new HashMap< String,Date>();

		
//		 listehp.add(0); listehp.add(1);listehp.add(2);listehp.add(3);
		 HashMap< Integer,String> listeh = new HashMap< Integer,String>();
		 HashMap< Integer,String> listeres = new HashMap< Integer,String>();
		 HashMap< String,String> listeres2 = new HashMap< String,String>();

		listeh.put(1,d1);
		listeh.put(2,d1);
		listeh.put(3,d1);
		listeh.put(4,d1);
		listeh.put(5,d1);
		listeh.put(6,d1);
		listeh.put(7,d1);
		listeh.put(8,d1);
		listeh.put(9,d1);
		
		listeres.put(1,d1);
		listeres.put(2,d1);
		listeres.put(3,d1);
		listeres.put(4,d1);
		listeres.put(5,d1);
		listeres.put(6,d1);
		listeres.put(7,d1);
		listeres.put(8,d1);
		listeres.put(9,d1);
		
		 lrdvs.stream().forEach(e->listehp.add(e.getHeure().getNumH()));
//		 {listehpr.put(e.getHeure().getLibH(),e.getDateRdv());
		 
			for (Integer i : listeh.keySet()) {
//			  System.out.println("key: " + i + " value: " + listeh.get(i));
			  for (int j=1;j<listehp.size();j++) {
				  if (i==j) {
				  System.out.println("contenu de hashmap: " +  i + " j: " + listehp.get(j));
				  listeres.remove(i);

				  }
				

//				  if(i==listehp.get(j)){
//					  listeh.remove(i);
//					  
//				  }
			  }
			}
		for (Integer i : listeres.keySet()) {
			
			String lib=hrepo.getById(i).getLibH();
			listeres2.put(lib, listeres.get(i));
			
			  System.out.println("heure diisponible: " + lib + " dans cette date: " + listeres.get(i));
			  
			  
			}

	return 	listeres2;
}

	@GetMapping("/index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int page,Pageable pageable) {
		
			int pageSize=5;
			int pageCourante = page;
			int startItem = pageCourante * pageSize;

			List<rdv> list;
				List<rdv> lstrdv=rdvrepo.findAll();
				if (lstrdv.size() < startItem) { list = Collections.emptyList(); } else {
					int toIndex = Math.min(startItem + pageSize, lstrdv.size()); 
					list =lstrdv.subList(startItem, toIndex); }

			Page<rdv> pageRdv=new PageImpl<rdv>(list,PageRequest.of(pageCourante, 5),lstrdv.size());
			int count=pageRdv.getTotalPages();
			
			int[] pages=new int[count];
			for(int i=0;i<count;i++) {
				pages[i]=i;
			}
		model.addAttribute("pages", pages);
		model.addAttribute("pageRdv", pageRdv);
		model.addAttribute("pageCourante",page);

		return list_template;
	}
	
	
	@PostMapping("/save")
	public String savePrestation(@Valid @ModelAttribute rdv rdv,Model model,BindingResult result) {
		model.addAttribute("rdv", rdv);
		if (result.hasErrors()) {
			return add_edit_template;
		}
		rdvrepo.save(rdv);
		
        return list_redirectrdv+"?success";

		
	}
	@GetMapping("/add")
	public String addrdv(rdv rdv,Model model) {
		
		model.addAttribute("rdv", rdv);
		 List<Prestation> prestations = prepo.findAll();
	     model.addAttribute("prestations",prestations);
	 	List<Heure> heures=hrepo.findAll();
	    model.addAttribute("heures",heures);
		return add_edit_template;
	}
	@GetMapping("/addrdvclt/{id}")
	public String addrdvclt(@PathVariable("id") Long id,rdv rdv,Model model) {
		Client clt=cltrep.getById(id);
//		model.addAttribute("rdvsclt",rdvsclt);
		rdv.setClient(clt);
		model.addAttribute("rdv", rdv);
		List<Prestation> prestations = prepo.findAll();
	   	model.addAttribute("prestations",prestations);
		List<Heure> heures=hrepo.findAll();
	    model.addAttribute("heures",heures);

		return add_edit_template;
	}

	@GetMapping("/delete/{id}")
	public String deletePrestation(@PathVariable("id") Long id ,Model model) {
		rdv rdv=rdvrepo.getById(id);
		rdvrepo.delete(rdv);
		model.addAttribute("rdv", rdv);
		
		 return list_redirectrdv+"?deleted";
	}
	
	@GetMapping("/edit/{id}")
	public String editPrestation(@PathVariable("id")Long id,Model model) {
		rdv rdv=rdvrepo.getById(id);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		rdv.setDateRdv(dateFormat.parse(rdv.getDateRdv()));
		model.addAttribute("rdv", rdv);
		List<Prestation> prestations = prepo.findAll();
	
	    model.addAttribute("prestations",prestations);
		List<Heure> heures=hrepo.findAll();
	    model.addAttribute("heures",heures);
	    Map<String,String> HD=findHD();
	    List<Map<String, String>> mapsList = new ArrayList<Map<String, String>>();

	   

	    mapsList.add(HD);

	    model.addAttribute("mapsList",mapsList);
		return add_edit_template;
	 
		
	}
	

}
