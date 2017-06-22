package hu.uni.miskolc.iit.java.technologies.carshop.persist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.CarDAO;

public class CarDAOJSONTest {
	
	private CarDAO dao;
	
	@Before
	public void setUp(){
		dao = new CarDAOJSON("resources/cars.json");
	}

	@Test
	public void testCreateCar() {
		Car car = new Car("abc123", Car.Producer.AUDI, "red", 5, 100);
		dao.createCar(car);
	}

	@Test
	public void testReadCars() {
		fail("Not yet implemented");
	}

}
