package org.ilh.gcabint.service;

import java.util.List;

import org.ilh.gcabint.entities.rdv;

public interface rdvService {

	List<rdv> rdvByClient(String mc);

}
