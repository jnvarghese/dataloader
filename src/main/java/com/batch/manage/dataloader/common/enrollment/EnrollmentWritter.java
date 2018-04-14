package com.batch.manage.dataloader.common.enrollment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.common.sponsor.SponsorWriter;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.service.enrollment.EnrollmentDAO;

public class EnrollmentWritter implements ItemWriter<Enrollment> {

	@Autowired
	private EnrollmentDAO enrollmentDAO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SponsorWriter.class);
	@Override
	public void write(List<? extends Enrollment> items) throws Exception {
		 items.forEach(i -> LOGGER.debug("Received the information of a Enrollment: {}", i));
		 
		  for(Enrollment e : items) {
			  
			  enrollmentDAO.save(e);
	        }
	}

}
