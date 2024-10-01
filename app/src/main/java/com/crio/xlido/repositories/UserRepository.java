package com.crio.xlido.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.User;

public class UserRepository implements IUserRepository{

private final Map<Long,User> storage = new HashMap<>();
private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public User save(User user) {       
        User userObject = new User(idCounter.incrementAndGet() , user);
        storage.putIfAbsent( userObject.getId(), userObject);
        return userObject;
    }

    @Override
    public Optional<User> findById(Long id) {        
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public boolean userIdExistOrNot(Long userId) {     
        return storage.containsKey(userId);
    }
    
}
