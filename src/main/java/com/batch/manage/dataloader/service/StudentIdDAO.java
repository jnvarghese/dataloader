package com.batch.manage.dataloader.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.batch.manage.dataloader.model.entity.StudentId;

public interface StudentIdDAO extends CrudRepository<StudentId, Long> {
	
	 @Query(value = "SELECT S.id FROM student S, project P, agency A WHERE STUDENTCODE=:studentCode AND S.PROJECTID = P.ID "
	 		+ "AND P.AGENCYID = A.ID AND P.CODE=:projectCode AND A.CODE=:agencyCode AND S.STATUS = 0 ", nativeQuery = true)
	 StudentId findOne(@Param("agencyCode") String agency, @Param("projectCode") String project, @Param("studentCode") String studentCode);
	 
	 @Query(value = "SELECT s.id FROM student s, parish_project pp where pp.projectId = s.projectId and pp.parishId =:parishId "
		 		+ "and s.id  not in ( select studentId from student_maxout) and s.STATUS = 0 "
		 		+ "order by studentCode LIMIT :limitBy ", nativeQuery = true)
		 List<StudentId> list(@Param("parishId") Long parishId, @Param("limitBy") int limitBy);
}
