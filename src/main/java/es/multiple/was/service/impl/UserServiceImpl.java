package es.multiple.was.service.impl;

import java.util.Optional;

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
		Optional<UserEntity> u = userRepository.findById("user");
		return u.isPresent() ? u.get().getUsername() : "null";
	}
	
	@Override
	public void save(UserEntity entity) {
		userRepository.save(entity);
	}
}
