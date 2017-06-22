package hu.uni.miskolc.iit.java.technologies.carshop.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Announcement;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Price;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.AnnouncementManagementService;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.AnnouncementDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.CarDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.exceptions.CarNotFoundException;

public class AnnouncementManagementServiceImpl implements AnnouncementManagementService {
	
	private static Logger LOG = LogManager.getLogger(AnnouncementManagementServiceImpl.class);
	
	
	private AnnouncementDAO announcementDAO;
	private CarDAO carDAO;
	
	

	public AnnouncementManagementServiceImpl(AnnouncementDAO announcementDAO, CarDAO carDAO) {
		super();
		this.announcementDAO = announcementDAO;
		this.carDAO = carDAO;
	}

	public Collection<Announcement> listAnnouncements() {
		return announcementDAO.readAnnouncements();
	}

	public Collection<Announcement> listOpenAnnouncements() {
		Collection<Announcement> result = new ArrayList<Announcement>();
		for(Announcement announcement : announcementDAO.readAnnouncements()){
			if(announcement.openAnnouncement()){
				result.add(announcement);
			}
		}
		return result;
	}

	public void announce(String carPlateNo, Date expire, Price price) {
		try{
		Car car = carDAO.readCarByPlateNo(carPlateNo);
		Announcement announcement = new Announcement(car, price, new Date(), expire, false);
		announcementDAO.createAnnouncement(announcement);
		}
		catch(CarNotFoundException ex){
			LOG.warn(ex.getMessage());
		}

	}

}
