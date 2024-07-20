package com.github.springwebsocketmongodb.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName())
                .orElse(null);

        log.info("storedUser = " + storedUser);

        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
