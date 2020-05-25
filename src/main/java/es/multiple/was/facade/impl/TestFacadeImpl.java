package es.multiple.was.facade.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.multiple.was.ds1.entity.UserEntity;
import es.multiple.was.ds2.entity.ProfileUserEntity;
import es.multiple.was.facade.TestFacade;
import es.multiple.was.service.ProfileUserService;
import es.multiple.was.service.UserService;

@Service
public class TestFacadeImpl implements TestFacade {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileUserService profileUserService;
	
	@Transactional
	@Override
	public boolean testCreate(boolean fail) {
		
		ProfileUserEntity p = ProfileUserEntity.builder().idProfile(UUID.randomUUID().toString()).profileName("admin").build();
		profileUserService.save(p);
		
		String username = fail ? null : UUID.randomUUID().toString();
		UserEntity user = UserEntity.builder().username(username).password("password").build();
		userService.save(user);
		
		return true;
	}
}
