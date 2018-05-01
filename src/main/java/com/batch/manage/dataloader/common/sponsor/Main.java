package com.batch.manage.dataloader.common.sponsor;

public class Main {

	public static void main(String[] args) {
		double contribution = 740;
		int noOfChild =5;
		//double mAmount = a % 20;
		double perChildContribution = contribution/noOfChild;
		double reminderContributionPerChild = perChildContribution % 20;
		double totalReminder = reminderContributionPerChild * noOfChild;
		int maxMonth = (int) (perChildContribution/20);
		//System.out.println(mAmount);
		System.out.println(totalReminder);
		//System.out.println(b);		
		System.out.println(maxMonth);

	}

}
