package com.crio.xlido.repositories;

import java.util.Optional;
import com.crio.xlido.entities.User;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    boolean userIdExistOrNot(Long userId);
}
