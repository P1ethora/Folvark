package net.plethora.folvark.security;

import javafx.scene.chart.CategoryAxis;
import net.plethora.folvark.dao.DaoUser;
import net.plethora.folvark.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private DaoUser daoUser;

    public UserDetailsServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = daoUser.findByEmail(email);
        return SecurityUser.fromUser(user);
    }
}
