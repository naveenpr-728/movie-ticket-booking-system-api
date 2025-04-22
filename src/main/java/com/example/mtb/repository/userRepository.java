package com.example.mtb.repository;

import com.example.mtb.entity.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<userDetails, String> {

    boolean existsByEmail(String email);
}
