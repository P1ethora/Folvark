package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public void deleteById(long id);

    public Optional<User> findById(long id);
}
