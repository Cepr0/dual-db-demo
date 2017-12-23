package io.github.cepr0.dualdb.reader;

import io.github.cepr0.dualdb.first.model.User;
import io.github.cepr0.dualdb.first.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

/**
 * @author Cepr0, 2017-12-23
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Transactional(transactionManager = "bitronixTransactionManager",
			readOnly = true, isolation = READ_COMMITTED)
	public List<User> findAll() {
		return userRepo.findAll();
	}
}
