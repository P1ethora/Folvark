package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.EmailUserRepository;
import net.plethora.folvark.models.EmailUser;
import org.springframework.stereotype.Component;

@Component
public class DaoEmailUser {

    private final EmailUserRepository emailUserRepository;

    public DaoEmailUser(EmailUserRepository emailUserRepository) {
        this.emailUserRepository = emailUserRepository;
    }

    public void saveEmail(EmailUser email) {
        emailUserRepository.save(email);
    }


}
