package es.multiple.was.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.multiple.was.ds2.entity.ProfileUserEntity;
import es.multiple.was.ds2.respository.ProfileUserRepository;
import es.multiple.was.service.ProfileUserService;

@Service
public class ProfileUserServiceImpl implements ProfileUserService {

	@Autowired
	@Qualifier("profileUserRepository")
	private ProfileUserRepository profileUserRepository;
	
	@Override
	public String getProfileName() {
		Optional<ProfileUserEntity> r = profileUserRepository.findById("1");
		return r.isPresent() ? r.get().getProfileName() : "null";
	}
	
	@Override
	public void save(ProfileUserEntity entity) {
		profileUserRepository.save(entity);
	}
}
