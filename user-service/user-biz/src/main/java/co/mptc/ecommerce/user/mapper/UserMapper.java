package co.mptc.ecommerce.user.mapper;


import co.mptc.ecommerce.user.dto.UserCreateRequest;
import co.mptc.ecommerce.user.dto.UserResponse;
import co.mptc.ecommerce.user.dto.UserUpdateRequest;
import co.mptc.ecommerce.user.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateRequest userCreateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UserUpdateRequest request, @MappingTarget UserEntity userEntity);
    UserResponse toResponse(UserEntity userEntity);

    default UserResponse toResponse(UserEntity userEntity, UserRepresentation userRepresentation) {
        return UserResponse.builder().id(userEntity.getId()).familyName(userRepresentation.getFirstName()).givenName(userRepresentation.getLastName()).address(userEntity.getAddress()).birthday(userEntity.getBirthday()).gender(userEntity.getGender()).mobilePhone(userEntity.getMobilePhone()).build();
    }

    @Mapping(source = "familyName", target = "firstName")
    @Mapping(source = "givenName", target = "lastName")
    UserRepresentation toRepresentation(UserCreateRequest userCreateRequest);
}
