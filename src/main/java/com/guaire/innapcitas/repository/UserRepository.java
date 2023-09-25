package com.guaire.innapcitas.repository;

import com.guaire.innapcitas.domain.User;

import com.guaire.innapcitas.service.dto.UserDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<User> findOneWithAuthoritiesByEmail(String email);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    Page<User> findByLoginStartsWithIgnoreCaseOrFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String query, String query1, String query2, Pageable pageable);

    List<User> findByLoginStartsWithIgnoreCaseOrFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String query, String query1, String query2);

    @Query("select user from User user where user.id in (select distinct cita.paciente.id from Cita cita)")
    Page<User> findPatients(Pageable pageable);

    @Query("select user from User user where user.id in (select distinct cita.paciente.id from Cita cita) and (user.firstName like %?2% OR user.lastName like %?3% OR user.email like %?1% OR user.login like %?4%)")
    Page<User> findPatientsSearch(String email, String firstName, String lastName, String login, Pageable pageable);

    Page<User> findByEmailStartsWithIgnoreCaseOrFirstNameStartsWithIgnoreCaseOrLastNameStartsWithIgnoreCase(String query, String nombre, String apellido, Pageable pageable);
}
