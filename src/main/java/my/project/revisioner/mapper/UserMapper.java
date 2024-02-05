package my.project.revisioner.mapper;

import my.project.revisioner.dto.UserDto;
import my.project.revisioner.entity.User;
import my.project.revisioner.enums.ERole;
import my.project.revisioner.payload.request.SignupRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Set;

/**
 * @author s.melekhin
 * @since 24 май 2023 г.
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Mapping(target = "password", source = "request", qualifiedByName = "mapPassword")
    @Mapping(target = "roles", source = "request", qualifiedByName = "mapRoles")
    public abstract User fromRequest(SignupRequest request);

//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "firstname", source = "userDto.firstname")
//    @Mapping(target = "lastname", source = "userDto.lastname")
//    @Mapping(target = "id", source = "user.id")
//    @Mapping(target = "username", source = "user.username")
//    @Mapping(target = "email", source = "user.email")
//    @Mapping(target = "password", source = "user.password")
//    @Mapping(target = "createdDate", source = "user.createdDate")
    public abstract User fromDto(UserDto userDto);

    public abstract UserDto toDto(User user);

    @Named("mapPassword")
    public String mapPassword(SignupRequest request) {
        return bCryptPasswordEncoder.encode(request.getPassword());
    }

    @Named("mapRoles")
    public Set<ERole> mapRoles(SignupRequest request) {
        return Collections.singleton(ERole.ROLE_USER);
    }

}
