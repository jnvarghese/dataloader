package com.batch.manage.dataloader.common.enrollment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.common.student.StudentProcessor;
import com.batch.manage.dataloader.model.EnrollmentDTO;
import com.batch.manage.dataloader.model.entity.Sponsor;
import com.batch.manage.dataloader.model.entity.Student;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.model.entity.enrollment.Sponsee;
import com.batch.manage.dataloader.model.entity.enrollment.SponsorMaxout;
import com.batch.manage.dataloader.model.entity.enrollment.StudentMaxout;
import com.batch.manage.dataloader.service.SponsorDAO;
import com.batch.manage.dataloader.service.StudentDAO;

public class EnrollmentProcessor implements ItemProcessor<EnrollmentDTO, Enrollment> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    @Autowired
    private SponsorDAO sponsorDAO;
    
    @Autowired
    private StudentDAO studentDAO;
    
	private Long jobId;
	 
	public EnrollmentProcessor(Long jobId) {
		super();
		this.jobId = jobId;
	}

	@Override
	public Enrollment process(EnrollmentDTO dto) throws Exception {
		LOGGER.debug("dto {}", dto);
		Set<SponsorMaxout> sponsorMaxOuts  = new HashSet<>();
				
		Set<StudentMaxout> studentMaxOuts  = new HashSet<>();
		 Set<Sponsee> sponsees  = new HashSet<>();
		 Sponsor sponsor;
		 Student student;
		 if(dto.getSponsorId().length() > 0) {
			    sponsor =  sponsorDAO.findOne(dto.getSponsorId().split("-")[3]);
			  //  student = studentDAO.findOne(studentCode)
				Enrollment e = new Enrollment();
				
				Calendar maxOut = Calendar.getInstance();
			
			
				maxOut.set(Integer.valueOf(dto.getYear().split("\\.")[0]), getMonth(dto.getMonth()), 1, 0, 0);  
				
				e.setContributionAmount(Double.valueOf(dto.getAmount().replace("$", "")));
				e.setCreatedBy(2L);
				e.setJobId(jobId);
				e.setEffectiveDate(new Date(Long.valueOf(dto.getDate())));
				e.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
				e.setSponsorId(sponsor.getId());
				e.setMiscAmount(0);
				e.setSponsees(sponsees);
				
				if(!dto.getTotal().equals("1.0")) {
					/*sponsee.setEnrollmentId(e.getId());
					enrollmentMapper.insertSponsee(sponsee);
					c.set(sponsee.getExpirationYear(), sponsee.getExpirationMonth() - 1, 1, 0, 0);  
					maxOutMapper.insertStudentMaxOut(new StudentMaxOut(sponsee.getStudentId(), e.getId(), c.getTime()));
					maxOutMapper.insertSponsorMaxOut(new SponsorMaxOut(enrollment.getSponsorId(), e.getId(), c.getTime()));*/
				}else {
					sponsees.add(new Sponsee(e,  getMonth(dto.getMonth()), Integer.valueOf(dto.getYear().split("\\.")[0]), 10840L));
					e.setSponsees(sponsees);
					sponsorMaxOuts.add(new SponsorMaxout(29L, e, maxOut.getTime()));
					e.setSponsorMaxOuts(sponsorMaxOuts);
					studentMaxOuts.add(new StudentMaxout(10840L, e, maxOut.getTime()));
					e.setStudentMaxOuts(studentMaxOuts);
				}
			
				return e;
		 }
		 return null;
	}

	private int getMonth(String month) {
		if("January".equalsIgnoreCase(month)) {
			return 1;
		}else if("February".equalsIgnoreCase(month)) {
			return 2;
		}else if("March".equalsIgnoreCase(month)) {
			return 3;
		}else if("April".equalsIgnoreCase(month)) {
			return 4;
		}else if("May".equalsIgnoreCase(month)) {
			return 5;
		}else if("June".equalsIgnoreCase(month)) {
			return 6;
		}else if("July".equalsIgnoreCase(month)) {
			return 7;
		}else if("August".equalsIgnoreCase(month)) {
			return 8;
		}else if("September".equalsIgnoreCase(month)) {
			return 9;
		}else if("October".equalsIgnoreCase(month)) {
			return 10;
		}else if("November".equalsIgnoreCase(month)) {
			return 11;
		}else if("December".equalsIgnoreCase(month)) {
			return 12;
		}
		return 0;
	}
}
