package com.batch.manage.dataloader.excel;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.batch.manage.dataloader.model.StudentDTO;

public class StudentExcelRowMapper implements RowMapper<StudentDTO> {

    @Override
    public StudentDTO mapRow(RowSet rowSet) throws Exception {
    	System.out.println( " ---- rowSet  ---- "+rowSet);
        StudentDTO student = new StudentDTO();
        student.setNameOfChild(rowSet.getColumnValue(1));
        student.setGender(rowSet.getColumnValue(2));
        student.setDateOfBirth(rowSet.getColumnValue(3));
        student.setGrade(rowSet.getColumnValue(4));
        student.setHobby(rowSet.getColumnValue(5));
        student.setFavoriteColor(rowSet.getColumnValue(6));
        student.setFavoriteGame(rowSet.getColumnValue(7));
        student.setTalent(rowSet.getColumnValue(8));
        student.setNameOfParent(rowSet.getColumnValue(9));
        student.setOccupationOfParent(rowSet.getColumnValue(10));
        student.setProjectLocation(rowSet.getColumnValue(11));
        student.setMotherTongue(rowSet.getColumnValue(12));
        student.setAddLinkForPicture(rowSet.getColumnValue(13));
        return student;
    }
}
