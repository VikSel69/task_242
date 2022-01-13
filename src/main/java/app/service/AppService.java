package app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import app.model.Role;
import app.model.User;

import java.util.List;

public interface AppService extends UserDetailsService {
    List<User> findAllUsers();

    User findUser(Long userId) throws NullPointerException;

    void deleteUser(Long userId);

    List<Role> findAllRoles();

    Role findRoleByRole(String role);

    boolean saveUser(User user);
}
