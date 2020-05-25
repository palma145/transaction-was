package es.multiple.was.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.multiple.was.facade.TestFacade;
import es.multiple.was.service.impl.ProfileUserServiceImpl;
import es.multiple.was.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ProfileUserServiceImpl profileUserService;
	
	@Autowired
	private TestFacade testFacadeImpl;
	
	@GetMapping(value = "data")
	public String getData() {		
		return userService.getUsername() +" - "+ profileUserService.getProfileName();
	}
	
	@GetMapping(value = "create/{fail}")
	public String create(@PathVariable("fail") Boolean fail) {
		
		try {
			testFacadeImpl.testCreate(fail);
			return "OK";
		}catch(Exception e) {
			log.error("**** ERROR!! de transaccion", e);
			return "KO";
		}
	}
}
