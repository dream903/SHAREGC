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
import org.ilh.gcabint.entities.rdvfixe;
import org.ilh.gcabint.repo.dispoRepo;
import org.ilh.gcabint.repo.h2Repo;
import org.ilh.gcabint.repo.rdvRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/demo")
public class demoController {
@Autowired
	private rdvRepo rdvr;
@Autowired
dispoRepo dispr;

@Autowired
h2Repo h2r;

@RequestMapping(method=RequestMethod.GET)
public String index() {
	return "demo/demo1";
}

}

