package org.jitzerttok51.social.run.model.mapping;

import org.jitzerttok51.social.run.model.dto.UserDTO;
import org.jitzerttok51.social.run.model.dto.UserRegisterDTO;
import org.jitzerttok51.social.run.model.dto.UserSecretDTO;
import org.jitzerttok51.social.run.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

// TODO: Add javadoc
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    UserEntity map(UserRegisterDTO register);
    UserDTO map(UserEntity entity);
    UserSecretDTO mapWithSecret(UserEntity entity);
}
