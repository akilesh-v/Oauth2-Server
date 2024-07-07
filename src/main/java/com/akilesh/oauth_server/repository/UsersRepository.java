package com.akilesh.oauth_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akilesh.oauth_server.entiry.Users;

/**
 * @author AkileshVasudevan
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    Users findByEmail(String email);

}
