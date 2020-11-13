package com.soro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soro.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	public  Optional<User> findByUsername(String userName);
    public  User getUserByUsernameAndPassword(String username,String password);
    public  User getUserById(Long id);
    public  User getUserByFirstname(String firstname);
    public  User findByMail(String mail);
    public  User findByMailAndPassword(String mail,String password);
}
