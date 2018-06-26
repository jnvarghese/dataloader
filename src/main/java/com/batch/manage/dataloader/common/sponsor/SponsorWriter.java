package com.batch.manage.dataloader.common.sponsor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.entity.Sponsor;
import com.batch.manage.dataloader.service.SponsorDAO;

public class SponsorWriter implements ItemWriter<Sponsor> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SponsorWriter.class);
    
    @Autowired
    private SponsorDAO sponsorDAO;

  
    
    @Override
    public void write(List<? extends Sponsor> items) throws Exception {
        LOGGER.info("Received the information of {} students", items.size());

       items.forEach(i -> LOGGER.debug("Received the information of a Sponsor: {}", i));
        
        for(Sponsor dto : items) {
        	//receiptDAO.save(dto.getErn().getReceipt());
        	sponsorDAO.save(dto);
        }
    }

}
