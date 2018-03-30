package com.batch.manage.dataloader.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.batch.manage.dataloader.model.StudentDTO;

public class StudentExcelRowMapper implements RowMapper<StudentDTO> {

	
	DateFormat df = new SimpleDateFormat("MM/DD/YYYY",Locale.US);
	
    @Override
    public StudentDTO mapRow(RowSet rowSet) throws Exception {
    	
        return null;
    }
}
