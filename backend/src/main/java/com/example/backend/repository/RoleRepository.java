package com.example.backend.repository;

import com.example.backend.domain.auth.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT EXISTS (SELECT * from user_role ur LEFT JOIN role_and_permission rap ON ur.id=rap.role_id " +
                    "LEFT JOIN permission p ON rap.permission_id=p.id " +
                    "WHERE ur.name = ?1 AND p.name = ?2)"
    )
    Integer checkIfRoleHasPermission(String roleName, String resource);
}