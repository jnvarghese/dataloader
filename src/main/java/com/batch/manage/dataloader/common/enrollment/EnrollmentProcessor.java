package com.batch.manage.dataloader.common.enrollment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.batch.manage.dataloader.common.student.StudentProcessor;
import com.batch.manage.dataloader.model.EnrollmentDTO;
import com.batch.manage.dataloader.model.entity.Sponsor;
import com.batch.manage.dataloader.model.entity.SponsorId;
import com.batch.manage.dataloader.model.entity.StudentId;
import com.batch.manage.dataloader.model.entity.enrollment.Enrollment;
import com.batch.manage.dataloader.model.entity.enrollment.Sponsee;
import com.batch.manage.dataloader.model.entity.enrollment.SponsorMaxout;
import com.batch.manage.dataloader.model.entity.enrollment.StudentMaxout;
import com.batch.manage.dataloader.service.SponsorDAO;
import com.batch.manage.dataloader.service.SponsorIdDAO;
import com.batch.manage.dataloader.service.StudentIdDAO;

public class EnrollmentProcessor implements ItemProcessor<EnrollmentDTO, Enrollment> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);
	DateFormat df = new SimpleDateFormat("MM/dd/YYYY");

	@Autowired
	private SponsorDAO sponsorDAO;
	
	@Autowired 
	private SponsorIdDAO sponsorIdDAO;

	@Autowired
	private StudentIdDAO studentIdDAO;

	private Long jobId;
	private Long parishId;

	public EnrollmentProcessor(Long jobId, Long parishId) {
		super();
		this.jobId = jobId;
		this.parishId = parishId;
	}

	@Override
	public Enrollment process(EnrollmentDTO dto) throws Exception {
		LOGGER.debug("dto {}", dto);
		Set<SponsorMaxout> sponsorMaxOuts = new HashSet<>();

		Set<StudentMaxout> studentMaxOuts = new HashSet<>();
		Set<Sponsee> sponsees = new HashSet<>();
		Sponsor sponsor;
		StudentId student;
		if (dto.getSponsorId().length() > 0) {

			Enrollment e = new Enrollment();

			Calendar maxOut = Calendar.getInstance();
			maxOut.set(Integer.valueOf(dto.getYear().split("\\.")[0]), getMonth(dto.getMonth()), 1, 0, 0);
			maxOut.add(Calendar.MONTH, -1);

			String[] sponsors = dto.getSponsorId().split("-");
			SponsorId sponsorId = sponsorIdDAO.findOne(sponsors[0], sponsors[1], sponsors[2], sponsors[3]);
			System.out.println(" sponsorId " +sponsorId);
			//sponsor = sponsorDAO.findOne(sponsors[0], sponsors[1], sponsors[2], sponsors[3]);
			if (null == sponsorId) { //TODO added job conditionhere
				//return null;
				
				sponsor = this.createSponsor(dto, sponsors[3]);
			}else {
				sponsor = new Sponsor();
				sponsor.setId(sponsorId.getId());
			}
			System.out.println(" ===== sposnorid "+sponsor);
			e.setSpn(sponsor);
			sponsorMaxOuts.add(new SponsorMaxout(sponsor,e,  maxOut.getTime()));
			e.setSponsorMaxOuts(sponsorMaxOuts);
			if (!dto.getTotal().equals("1.0")) {
				List<String> sList = new ArrayList<String>();
				if (dto.getTotal().equals("2.0")) {
					sList.add(dto.getChild1());
					sList.add(dto.getChild2());
				} else if (dto.getTotal().equals("3.0")) {
					sList.add(dto.getChild1());
					sList.add(dto.getChild2());
					sList.add(dto.getChild3());
				} else if (dto.getTotal().equals("4.0")) {
					sList.add(dto.getChild1());
					sList.add(dto.getChild2());
					sList.add(dto.getChild3());
					sList.add(dto.getChild4());
				} else if (dto.getTotal().equals("5.0")) {
					sList.add(dto.getChild1());
					sList.add(dto.getChild2());
					sList.add(dto.getChild3());
					sList.add(dto.getChild4());
					sList.add(dto.getChild5());
				} else if (dto.getTotal().equals("6.0")) {
					sList.add(dto.getChild1());
					sList.add(dto.getChild2());
					sList.add(dto.getChild3());
					sList.add(dto.getChild4());
					sList.add(dto.getChild5());
					sList.add(dto.getChild6());
				}

				for (String child : sList) {
					String[] students = child.split("-");
					student = studentIdDAO.findOne(students[0], students[1], students[2]);
					if (null != student) {
						sponsees.add(new Sponsee(e, getMonth(dto.getMonth()),
								Integer.valueOf(dto.getYear().split("\\.")[0]), student.getId()));
						studentMaxOuts.add(new StudentMaxout(student.getId(), e, maxOut.getTime()));
					}
				}
				e.setActualamount(Double.valueOf(dto.getAmount().replace("$", "")));
				e.setContributionAmount(Double.valueOf(dto.getAmount().replace("$", "")));
				e.setCreatedBy(2L);
				e.setJobId(jobId);
				e.setEffectiveDate(new Date(Long.valueOf(dto.getDate())));
				e.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
				e.setMiscAmount(0);
				e.setSponsees(sponsees);
				e.setStudentMaxOuts(studentMaxOuts);

			} else {
				String[] students = dto.getChild1().split("-");
				System.out.println(" students " + students[0] + " - " + students[1] + " - " + students[2]);
				student = studentIdDAO.findOne(students[0], students[1], students[2]);
				e.setActualamount(Double.valueOf(dto.getAmount().replace("$", "")));
				e.setContributionAmount(Double.valueOf(dto.getAmount().replace("$", "")));
				e.setCreatedBy(2L);
				e.setJobId(jobId);
				e.setEffectiveDate(new Date(Long.valueOf(dto.getDate())));
				e.setPaymentDate(new Date(Long.valueOf(dto.getDate())));
				e.setMiscAmount(0);
				System.out.println(student + " getMonth(dto.getMonth()) " + getMonth(dto.getMonth()));
				if(null != student) {
					sponsees.add(new Sponsee(e, getMonth(dto.getMonth()), Integer.valueOf(dto.getYear().split("\\.")[0]),
						student.getId()));
					e.setSponsees(sponsees);
					studentMaxOuts.add(new StudentMaxout(student.getId(), e, maxOut.getTime()));
					e.setStudentMaxOuts(studentMaxOuts);
				}else {
					LOGGER.warn("No students found for {}" ,students[0], students[1],students[2]);
				}
				
			}
			return e;
		}
		return null;
	}

	private Sponsor createSponsor(EnrollmentDTO dto, String sponsorCode) {
		Sponsor sponsor = new Sponsor();
		//sponsor.setAppartmentNumber(null);
		sponsor.setCity(dto.getCity());
		sponsor.setEmailAddress(dto.getEmail1());
		sponsor.setSponsorCode(sponsorCode);
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
		sponsor.setState(dto.getState());
		sponsor.setStreet(dto.getStreetAddress());
		sponsor.setCreatedBy(2L);
		sponsor.setEmailAddress2(dto.getEmail2());
		sponsor.setPhone1(dto.getPhone1());
		sponsor.setPhone2(dto.getPhone2());
		sponsor.setTransactionRemarks(dto.getTransaction());
		return sponsor;
	}

	private int getMonth(String month) {
		if ("January".equalsIgnoreCase(month)) {
			return 1;
		} else if ("February".equalsIgnoreCase(month)) {
			return 2;
		} else if ("March".equalsIgnoreCase(month)) {
			return 3;
		} else if ("April".equalsIgnoreCase(month)) {
			return 4;
		} else if ("May".equalsIgnoreCase(month)) {
			return 5;
		} else if ("June".equalsIgnoreCase(month)) {
			return 6;
		} else if ("July".equalsIgnoreCase(month)) {
			return 7;
		} else if ("August".equalsIgnoreCase(month)) {
			return 8;
		} else if ("September".equalsIgnoreCase(month)) {
			return 9;
		} else if ("October".equalsIgnoreCase(month)) {
			return 10;
		} else if ("November".equalsIgnoreCase(month)) {
			return 11;
		} else if ("December".equalsIgnoreCase(month)) {
			return 12;
		}
		return 0;
	}
}
