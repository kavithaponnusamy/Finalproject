package co.grandcircus.FinalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.FinalProject.entity.Favorites;


public interface FavoritesDao extends JpaRepository<Favorites, Long>{
	Favorites findByPropertyId(String propertyId);

}
