package org.alex.springapp.dao;

import java.util.List;
import org.alex.springapp.entity.User;
import org.alex.springapp.repository.UserRepository;
import org.alex.springapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zamdirit
 */
@Service
public class UserDAO implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOG = LogManager.getLogger(UserDAO.class);

    @Autowired
    public UserDAO(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByRoleId(long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public Page<User> findByRoleId(long roleId, Pageable pageable) {
        return repository.findAllByRoleId(roleId, pageable);
    }

    @Override
    public User findByUserName(String userName) {
        return repository.findByUserName(userName).orElse(null);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        User user = findByUserName(userName);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user == null) {
            LOG.error("User is null!");
            return null;
        }
        return repository.save(user);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        User user = findById(id);
        if (user == null) {
            LOG.error("User for id {} not found!", id);
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}
