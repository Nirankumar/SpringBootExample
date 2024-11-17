package com.springboot.examples.service;

import java.util.List;

import com.springboot.examples.model.IPInfo;

public interface IPService {
	Long addIp(IPInfo ip);
	List<IPInfo> findByIpValue(String value);
}
