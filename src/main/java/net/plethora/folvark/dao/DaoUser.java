package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.User;
import org.springframework.stereotype.Component;

@Component
public class DaoUser {

    private UserRepository userRepository;

    public DaoUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

}
