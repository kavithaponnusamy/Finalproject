package co.grandcircus.FinalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.FinalProject.entity.BuyerInformation;

public interface BuyerInformationDao extends JpaRepository<BuyerInformation, Long> {

	

	BuyerInformation findByUserIdAndPropertyId(Long id, String propertyId);
	

}
