package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.model.Role;
import app.model.User;
import app.repository.RoleRepository;
import app.repository.UserRepository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.find(userId)
        .orElseThrow(() -> new EmptyResultDataAccessException(String.format("User with ID = %d not found", userId), 1));
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.find(email);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("User email %s not found", email));
        }
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.find(userId);
        if (user.isPresent()) {
            try {
                userRepository.delete(user.get());
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }

    @Override
    public boolean saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            if (user.getId() != null) {
            userRepository.save(user);
            } else userRepository.update(user);
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }
}
