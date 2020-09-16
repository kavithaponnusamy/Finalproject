package co.grandcircus.FinalProject.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.FinalProject.entity.Favorites;


public interface FavoritesDao extends JpaRepository<Favorites, Long>{
	Favorites findByPropertyId(String propertyId);
	Favorites findByPropertyIdAndUserId(String propertyId,Long userId);
	
	List<Favorites> findByUserId(Long userId);
	
	@Transactional
	void deleteByPropertyId(String propertyId);	
	@Transactional
	void deleteByPropertyIdAndUserId(String propertyId,Long userId);



}
