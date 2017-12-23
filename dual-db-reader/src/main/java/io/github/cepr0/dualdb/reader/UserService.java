package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.first.model.User;

import java.util.List;

/**
 * @author Cepr0, 2017-12-23
 */
public interface UserService {
	List<User> findAll();
}
