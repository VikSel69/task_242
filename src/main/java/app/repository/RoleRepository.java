package app.repository;

import app.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleRepository {
    List<Role> findAll();

    Role findRoleByRole(String authority) throws NoSuchElementException;
}
