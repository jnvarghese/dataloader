package com.batch.manage.dataloader.listener;

import org.springframework.batch.core.ItemReadListener;

import com.batch.manage.dataloader.model.StudentDTO;

public class StudentReaderListener implements ItemReadListener<StudentDTO> {

	@Override
	public void afterRead(StudentDTO arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReadError(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

}
