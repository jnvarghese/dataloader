package com.batch.manage.dataloader.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.StudentId;

public interface StudentIdDAO extends CrudRepository<StudentId, Long> {
	
	 @Query(value = "SELECT S.id FROM student S, project P, agency A WHERE STUDENTCODE=:studentCode AND S.PROJECTID = P.ID "
	 		+ "AND P.AGENCYID = A.ID AND P.CODE=:projectCode AND A.CODE=:agencyCode AND S.STATUS = 0 ", nativeQuery = true)
	 StudentId findOne(@Param("agencyCode") String agency, @Param("projectCode") String project, @Param("studentCode") String studentCode);
}
