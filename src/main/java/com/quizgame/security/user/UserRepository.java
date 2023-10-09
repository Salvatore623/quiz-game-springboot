package com.quizgame.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>findByUsername(String Username);

    Optional<List<User>> findFirst10ByOrderByBestScoreEasyDesc();

    Optional<List<User>> findFirst10ByOrderByBestScoreMediumDesc();

    Optional<List<User>> findFirst10ByOrderByBestScoreHardDesc();

}
