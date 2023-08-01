package tech.obss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.obss.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
