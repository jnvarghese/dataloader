package com.batch.manage.dataloader.common.sponsor;

import java.util.Calendar;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		double contribution = 740;
		Calendar expCal = Calendar.getInstance();
		
		expCal.add(Calendar.MONTH, -2);
		
		Date result = expCal.getTime();
System.out.println(result);
	}

}
