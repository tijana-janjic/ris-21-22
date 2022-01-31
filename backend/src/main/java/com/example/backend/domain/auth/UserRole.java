package com.example.backend.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserRole {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false, unique=true)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_and_permission",
			joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="permission_id", referencedColumnName = "id"))
	private Set<Permission> permissions = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();
}
