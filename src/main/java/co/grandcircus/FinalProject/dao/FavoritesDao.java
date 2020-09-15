package co.grandcircus.FinalProject.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.FinalProject.entity.Favorites;


public interface FavoritesDao extends JpaRepository<Favorites, Long>{
	Favorites findByPropertyId(String propertyId);
	@Transactional
	void deleteByPropertyId(String propertyId);

}
