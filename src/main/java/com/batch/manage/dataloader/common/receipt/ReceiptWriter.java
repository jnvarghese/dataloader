package com.batch.manage.dataloader.common.receipt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.batch.manage.dataloader.model.entity.receipts.Receipts;
import com.batch.manage.dataloader.service.ReceiptsDAO;

public class ReceiptWriter implements ItemWriter<Receipts> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptWriter.class);

	@Autowired
	private ReceiptsDAO receiptsDAO;

	@Override
	public void write(List<? extends Receipts> items) throws Exception {
		LOGGER.info("Received the information of {} receipts", items.size());

		items.forEach(i -> LOGGER.debug("Received the information of a receipts: {}", i));

		for (Receipts entity : items) {
			entity.setCreatedby(2L);
		receiptsDAO.save(entity);
		}

	}

}
