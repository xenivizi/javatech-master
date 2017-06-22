package hu.uni.miskolc.iit.java.technologies.carshop.service.dao;

import java.util.Collection;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Announcement;

public interface AnnouncementDAO {

	void createAnnouncement(Announcement announcement);

	Collection<Announcement> readAnnouncements();

}
