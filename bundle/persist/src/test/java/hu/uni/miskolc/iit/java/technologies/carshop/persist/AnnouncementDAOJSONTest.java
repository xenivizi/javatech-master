package hu.uni.miskolc.iit.java.technologies.carshop.persist;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Announcement;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Price;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.AnnouncementDAO;

public class AnnouncementDAOJSONTest {

	private AnnouncementDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = new AnnouncementDAOJSON("resources/announcements.json");
	}

	@Test
	public void testCreateAnnouncement() {
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + 24*60*60*1000);
		dao.createAnnouncement(new Announcement(new Car("abc123", Car.Producer.BMW, "green", 3, 200), new Price(100, Currency.getInstance("HUF")), today, tomorrow, false));
	}

	@Test
	public void testReadAnnouncements() {
		Collection<Announcement> announcements = dao.readAnnouncements();
		System.out.println(announcements);
	}

}
