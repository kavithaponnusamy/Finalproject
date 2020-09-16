package co.grandcircus.FinalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.FinalProject.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	 
}
