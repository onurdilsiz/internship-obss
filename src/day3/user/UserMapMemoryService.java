package day3.user;
import day3.user.exceptions.UserNotFoundException;

import java.util.HashMap; import java.util.Map;
public class UserMapMemoryService implements UserMemoryService {

    private final Map<Integer, User> users;
    private int capacity = 0;

    public UserMapMemoryService(int capacity) {
        this.capacity = capacity;
        this.users = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        if (users.keySet().size() < capacity) {
            users.put(user.getUserId(), user);
            return;
        }
    }

    @Override
    public void deleteUser(int id) throws UserNotFoundException {
        try {
            users.remove(id);
        } catch (Exception e) {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public boolean hasUser(int id) {
        return users.containsKey(id);
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        try {
            return users.get(id);
        } catch (Exception e) {
            throw new UserNotFoundException("User not found");

        }

    }
}