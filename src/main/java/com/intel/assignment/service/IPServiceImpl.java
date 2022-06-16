package com.intel.assignment.service;

/**
 * Service class connect to Repo and perform DB operations 
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intel.assignment.data.IPRepository;
import com.intel.assignment.model.IPInfo;

@Service
public class IPServiceImpl implements IPService {

	@Autowired
	private IPRepository ipRepo;
	
	@Override
	public Long addIp(IPInfo ip) {
		return ipRepo.save(ip).getId();
	}

	@Override
	public List<IPInfo> findByIpValue(String value) {
		return ipRepo.findByValue(value);
	}
}
