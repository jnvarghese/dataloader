package com.batch.manage.dataloader.common.receipt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.batch.manage.dataloader.model.ReceiptDTO;
import com.batch.manage.dataloader.model.entity.Initiative;
import com.batch.manage.dataloader.model.entity.Organization;
import com.batch.manage.dataloader.model.entity.Parish;
import com.batch.manage.dataloader.model.entity.receipts.Receipts;
import com.batch.manage.dataloader.service.MissionDAO;
import com.batch.manage.dataloader.service.OrganizationDAO;
import com.batch.manage.dataloader.service.ParishDAO;

public class ReceiptProcessor implements ItemProcessor<ReceiptDTO, Receipts> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProcessor.class);

	DateFormat df = new SimpleDateFormat("MM/dd/YYYY");
	 
	@Autowired
	ParishDAO parishDAO;

	@Autowired
	MissionDAO missionDAO;

	@Autowired
	OrganizationDAO organizationDAO;

	@Override
	public Receipts process(ReceiptDTO dto) throws Exception {
		Receipts r = null;
		Initiative i = null;
		Parish p;
		Organization o;
		LOGGER.info("  receipt dto " + dto);

		LOGGER.info(" parish code " + dto.getCode());
		if (!StringUtils.isEmpty(dto.getCode()) && !StringUtils.isEmpty(dto.getInitiative())) {
			r = new Receipts();
			if (dto.getType().equalsIgnoreCase("P")) {
				if (!StringUtils.isEmpty(dto.getCode())) {
					p = parishDAO.findOne(dto.getCode());
					LOGGER.info(" parish name " + p.getName());
					r.setReferenceId(p.getId());
					r.setReceiptType(0);
				}
			} else if (dto.getType().equalsIgnoreCase("O")) {
				if (!StringUtils.isEmpty(dto.getCode())) {
					o = organizationDAO.findOne(dto.getCode());
					LOGGER.info(" org name " + o.getName());
					r.setReferenceId(o.getId());
					r.setReceiptType(1);
				}
			}

			if (!StringUtils.isEmpty(dto.getInitiative())) {
				LOGGER.info(" dto.getInitiative() " + dto.getInitiative());
				i = missionDAO.findOne(dto.getInitiative());
				LOGGER.info(dto.getInitiative() + " initiative name " + i.getName());
			}
			r.setAmount(Double.valueOf(dto.getAmount()));
			r.setCity(dto.getCity());
			r.setEmail1(dto.getEmail1());
			r.setEmail2(dto.getEmail2());
			r.setFirstName(dto.getFirstname());
			r.setFullName(dto.getFullname().replaceAll("\"", ""));
			r.setInitiativeId(i.getId());
			r.setLastName(dto.getLastname());
			r.setMiddleName(dto.getMiddlename());
			r.setPhone1(dto.getPhone1());
			r.setPhone2(dto.getPhone2());
			if(null != dto.getDate() || !dto.getDate().isEmpty() || !"".equals(dto.getDate().trim())) {
				r.setrDate(df.format(new Date(Long.valueOf(dto.getDate()))));
		    }
			
			r.setState(dto.getState());
			// r.setStatus(status);
			r.setStreetAddress(dto.getStreetaddress());
			r.setTransaction(dto.getTransaction());
			r.setType(dto.getCategory().equals("DONATION") ? 0 : 1);
			if(null != dto.getZip()) {
	    		if(dto.getZip().contains(".")) {
	    			String[] zips = dto.getZip().split("\\.");
	       			if(zips[0].length() < 5) {
	       				r.setZipCode(0+zips[0]);
	    			}else {
	    				r.setZipCode(zips[0]);
	    			}
	        		
	        	}else {
	        		r.setZipCode(dto.getZip());
	        	}
	    	}else {
	    		r.setZipCode("00000");
	    	}
		}
		return r;
	}

}
