package com.batch.manage.dataloader.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.batch.manage.dataloader.model.SponsorDTO;
import com.batch.manage.dataloader.model.entity.Sponsor;

public class SponsorProcessor implements ItemProcessor<SponsorDTO, Sponsor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SponsorProcessor.class);
    DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
    
    private Long jobId;
    private Long parishId;
    
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
    	Sponsor e = new Sponsor();
    	e.setAppartmentNumber(null);
    	e.setCity(dto.getCity());
    	
    	e.setEmailAddress(dto.getEmail1());
    	
    	if("&".equalsIgnoreCase(dto.getMi())) {
    		String[] names = null;
    		if(dto.getFirstName().contains(" ")) {
    			names = dto.getFirstName().split(" ");
    		}else {
    			names = new String[0];
    			names[0] = dto.getFirstName();
    			names[1] = "N/A";
    		}
    		
    		e.setFirstName(names[0] == null ? "N/A" : names[0]);
    		e.setLastName(names[1]);
    		e.setHasAnyCoSponser(true);
    		e.setCoSponserName(dto.getLastName());
    	}else {
    		e.setFirstName(dto.getFirstName());
    		e.setLastName(dto.getLastName());
    	}
    	
    	e.setJobId(jobId);
    	if(!"&".equalsIgnoreCase(dto.getMi())) {
    		e.setMiddleInitial(dto.getMi());
    	}
    	
    	e.setNickName(null);
    	e.setParishId(this.parishId);
    	if(null != dto.getZip()) {
    		if(dto.getZip().contains(".")) {
    			String[] zips = dto.getZip().split("\\.");
    			System.out.println( " -- -zips --"+zips.length);
    			for( String a: zips) {
    				System.out.println( "  --------  "+ a);
    			}
    			if(zips[0].length() < 5) {
    				e.setPostalCode(0+zips[0]);
    			}else {
    				e.setPostalCode(zips[0]);
    			}
        		
        	}else {
        		e.setPostalCode(dto.getZip());
        	}
    	}else {
    		e.setPostalCode("00000");
    	}
    	
    	
    	e.setSponsorCode(dto.getSponsorId().split("-")[3]);
    	//e.setSponsorStatus(sponsorStatus);
    	if("New York".equalsIgnoreCase(dto.getState())) {
    		
    	}
    	e.setState(dto.getState());
    	e.setStreet(dto.getStreetAddress());
    	e.setCreatedBy(2L);
    	e.setEmailAddress2(dto.getEmail2());
    	e.setTransactionRemarks(dto.getTransaction());
      return e;
    }
    
    
}
