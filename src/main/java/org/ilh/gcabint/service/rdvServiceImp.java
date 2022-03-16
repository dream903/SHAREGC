package org.ilh.gcabint.service;

import java.util.List;

import org.ilh.gcabint.entities.rdv;
import org.ilh.gcabint.repo.rdvRepo;

public class rdvServiceImp implements rdvService

{
	private rdvRepo rdvr;

	@Override
	public List<rdv> rdvByClient(String mc) {
		// TODO Auto-generated method stub
		return rdvr.findByClient(mc);
	}

}
