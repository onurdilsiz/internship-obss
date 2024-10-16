package day3.user;

import day3.user.exceptions.UserNotFoundException;

public interface UserMemoryService {

    public void addUser (User user);
    public void deleteUser (int id) throws UserNotFoundException;

    public boolean hasUser (int id);

    public User getUserById (int id) throws UserNotFoundException;

}
