package es.multiple.was.ds2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFILES", schema = "MULTIPLEDS")
public class ProfileUserEntity {

	@Id
	@Column(name = "id_profile")
	private String idProfile;
	
	@Column(name = "profile_name")
	private String profileName;
}
