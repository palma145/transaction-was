package es.multiple.was.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.multiple.was.ds1.entity.UserEntity;
import es.multiple.was.ds1.respository.UserRepository;
import es.multiple.was.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public String getUsername() {
		List<UserEntity> users = userRepository.findAll();
		return !users.isEmpty() ? users.get(0).getUsername() : "null";
	}
	
	@Override
	public void save(UserEntity entity) {
		userRepository.save(entity);
	}
}
