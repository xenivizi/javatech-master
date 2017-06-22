package hu.uni.miskolc.iit.java.technologies.carshop.service.impl;

import java.util.Collection;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.CarManagementService;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.exceptions.UnknownCarException;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.CarDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.exceptions.CarNotFoundException;

public class CarManagementServiceImpl implements CarManagementService {

	private CarDAO carDAO;

	public CarManagementServiceImpl(CarDAO carDAO) {
		super();
		this.carDAO = carDAO;
	}

	public Collection<Car> listCars() {
		return carDAO.readCars();
	}

	public void acquireCar(Car car) {
		try {
			Car result = carDAO.readCarByPlateNo(car.getPlateNo());
			System.out.println("Existing car, not added.");
		} catch (CarNotFoundException e) {
			carDAO.createCar(car);
		}		
	}
	
	public void delCar(String plateNo) {
		try {
			Car result = carDAO.readCarByPlateNo(plateNo);
			carDAO.deleteCar(result);
		} catch (CarNotFoundException e) {
			System.out.println("Not existing car, not deleted.");
		}		
	}	
	
	public Car getCarByPlateNo(String plateNo) throws UnknownCarException {
		try {
			Car result = carDAO.readCarByPlateNo(plateNo);
			return result;
		} catch (CarNotFoundException e) {
			throw new UnknownCarException(plateNo);
		}
	}

}
