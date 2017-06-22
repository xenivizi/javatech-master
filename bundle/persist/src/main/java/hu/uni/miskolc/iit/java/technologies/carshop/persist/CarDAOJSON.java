package hu.uni.miskolc.iit.java.technologies.carshop.persist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.CarDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.exceptions.CarNotFoundException;

public class CarDAOJSON implements CarDAO {

	private Logger LOGGER = LogManager.getLogger(CarDAOJSON.class);
	
	private File database;
	
	public CarDAOJSON(String databasePath) {
		this.database = new File(databasePath);
		LOGGER.debug(String.format("Car Databse : %s", database.getAbsolutePath()));
	}

	public void createCar(Car car) {
		Collection<Car> allCars = readCars();
		allCars.add(car);
		Car[] extendedDatabase = allCars.toArray(new Car[allCars.size()]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Car (%s) has been added!", car.getPlateNo()));
		

	}
	
	public void deleteCar(Car car) {
		Collection<Car> allCars = readCars();
		allCars.remove(car);

		Car[] extendedDatabase = allCars.toArray(new Car[allCars.size()]);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(database, extendedDatabase);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		LOGGER.info(String.format("Car (%s) has been deleted!", car.getPlateNo()));
		

	}	

	public Collection<Car> readCars() {
		ObjectMapper mapper = new ObjectMapper();
		Car[] cars = new Car[] {};
		try {
			cars = mapper.readValue(database, Car[].class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			LOGGER.fatal(String.format("IOException occured due to %s", e.getMessage()));
		}
		Collection<Car> result = new ArrayList<Car>(Arrays.asList(cars));
		return result;
	}

	public Car readCarByPlateNo(String plateNo) throws CarNotFoundException {
		for(Car car : readCars()){
			if(plateNo.equals(car.getPlateNo())){
				return car;
			}
		}
		throw new CarNotFoundException(String.format("There is no car with plate number: %s", plateNo));
	}

}
