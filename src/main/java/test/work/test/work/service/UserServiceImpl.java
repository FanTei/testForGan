package test.work.test.work.service;

import org.springframework.stereotype.Service;
import test.work.test.work.entity.User;
import test.work.test.work.exceptions.PasswordException;
import test.work.test.work.exceptions.ExistsWithNameException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;
import test.work.test.work.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(User user) throws ExistsWithNameException {
        String username = user.getUsername();
        if (repository.existsUserByUsername(username))
            throw new ExistsWithNameException("With the same username already exists");
        repository.save(user);
    }

    @Override
    public void getUser(User user) throws NotExistsWithUsernameException, PasswordException {
        String username = user.getUsername();
        if (!repository.existsUserByUsername(username))
            throw new NotExistsWithUsernameException("With this username does not exist");
        else if (!repository.findUserByUsername(username).getPassword().equals(user.getPassword()))
            throw new PasswordException();
    }
}
