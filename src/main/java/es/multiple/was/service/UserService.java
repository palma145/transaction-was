package es.multiple.was.service;

import es.multiple.was.ds1.entity.UserEntity;

public interface UserService {

	void save(UserEntity entity);
	String getUsername();
}
