package api1.service;

import api1.dto.request.UserRequest;
import api1.dto.response.UserResponse;
import api1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUser();
    Optional<User> findById(Long id);
    User saveUser(User User);
    User updateUser(User User);
    void deleteUser(Long id);

//    Using Request for Save and Update User
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest, Long id);


}