package app.repository;

import org.springframework.stereotype.Repository;
import app.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role findRoleByRole(String role) throws NoSuchElementException {
        return findAll().stream()
                .filter(r -> role.equals(r.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Role %s not found", role)));
    }
}
