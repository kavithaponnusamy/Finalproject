package co.grandcircus.FinalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import co.grandcircus.FinalProject.entity.SavedSearches;

public interface SavedSearchesDao extends JpaRepository<SavedSearches, Long>{
	
}

