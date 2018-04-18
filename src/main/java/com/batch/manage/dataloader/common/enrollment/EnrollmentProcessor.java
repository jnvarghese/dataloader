package com.batch.manage.dataloader.common.enrollment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.common.student.StudentProcessor;
import com.batch.manage.dataloader.model.EnrollmentDTO;
import com.batch.manage.dataloader.model.entity.SponsorId;
import com.batch.manage.dataloader.model.entity.StudentId;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.model.entity.enrollment.Sponsee;
import com.batch.manage.dataloader.model.entity.enrollment.SponsorMaxout;
import com.batch.manage.dataloader.model.entity.enrollment.StudentMaxout;
import com.batch.manage.dataloader.service.SponsorIdDAO;
import com.batch.manage.dataloader.service.StudentIdDAO;

public class EnrollmentProcessor implements ItemProcessor<EnrollmentDTO, Enrollment> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    @Autowired
    private SponsorIdDAO sponsorIdDAO;
    
    @Autowired
    private StudentIdDAO studentIdDAO;
    
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
		 SponsorId sponsor;
		 StudentId student;
		 if(dto.getSponsorId().length() > 0) {
			 	
				Enrollment e = new Enrollment();
				
				Calendar maxOut = Calendar.getInstance();
				maxOut.set(Integer.valueOf(dto.getYear().split("\\.")[0]), getMonth(dto.getMonth()), 1, 0, 0);  
				maxOut.add(Calendar.MONTH, -1);
		
				String[] sponsors = dto.getSponsorId().split("-");
				sponsor =  sponsorIdDAO.findOne(sponsors[0], sponsors[1], sponsors[2], sponsors[3]);
				sponsorMaxOuts.add(new SponsorMaxout(sponsor.getId(), e, maxOut.getTime()));
				e.setSponsorMaxOuts(sponsorMaxOuts);			
				
				if(!dto.getTotal().equals("1.0")) {
					List<String> sList = new ArrayList<String>();
					if(dto.getTotal().equals("2.0")) {
						sList.add(dto.getChild1());
						sList.add(dto.getChild2());
					}else if(dto.getTotal().equals("3.0")) {
						sList.add(dto.getChild1());
						sList.add(dto.getChild2());
						sList.add(dto.getChild3());
					}else if(dto.getTotal().equals("4.0")) {
						sList.add(dto.getChild1());
						sList.add(dto.getChild2());
						sList.add(dto.getChild3());
						sList.add(dto.getChild4());
					}else if(dto.getTotal().equals("5.0")) {
						sList.add(dto.getChild1());
						sList.add(dto.getChild2());
						sList.add(dto.getChild3());
						sList.add(dto.getChild4());
						sList.add(dto.getChild5());
					}else if(dto.getTotal().equals("6.0")) {
						sList.add(dto.getChild1());
						sList.add(dto.getChild2());
						sList.add(dto.getChild3());
						sList.add(dto.getChild4());
						sList.add(dto.getChild5());
						sList.add(dto.getChild6());
					}
					
					for(String child: sList) {			
						
					 	String[] students = child.split("-");					    
					    student = studentIdDAO.findOne(students[0],students[1],students[2]);
					    sponsees.add(new Sponsee(e,  getMonth(dto.getMonth()), Integer.valueOf(dto.getYear().split("\\.")[0]), student.getId()));
					    studentMaxOuts.add(new StudentMaxout(student.getId(), e, maxOut.getTime()));
					}
										
				 	//String[] students = dto.getChild1().split("-");
				   // student = studentIdDAO.findOne(students[0],students[1],students[2]);				    
					e.setContributionAmount(Double.valueOf(dto.getAmount().replace("$", "")));
					e.setCreatedBy(2L);
					e.setJobId(jobId);
					e.setEffectiveDate(new Date(Long.valueOf(dto.getDate())));
					e.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
					e.setSponsorId(sponsor.getId());
					e.setMiscAmount(0);						
					e.setSponsees(sponsees);							
					e.setStudentMaxOuts(studentMaxOuts);
					
				}else {

					String[] students = dto.getChild1().split("-");
					student = studentIdDAO.findOne(students[0],students[1],students[2]);
				    
					e.setContributionAmount(Double.valueOf(dto.getAmount().replace("$", "")));
					e.setCreatedBy(2L);
					e.setJobId(jobId);
					e.setEffectiveDate(new Date(Long.valueOf(dto.getDate())));
					e.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
					e.setSponsorId(sponsor.getId());
					e.setMiscAmount(0);			
					
					sponsees.add(new Sponsee(e,  getMonth(dto.getMonth()), Integer.valueOf(dto.getYear().split("\\.")[0]), student.getId()));
					e.setSponsees(sponsees);					
				
					studentMaxOuts.add(new StudentMaxout(student.getId(), e, maxOut.getTime()));
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
