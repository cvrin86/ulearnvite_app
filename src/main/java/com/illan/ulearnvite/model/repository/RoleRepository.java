package com.illan.ulearnvite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.illan.ulearnvite.enums.ERole;
import com.illan.ulearnvite.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(ERole name);
}
