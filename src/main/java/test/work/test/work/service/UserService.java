package test.work.test.work.service;

import test.work.test.work.entity.User;
import test.work.test.work.exceptions.PasswordException;
import test.work.test.work.exceptions.ExistsWithNameException;
import test.work.test.work.exceptions.NotExistsWithUsernameException;

public interface UserService {

    void createUser(User user) throws ExistsWithNameException;



    void getUser(User user) throws NotExistsWithUsernameException, PasswordException;
}
