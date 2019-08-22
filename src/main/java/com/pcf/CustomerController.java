package com.pcf;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRespository customerRespository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@GetMapping(value = "/customer")
	public String getCustomer(@RequestParam String lastname) {
		Customer cust = customerRespository.findByLastName(lastname);
		System.out.println(cust+"=====================");
		if(cust == null) {
			return "customer not found";
		}
		else {return cust.toString();
		}
	}
	
	@GetMapping(value = "/rabbit")
	public void publish(@RequestParam String message) {
		rabbitTemplate.convertAndSend("myqueue", message);
	}
	
	@GetMapping(value = "/abc")
	public String get() {
		return "Hi there !!!!!!!!!!!!!!!!!";
	}
	
	@GetMapping(value = "/vcap")
	public String getVCAP() {
		return System.getenv("VCAP_SERVICES");
	}
	
	@PostMapping(value = "/customer")
	public String postCustomer(@RequestBody Customer customer) {
		Customer customer1 = customerRespository.save(customer);
		return  customer1.toString();
	}
}
