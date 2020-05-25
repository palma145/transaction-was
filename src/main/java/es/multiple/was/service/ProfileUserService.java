package es.multiple.was.service;

import es.multiple.was.ds2.entity.ProfileUserEntity;

public interface ProfileUserService {

	void save(ProfileUserEntity entity);
	String getProfileName();
}
