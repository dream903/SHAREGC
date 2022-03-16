package org.ilh.gcabint.cont;

import java.util.List;
import java.util.Optional;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.repo.clientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping(path="/adminclt")
public class ClientRestController {
	@Autowired
	private clientRepository cltrep;
	
	@RequestMapping(value="/saveClt",method=RequestMethod.GET)
	public Client saveClient(Client clt) {
		return cltrep.save(clt);
	}
	
	
	@GetMapping(path="/clients")
	public List<Client> findClient() {
		return cltrep.findAll();
	}
	

	@GetMapping(path="/clients/{id}")
	public Optional<Client> findbyClient(@PathVariable Long id) {
		return cltrep.findById(id);
	}
	
	//create client rest api
	@PostMapping("/clients")
	public Client createClient(@RequestBody Client client) {
		return cltrep.save(client);
	}

//	@RequestMapping(value="/editClt",method=RequestMethod.GET)
//	public Client editClient(Client clt) {
//		return cltrep.save(clt);
//	}

//	@GetMapping(path="/findbyId/{id}")
//	public Client findClient(@PathVariable Long id) {
//		return cltrep.getOne(id);
//	}
}

