package hu.uni.miskolc.iit.java.technologies.carshop.api.service;

import java.util.Date;
import java.util.Collection;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Announcement;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Price;

public interface AnnouncementManagementService {

	Collection<Announcement> listAnnouncements();
	Collection<Announcement> listOpenAnnouncements();
	
	void announce(String carPlateNo, Date expire, Price price);
}
