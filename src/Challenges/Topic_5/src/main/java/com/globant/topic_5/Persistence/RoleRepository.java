package com.globant.topic_5.Persistence;

import com.globant.topic_5.Persistence.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByNameRole(String nameRole);
}
