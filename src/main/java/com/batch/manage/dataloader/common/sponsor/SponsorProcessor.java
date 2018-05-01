package com.batch.manage.dataloader.common.sponsor;

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

import com.batch.manage.dataloader.model.SponsorDTO;
import com.batch.manage.dataloader.model.entity.Sponsor;
import com.batch.manage.dataloader.model.entity.StudentId;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.model.entity.enrollment.Sponsee;
import com.batch.manage.dataloader.model.entity.enrollment.SponsorMaxout;
import com.batch.manage.dataloader.model.entity.enrollment.StudentMaxout;
import com.batch.manage.dataloader.service.StudentIdDAO;

public class SponsorProcessor implements ItemProcessor<SponsorDTO, Sponsor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SponsorProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    private Long jobId;
    private Long parishId;
    
    @Autowired
    private StudentIdDAO studentIdDAO;
    
	public SponsorProcessor(Long jobId, Long parishId) {
		this.jobId =jobId;
		this.parishId = parishId;
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Job Id "+this.jobId+ " and parish id "+parishId+" in LoggingStudentProcessor constructor");
		}
	}

    @Override
    public Sponsor process(SponsorDTO dto) throws Exception {
    	LOGGER.debug("dto {}", dto);
    	Sponsor sponsor = new Sponsor();
    	
    	Set<SponsorMaxout> sponsorMaxOuts  = new HashSet<>();
		
		Set<StudentMaxout> studentMaxOuts  = new HashSet<>();
    	sponsor.setAppartmentNumber(null);
    	sponsor.setCity(dto.getCity());
    	
    	sponsor.setEmailAddress(dto.getEmail1());
    	
    	
    	if("&".equalsIgnoreCase(dto.getMi())) {
    		String[] names;
    		if(dto.getFirstName().contains(" ")) {
    			names = dto.getFirstName().split(" ");
    		}else {
    			names = new String[2];
    			names[0] = dto.getFirstName();
    			names[1] = "N/A";
    		}
    		
    		sponsor.setFirstName(names[0] == null ? "N/A" : names[0]);
    		sponsor.setLastName(names[1]);
    		sponsor.setHasAnyCoSponser(true);
    		sponsor.setCoSponserName(dto.getLastName());
    	}else {
    		sponsor.setFirstName(dto.getFirstName());
    		sponsor.setLastName(dto.getLastName());
    	}
    	
    	sponsor.setJobId(jobId);
    	if("&".equalsIgnoreCase(dto.getMi())) {
    		sponsor.setMiddleInitial(dto.getMi().replaceAll("&", ""));
    	}
    	
    	sponsor.setNickName(null);
    	sponsor.setParishId(this.parishId);
    	if(null != dto.getZip()) {
    		if(dto.getZip().contains(".")) {
    			String[] zips = dto.getZip().split("\\.");
    			System.out.println( " -- -zips --"+zips.length);
    			for( String a: zips) {
    				System.out.println( "  --------  "+ a);
    			}
    			if(zips[0].length() < 5) {
    				sponsor.setPostalCode(0+zips[0]);
    			}else {
    				sponsor.setPostalCode(zips[0]);
    			}
        		
        	}else {
        		sponsor.setPostalCode(dto.getZip());
        	}
    	}else {
    		sponsor.setPostalCode("00000");
    	}
    	
    	
    	//sponsor.setSponsorCode(dto.getSponsorId().split("-")[3]);
    	//sponsor.setSponsorStatus(sponsorStatus);
    	if("New York".equalsIgnoreCase(dto.getState())) {
    		
    	}
    	sponsor.setState(dto.getState());
    	sponsor.setStreet(dto.getStreetAddress());
    	sponsor.setCreatedBy(2L);
    	sponsor.setEmailAddress2(dto.getEmail2());
    	sponsor.setPhone1(dto.getPhone1());
    	sponsor.setPhone2(dto.getPhone2());
    	sponsor.setTransactionRemarks(dto.getTransaction());
    	
    	Enrollment enrollment =  getEnrollment(dto, sponsor);
    	sponsor.setErn(enrollment);
		
      return sponsor;
    }
    
    private Enrollment getEnrollment(SponsorDTO dto, Sponsor sponsor) {
    	List<StudentId> studentIds = new ArrayList<StudentId>();
    	Set<Sponsee> sponsees  = new HashSet<>();
    	Set<SponsorMaxout> sponsorMaxOuts  = new HashSet<>();
		
		Set<StudentMaxout> studentMaxOuts  = new HashSet<>();
    	Enrollment en = new Enrollment();

    	String studentCount = "1";
		if(null != dto.getTotal() && dto.getTotal().contains(".")) {
			studentCount = dto.getTotal().split("\\.")[0];
			studentIds = studentIdDAO.list(this.parishId, Integer.valueOf(studentCount));			
		}
		
		double contribution = Double.valueOf(dto.getAmount().replace("$", ""));
		int noOfChild = Integer.valueOf(studentCount);
		double perChildContribution = contribution/noOfChild;
		double reminderContributionPerChild = perChildContribution % 20;
		double totalReminder = reminderContributionPerChild * noOfChild;
		int maxMonth = (int) (perChildContribution/20);
		
		Calendar myCal = Calendar.getInstance();
		
		Date sponsorDate = new Date(Long.valueOf(dto.getDate()));
		myCal.setTime(sponsorDate);	
		
		myCal.add(Calendar.MONTH, 1);
		Date effectiveDate = myCal.getTime();
		
		myCal.add(Calendar.MONTH, maxMonth+1);
		Date expirationDate = myCal.getTime();
		int month = myCal.get(Calendar.MONTH);
		int year = myCal.get(Calendar.YEAR);
		
		for(StudentId s : studentIds) {		
		    sponsees.add(new Sponsee(en,  month, year, s.getId()));
		    studentMaxOuts.add(new StudentMaxout(s.getId(), en, expirationDate));
		}

		//sponsorMaxOuts.add(new SponsorMaxout(en, expirationDate));
		//en.setSponsorMaxOuts(sponsorMaxOuts);			
		en.setSpn(sponsor);
		en.setContributionAmount(contribution);
		en.setCreatedBy(2L);
		en.setJobId(jobId);
		en.setEffectiveDate(effectiveDate);
		en.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
		en.setMiscAmount(totalReminder);						
		en.setSponsees(sponsees);							
		en.setStudentMaxOuts(studentMaxOuts);
		
		return en;
    }
}
