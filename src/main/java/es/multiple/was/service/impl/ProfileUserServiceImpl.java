package es.multiple.was.service.impl;

import java.util.List;

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
		List<ProfileUserEntity> profiles = profileUserRepository.findAll();
		return !profiles.isEmpty() ? profiles.get(0).getProfileName() : "null";
	}
	
	@Override
	public void save(ProfileUserEntity entity) {
		profileUserRepository.save(entity);
	}
}
