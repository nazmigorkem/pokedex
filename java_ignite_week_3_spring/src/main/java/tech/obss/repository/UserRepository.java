package tech.obss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.obss.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameStartsWithAndActiveIsTrueOrderByCreatedDateDesc(String username);

    Optional<User> findByUsername(String username);
}
