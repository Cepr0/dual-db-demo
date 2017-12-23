package io.github.cepr0.dualdb.first.repo;

import io.github.cepr0.dualdb.first.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Cepr0, 2017-12-21
 */
public interface UserRepo extends JpaRepository<User, UUID> {
}
