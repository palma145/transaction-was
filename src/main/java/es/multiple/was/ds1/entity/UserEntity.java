package es.multiple.was.ds1.entity;

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
@Table(name = "USERS", schema = "MULTIPLEDS")
public class UserEntity {

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
}
