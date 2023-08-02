package tech.obss.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import tech.obss.entity.User;

import java.util.List;

@Repository
public class UserDAO {
    private final EntityManager entityManager;

    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> getUsers(int pageNumber, int pageSize) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var from = criteriaQuery.from(User.class);
        criteriaQuery.select(from).where(criteriaBuilder.isTrue(from.get("active")));
        var query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
