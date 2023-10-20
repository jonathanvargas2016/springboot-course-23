package com.jonathan.users.repository;

import com.jonathan.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    @Query("Select u from  User u where u.username = ?1")
    public User getByUsername(String username);

}
