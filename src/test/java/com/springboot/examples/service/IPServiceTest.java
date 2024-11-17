package com.springboot.examples.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.springboot.examples.data.IPRepository;
import com.springboot.examples.model.IPInfo;
import com.springboot.examples.service.IPService;
import com.springboot.examples.service.IPServiceImpl;

class IPServiceTest {
	private IPService ipservice;
	private IPRepository iprepo;
	
	@BeforeEach
	public void setup() {
		ipservice = new IPServiceImpl();
		iprepo = Mockito.mock(IPRepository.class);
		ReflectionTestUtils.setField(ipservice, "ipRepo", iprepo);
	}
	
	@Test
	void saveTest() {
		IPInfo ip = new IPInfo();
		ip.setId(2L);
		Mockito.when(iprepo.save(Mockito.any(IPInfo.class))).thenReturn(ip);
		long result = ipservice.addIp(new IPInfo());
		Assert.assertSame(2L, result);
	}
	
	@Test
	void findByValueTest() {
		IPInfo ip = new IPInfo();
		ip.setId(2L);
		ip.setValue("1.2.3.4");
		List<IPInfo> ipList =new ArrayList<>();
		ipList.add(ip);
		Mockito.when(iprepo.findByValue(Mockito.anyString())).thenReturn(ipList);
		Assert.assertSame(1, ipservice.findByIpValue("1.2.3.4").size());
	}
}
