package com.batch.manage.dataloader.common.sponsor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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
import com.batch.manage.dataloader.model.entity.Receipt;
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
	private String parish;
	private String mission;

	private String startingCode;
	private String startingStudentCode;

	@Autowired
	private StudentIdDAO studentIdDAO;

	public SponsorProcessor(Long jobId, Long parishId, String parish, String mission, String startingCode,
			String startingStudentCode) {
		this.jobId = jobId;
		this.parishId = parishId;
		this.parish = parish;
		this.mission = mission;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"Job Id " + this.jobId + " and parish id " + parishId + " in LoggingStudentProcessor constructor");
		}
	}

	@Override
	public Sponsor process(SponsorDTO dto) throws Exception {
		//LOGGER.debug("dto {}", dto);
		Sponsor sponsor = new Sponsor();

		Set<SponsorMaxout> sponsorMaxOuts = new HashSet<>();

		sponsor.setAppartmentNumber(null);
		sponsor.setCity(dto.getCity());

		sponsor.setEmailAddress(dto.getEmail1());

		if ("&".equalsIgnoreCase(dto.getMi())) {
			String[] names;
			if (dto.getFirstName().contains(" ")) {
				names = dto.getFirstName().split(" ");
			} else {
				names = new String[2];
				names[0] = dto.getFirstName();
				names[1] = "N/A";
			}

			sponsor.setFirstName(names[0] == null ? "N/A" : names[0]);
			sponsor.setLastName(names[1]);
			sponsor.setHasAnyCoSponser(true);
			sponsor.setCoSponserName(dto.getLastName());
		} else {
			sponsor.setFirstName(dto.getFirstName());
			sponsor.setLastName(dto.getLastName());
			String mi = null;
			if ("&".equalsIgnoreCase(dto.getMi())) {
				mi = null;
			} else {
				mi = dto.getMi();
			}
			sponsor.setMiddleInitial(mi);

		}

		sponsor.setJobId(jobId);

		sponsor.setNickName(null);
		sponsor.setParishId(this.parishId);
		if (null != dto.getZip()) {
			if (dto.getZip().contains(".")) {
				String[] zips = dto.getZip().split("\\.");
				if (zips[0].length() < 5) {
					sponsor.setPostalCode(0 + zips[0]);
				} else {
					sponsor.setPostalCode(zips[0]);
				}

			} else {
				sponsor.setPostalCode(dto.getZip());
			}
		} else {
			sponsor.setPostalCode("00000");
		}

		// sponsor.setSponsorCode(dto.getSponsorId().split("-")[3]);
		// sponsor.setSponsorStatus(sponsorStatus);
		if ("New York".equalsIgnoreCase(dto.getState())) {

		}
		sponsor.setState(dto.getState());
		sponsor.setStreet(dto.getStreetAddress());
		sponsor.setCreatedBy(2L);
		sponsor.setEmailAddress2(dto.getEmail2());
		sponsor.setPhone1(dto.getPhone1());
		sponsor.setPhone2(dto.getPhone2());
		sponsor.setTransactionRemarks(dto.getTransaction());

		Enrollment enrollment = getEnrollment(dto, sponsor);
		if(null == enrollment) {
			LOGGER.warn(String.format(" No students found for job %d", jobId));
			return null;
		}
		sponsorMaxOuts.add(new SponsorMaxout(sponsor, enrollment, enrollment.getLocalExpDate()));
		sponsor.setSponsorMaxOuts(sponsorMaxOuts);
		sponsor.setErn(enrollment);

		return sponsor;
	}

	private Enrollment getEnrollment(SponsorDTO dto, Sponsor sponsor) {
		List<StudentId> studentIds = new ArrayList<StudentId>();
		Set<Sponsee> sponsees = new HashSet<>();
		/*
		Receipt receipt = new Receipt();
		if (null != sponsor.getAppartmentNumber()) {
			receipt.setAddress(sponsor.getAppartmentNumber() + " " + sponsor.getStreet() + " " + sponsor.getCity() + " "
					+ sponsor.getState() + " " + sponsor.getPostalCode());
		} else {
			receipt.setAddress(sponsor.getStreet() + " " + sponsor.getCity() + " " + sponsor.getState() + " "
					+ sponsor.getPostalCode());
		}
		receipt.setCreatedby(2L);
		receipt.setMissionname(this.mission);
		receipt.setParish(this.parish);
		receipt.setPaymentmethod(dto.getTransaction());
		if (sponsor.isHasAnyCoSponser()) {
			receipt.setReceivedfrom(sponsor.getFirstName() + " & " + sponsor.getCoSponserName());
		} else {
			receipt.setReceivedfrom(sponsor.getFirstName() + " " + sponsor.getLastName());
		}

		receipt.setTotal(dto.getAmount()); */
		Set<StudentMaxout> studentMaxOuts = new HashSet<>();
		Enrollment en = new Enrollment();

		String studentCount = "1";
		if (dto.getTotal().contains(".")) {
			studentCount = dto.getTotal().split("\\.")[0];
		}
		double contribution = Double.valueOf(dto.getAmount().replace("$", ""));
		int noOfChild = Integer.valueOf(studentCount);
		System.out.println(" ---- noOfChild --- "+ noOfChild);
		double perChildContribution = contribution / noOfChild;
		double reminderContributionPerChild = perChildContribution % 20;
		double totalReminder = reminderContributionPerChild * noOfChild;
		int maxMonth = (int) (perChildContribution / 20);
		// int expMonth = maxMonth / noOfChild;
		LOGGER.debug(sponsor.getFirstName() + " " + sponsor.getLastName() + " perChildContribution "
				+ (perChildContribution - reminderContributionPerChild) + " reminderContributionPerChild "
				+ reminderContributionPerChild + " noOfChild " + noOfChild + " totalReminder " + totalReminder);
		Calendar myCal = Calendar.getInstance();

		// 2/1/2018 --dto.getDate()
		Date effectiveDate = new Date(Long.valueOf(dto.getDate()));
		myCal.setTime(effectiveDate);
		// int diffCurrentAndEffectiveDate = (int)( (((Date) myCal.getTime()).getTime()
		// - effectiveDate.getTime())
		// / (1000 * 60 * 60 * 24) );
		// LOGGER.debug(String.format(" diffCurrentAndEffectiveDate %d
		// ",diffCurrentAndEffectiveDate));
		int monthToAdd	 = maxMonth - 1;
		myCal.add(Calendar.MONTH, monthToAdd);
		// 2018-02-01 00:00:00 --expirationDate
		Date expirationDate = myCal.getTime();
		int diffInDays = (int) ((expirationDate.getTime() - effectiveDate.getTime()) / (1000 * 60 * 60 * 24));
		LOGGER.debug(String.format(
				" ExpirationDate(Renewal) %s, Effectivedate %s, calander after adding monthToAdd (maxMonth-1) %d, diff %d ",
				expirationDate, effectiveDate, monthToAdd, diffInDays));
		// 01 -- month
		int month = myCal.get(Calendar.MONTH);
		int year = myCal.get(Calendar.YEAR);
		if (null != dto.getTotal() && dto.getTotal().contains(".")) {
			studentIds = studentIdDAO.list(this.parishId, Integer.valueOf(studentCount));
		}else {
			System.out.println("-----else-----");
		}
		if (studentIds.size() > 0) {
			int incrementedMonth = month + 1;
			LOGGER.debug(String.format(" Month -MyCal.get(Calendar.MONTH) %d year %d and incremented month is %d",
					month, year, incrementedMonth));
			for (StudentId s : studentIds) {
				sponsees.add(new Sponsee(en, incrementedMonth, year, s.getId()));
				studentMaxOuts.add(new StudentMaxout(s.getId(), en, expirationDate));
			}
			en.setLocalExpDate(expirationDate); // just to pass the value back - a place holder this is expdate for
												// sponsor_maxOut
			en.setSpn(sponsor);
			en.setContributionAmount(contribution - totalReminder); // 500 in excel (2 kids) this wll be
																					// 480
			en.setCreatedBy(2L);
			en.setJobId(jobId);
			en.setEffectiveDate(effectiveDate);
			en.setPaymentDate(new Date(Long.valueOf(dto.getDate()))); // same date as effective date
			en.setMiscAmount(totalReminder);
			en.setSponsees(sponsees);
			en.setStudentMaxOuts(studentMaxOuts);
			// en.setReceipt(receipt);
			return en;
		} else {
			return null;
		}
	}
}
