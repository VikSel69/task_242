package app.repository;

import app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> find(Long id);

    User find(String email);

    void save(User user);

    void update(User user);

    void delete(User user);
}
