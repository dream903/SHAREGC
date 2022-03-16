package org.ilh.gcabint.controller;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.entities.Prestation;
import org.ilh.gcabint.entities.rdvfixe;
import org.ilh.gcabint.repo.clientRepository;
import org.ilh.gcabint.repo.prestationRepository;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/client")
public class ClientCont {
	@Autowired
	private clientRepository cltrep;
	
	
	@Autowired
	private prestationRepository prepo;
	
	@Autowired
	private rdvRepo rrepo;
	
	@Value("${welcome.message}")
	private String message;
	@Value("${error.message}")
	
	private String errorMessage;
	private String list_redirectrdv="redirect:/rdvs/add";
    private String list_redirectclt="redirect:/client/listclt";
    private String list_redirect="redirect:/client/index";
    private String lst_redirect="redirect:/client/list";
    
    private String list_template="/client/list-client";
    private String list_templateclt="/client/add-edit-clt";
    private String add_edit_templateclt="/client/add-edit-clt";

    
    
    
    @GetMapping("/home")
    public String home() {
       
        return "index";
    }
    
    @GetMapping("/list")
    public String listClt(Model model) {
        List<Client> clts = cltrep.findAll();
        model.addAttribute("clts",clts);
        return list_template;
    }
    
    @GetMapping("/findByName")
    public String findPaginated(String search,Model model,@RequestParam(name="page",defaultValue="0") int page, Pageable pageable) {
      if (search!=null) {

      
    	List<Client> clts = cltrep.searchClient(search); //repo.findAll();
    	int pageSize=5;
		int pageCourante = page;
		int startItem = pageCourante * pageSize;
        List<Client> list;

        if (clts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize,clts.size());
            list = clts.subList(startItem, toIndex);
        }

		Page<Client> pageClt=new PageImpl<Client>(list,PageRequest.of(pageCourante, 5),cltrep.findAll().size());
		int count=pageClt.getTotalPages();
		
		int[] pages=new int[count];
		for(int i=0;i<count;i++) {
			pages[i]=i;
		}
	model.addAttribute("pages", pages);
	model.addAttribute("pageClt", pageClt);
	model.addAttribute("pageCourante",page);
      }
      else
      {
    	  
      	List<Client> clts = cltrep.findAll(); //repo.findAll();
      	int pageSize=5;
		int pageCourante = page;
		int startItem = pageCourante * pageSize;
        List<Client> list;

        if (clts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize,clts.size());
            list = clts.subList(startItem, toIndex);
        }

		Page<Client> pageClt=new PageImpl<Client>(list,PageRequest.of(pageCourante, 5),cltrep.findAll().size());
		int count=pageClt.getTotalPages();
		
		int[] pages=new int[count];
		for(int i=0;i<count;i++) {
			pages[i]=i;
		}
	model.addAttribute("pages", pages);
	model.addAttribute("pageClt", pageClt);
	model.addAttribute("pageCourante",page);


      }
    		
	return list_template;
	}

    
	@GetMapping("/index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int page,Pageable pageable) {
		
			int pageSize=5;
			int pageCourante = page;
			int startItem = pageCourante * pageSize;

			List<Client> list;
				List<Client> lstclt=cltrep.findAll();
				
				if (lstclt.size() < startItem)
				{ list = Collections.emptyList(); } 
				else
				{
					int toIndex = Math.min(startItem + pageSize, lstclt.size()); 
					list =lstclt.subList(startItem, toIndex);
				}

			Page<Client> pageClt=new PageImpl<Client>(list,PageRequest.of(pageCourante, 5),cltrep.findAll().size());
			int count=pageClt.getTotalPages();
			
			int[] pages=new int[count];
			
			for(int i=0;i<count;i++) {
				pages[i]=i;
			}
		model.addAttribute("pages", pages);
		model.addAttribute("pageClt", pageClt);
		model.addAttribute("pageCourante",page);

		
		return list_template;
	}
	 
		

		
	
	  @PostMapping("/save")
	    public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model){
		  
	        model.addAttribute("client", client);
	        List<Prestation> prestations = prepo.findAll();
	        model.addAttribute("prestations",prestations);

	        if(result.hasErrors()){
	            return add_edit_templateclt;
	        }

	        cltrep.save(client);
	        return list_redirect+"?success";
	  
	    }
		
		
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") long id, Model model) {

		cltrep.deleteById(id);

		return list_redirect+"?deleted";
    }
    
    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable("id") long id, Model model) {
    	Client client=cltrep.getById(id);
    	model.addAttribute("client", client);
    	
    	List<Prestation> prestations = prepo.findAll();
        model.addAttribute("prestations",prestations);
        
    	return add_edit_templateclt;
    }
    
    
    
    
    
   
    
    @GetMapping("/lay")
    public String lay(Model model) {
      
        return "layout";
    }
    
  
    
    
 
    
    @GetMapping("/add")
    public String addclient(Client client, Model model){
        model.addAttribute("client", client);
        List<Prestation> prestations = prepo.findAll();
        model.addAttribute("prestations",prestations);

        return add_edit_templateclt;
    }
    
    
    
    


    
	@GetMapping("/register")
	public String showForm(@Validated@ModelAttribute("client") Client client,Model model) {
	
		model.addAttribute("client",client);

		
		return "client/register_form";
	}
	
	@PostMapping(path="/register")
	public String submitForm(@ModelAttribute("client") Client client,Model model) {
		
		cltrep.save(client);
		return "client/register_success";
		
		
	}

    
    
    
    
}

//Client client=new Client();
/*
 * String name=client.getName(); name=""; if ( name.length() == 0) {
 * 
 * 
 * model.addAttribute("errorMessage", errorMessage); }
 */