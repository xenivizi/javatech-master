package hu.uni.miskolc.iit.java.technologies.carshop.service.dao;

import java.util.Collection;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.exceptions.CarNotFoundException;

public interface CarDAO {

	void createCar(Car car);
	void deleteCar(Car car);
	
	Collection<Car> readCars();
	Car readCarByPlateNo(String plateNo) throws CarNotFoundException;
	
	
	
}
