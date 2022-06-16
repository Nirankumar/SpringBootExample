package com.intel.assignment.service;

import java.util.List;

import com.intel.assignment.model.IPInfo;

public interface IPService {
	Long addIp(IPInfo ip);
	List<IPInfo> findByIpValue(String value);
}
