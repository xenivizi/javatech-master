package hu.uni.miskolc.iit.java.technologies.carshop.api.model;

import java.util.Date;

public class Announcement {

	private Car car;
	private Price price;
	private Date startDate;
	private Date expirationDate;
	private boolean sold;

	public Announcement() {
		super();
	}

	public Announcement(Car car, Price price, Date startDate, Date expirationDate, boolean isSold) {
		super();
		this.car = car;
		this.price = price;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.sold = isSold;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean isSold) {
		this.sold = isSold;
	}

	public boolean openAnnouncement() {
		return !sold && expirationDate.after(new Date());
	}

	@Override
	public String toString() {
		return "Announcement [car=" + car + ", price=" + price + ", startDate=" + startDate + ", expirationDate="
				+ expirationDate + ", sold=" + sold + "]";
	}

}
