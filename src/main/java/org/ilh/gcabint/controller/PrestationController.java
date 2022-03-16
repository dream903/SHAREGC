package org.ilh.gcabint.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.ilh.gcabint.repo.prestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ilh.gcabint.entities.*;
@Controller
@RequestMapping("/prestation")
public class PrestationController {
	@Autowired
	private prestationRepository prepo;
	
	
	private String list_template="/prestation/list-prestation";
	private String add_edit_template="/prestation/add-edit-prestation";
    private String list_redirect="redirect:/prestation/list";
    private String list_redirectprest="redirect:/prestation/index";

    
    
    @GetMapping("/index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int page,Pageable pageable) {
		
			int pageSize=5;
			int pageCourante = page;
			int startItem = pageCourante * pageSize;

			List<Prestation> list;
				List<Prestation> lstprest=prepo.findAll();
				if (lstprest.size() < startItem) { list = Collections.emptyList(); } else 
				{
					int toIndex = Math.min(startItem + pageSize, lstprest.size()); 
					
					list =lstprest.subList(startItem, toIndex); 
					
				
				}


			Page<Prestation> pagePrest=new PageImpl<Prestation>(list,PageRequest.of(pageCourante, 2),prepo.findAll().size());
			
			int count=pagePrest.getTotalPages();
			int[] pages=new int[count];
			for(int i=0;i<count;i++) {
				pages[i]=i;
			}
		model.addAttribute("pages", pages);
		model.addAttribute("pagePrest", pagePrest);
		model.addAttribute("pageCourante",page);

		
		return list_template;
	}


	@GetMapping("/list")
    public String Prestation(Model model) {
        List<Prestation> lisPrestations = prepo.findAll();
        model.addAttribute("lisPrestations", lisPrestations);

        return list_template;
    }
	
	@GetMapping("/add")
	public String addPrestation(Prestation prestation,Model model) {
		
		model.addAttribute("prestation", prestation);
		return add_edit_template;
	}
	
	
	@PostMapping("/save")
	public String savePrestation(@Valid @ModelAttribute Prestation prestation,Model model,BindingResult result) {
		model.addAttribute("prestation", prestation);
		if (result.hasErrors()) {
			return add_edit_template;
		}
		prepo.save(prestation);
		
        return list_redirectprest+"?success";

		
	}
	@GetMapping("/delete/{id}")
	public String deletePrestation(@PathVariable("id") Long id ,Model model) {
		Prestation prestation=prepo.getById(id);
		prepo.delete(prestation);
		model.addAttribute("Presation", prestation);
		
		 return list_redirectprest+"?deleted";
	}
	
	@GetMapping("/edit/{id}")
	public String editPrestation(@PathVariable("id")Long id,Model model) {
		Prestation prestation=prepo.getById(id);
		model.addAttribute("prestation", prestation);
		return add_edit_template;
	 
		
	}
	
	
} 
