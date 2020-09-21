package co.grandcircus.FinalProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import co.grandcircus.FinalProject.entity.SavedSearches;

public interface SavedSearchesDao extends JpaRepository<SavedSearches, Long>{

	SavedSearches findByNameAndUserIdAndSearchUrl(String name, Long id, String searchUrl);
	List<SavedSearches> findByUserId(Long userId);
	
	

}

