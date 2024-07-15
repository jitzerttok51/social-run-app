package org.jitzerttok51.social.run.repository;

import org.jitzerttok51.social.run.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT COUNT(*) > 0 FROM UserEntity u WHERE u.username = :username")
    boolean usernameExists(@Param("username") String username);

    @Query("SELECT COUNT(*) > 0 FROM UserEntity u WHERE u.email = :email")
    boolean emailExists(@Param("email") String email);
}
