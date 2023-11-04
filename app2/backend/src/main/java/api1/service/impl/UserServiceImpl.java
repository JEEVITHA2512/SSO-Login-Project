package api1.service.impl;

import api1.dto.mapper.UserMapper;
import api1.dto.request.UserRequest;
import api1.dto.response.UserResponse;
import api1.entity.User;
import api1.repo.UserRepo;
import api1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();

    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

//    Using Request and Response with save and update user

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        userRepo.save(user);
        return UserMapper.MAPPER.fromEntityToResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, Long id) {

        Optional<User> checkExistingUser = findById(id);
        if (! checkExistingUser.isPresent())
            throw new RuntimeException("User Id "+ id + " Not Found!");

        User user = UserMapper.MAPPER.fromRequestToEntity(userRequest);
        user.setId(id);
        userRepo.save(user);
        return UserMapper.MAPPER.fromEntityToResponse(user);
    }

}