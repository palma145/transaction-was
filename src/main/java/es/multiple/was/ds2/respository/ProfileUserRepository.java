package es.multiple.was.ds2.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.multiple.was.ds2.entity.ProfileUserEntity;

@Repository("profileUserRepository")
public interface ProfileUserRepository extends JpaRepository<ProfileUserEntity, String>{

}
