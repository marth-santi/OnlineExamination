package vn.kase.onlineExam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "fullname")
	protected String fullname;
	
	@Column(name = "mobile")
	protected String mobile;
	
	@Column(name = "email")
	protected String email;
	
	@Column(name = "address")
	protected String address;
	
	@Column(name = "username")
	protected String username;
	
	@Column(name = "pass")
	protected String pass;
	
	@Column(name = "roles")
	protected int roles;
	
	@Column(name = "confirm", columnDefinition = "boolean default false")
	protected boolean confirm;
	
}
