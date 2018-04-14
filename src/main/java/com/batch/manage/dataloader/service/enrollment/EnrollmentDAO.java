package com.batch.manage.dataloader.service.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;

@Repository
public interface EnrollmentDAO extends JpaRepository<Enrollment, Long> {

}
