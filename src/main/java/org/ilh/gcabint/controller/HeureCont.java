

package org.ilh.gcabint.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/heure")
public class HeureCont {
	
	@RequestMapping("/HD")
private String findHD(Date d1) {
		HashMap<Date, Integer> listeh = new HashMap<Date, Integer>();
		listeh.put(d1, 1);
		listeh.put(d1, 2);
		listeh.put(d1, 3);
		listeh.put(d1, 4);
		listeh.put(d1, 5);
		listeh.put(d1, 6);
		listeh.put(d1, 7);
		listeh.put(d1, 8);
		listeh.put(d1, 9);
		

	return "";
}
}
