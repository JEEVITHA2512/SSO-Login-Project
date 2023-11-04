package api1.dto.mapper;

import api1.dto.request.UserRequest;
import api1.dto.response.UserResponse;
import api1.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    User fromRequestToEntity(UserRequest userRequest);
    UserResponse fromEntityToResponse(User user);
}