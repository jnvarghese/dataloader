package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.SponsorId;

public interface SponsorIdDAO extends CrudRepository<SponsorId, Long> {

	 @Query(value = " SELECT S.id FROM sponsor S, parish P, center C, region R WHERE SPONSORCODE =:sponsorId "
	 		+ "AND PARISHID = P.ID AND P.CENTERID = C.ID AND C.REGIONID = R.ID AND P.CODE =:parishCode AND C.CODE = :centerCode "
	 		+ "AND R.CODE =:regionCode AND S.SPONSORSTATUS = 0", nativeQuery = true)
	 SponsorId findOne(@Param("regionCode") String region, 
			 @Param("centerCode") String center, 
			 @Param("parishCode") String parish, 
			 @Param("sponsorId") String sponsor);
}
