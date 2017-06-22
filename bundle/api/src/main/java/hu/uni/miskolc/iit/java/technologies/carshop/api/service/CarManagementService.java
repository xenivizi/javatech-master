package hu.uni.miskolc.iit.java.technologies.carshop.api.service;

import java.util.Collection;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.exceptions.UnknownCarException;

public interface CarManagementService {

	Collection<Car> listCars();
	Car getCarByPlateNo(String plateNo) throws UnknownCarException;
	void acquireCar(Car car);
	void delCar(String plateNo);
	
}
