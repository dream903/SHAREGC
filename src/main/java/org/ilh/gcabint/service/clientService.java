package org.ilh.gcabint.service;

import java.util.Collections;
import java.util.List;

import org.ilh.gcabint.entities.Client;
import org.ilh.gcabint.repo.clientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class clientService {
	@Autowired
	private clientRepository cltrep;	
	
public Page<Client> findPaginated( Pageable pageable) {
    	
        List<Client> clts = cltrep.findAll(); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Client> list;

        if (clts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clts.size());
            list = clts.subList(startItem, toIndex);
        }

        Page<Client> bookPage = new PageImpl<Client>(list, PageRequest.of(currentPage, pageSize), clts.size());

        return bookPage;
    }

}
