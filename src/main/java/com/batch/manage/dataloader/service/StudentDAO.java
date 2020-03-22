package com.batch.manage.dataloader.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.Student;

public interface StudentDAO extends CrudRepository<Student, Long> {
	
	 @Query(value = "SELECT S.ID,P.ID,A.ID FROM student S, project P, agency A WHERE STUDENTCODE=:studentCode AND S.PROJECTID = P.ID "
	 		+ "AND P.AGENCYID = A.ID AND P.CODE=:projectCode AND A.CODE=:agencyCode AND S.STATUS = 0 ", nativeQuery = true)
	 Student findOne(@Param("agencyCode") String agency, @Param("projectCode") String project, @Param("studentCode") String studentCode);

	 @Query(value = "SELECT * FROM student S WHERE STUDENTCODE=:studentCode AND PROJECTID=:projectId  AND S.STATUS = 0 ORDER BY ID", nativeQuery = true)
	 List<Student> findByCodeAndProject(@Param("studentCode") String studentCode, @Param("projectId") Long projectId);
}
