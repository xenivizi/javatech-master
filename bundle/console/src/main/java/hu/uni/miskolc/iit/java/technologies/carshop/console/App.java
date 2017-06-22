package hu.uni.miskolc.iit.java.technologies.carshop.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;

import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Announcement;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Price;
import hu.uni.miskolc.iit.java.technologies.carshop.api.model.Car.Producer;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.AnnouncementManagementService;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.CarManagementService;
import hu.uni.miskolc.iit.java.technologies.carshop.api.service.exceptions.UnknownCarException;
import hu.uni.miskolc.iit.java.technologies.carshop.persist.AnnouncementDAOJSON;
import hu.uni.miskolc.iit.java.technologies.carshop.persist.CarDAOJSON;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.AnnouncementDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.dao.CarDAO;
import hu.uni.miskolc.iit.java.technologies.carshop.service.impl.AnnouncementManagementServiceImpl;
import hu.uni.miskolc.iit.java.technologies.carshop.service.impl.CarManagementServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
	private static CarManagementService carManager;
	private static AnnouncementManagementService announcementManager;

	static {
		CarDAO carDAO = new CarDAOJSON("resources/cars.json");
		AnnouncementDAO announcementDAO = new AnnouncementDAOJSON("resources/announcements.json");
		carManager = new CarManagementServiceImpl(carDAO);
		announcementManager = new AnnouncementManagementServiceImpl(announcementDAO, carDAO);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean run = true;
		while (run) {

			String line = br.readLine();
			if ("exit".equals(line)) {
				break;
			}
			if ("list cars".equals(line)) {
				listCars();
			}
			if ("insert car".equals(line)) {
				addCar();
			}
			if ("delete car".equals(line)) {
				deleteCar();
			}			
			if ("list announcements".equals(line)) {
				printAnnouncements(announcementManager.listAnnouncements());
			}
			if ("list open announcements".equals(line)) {
				printAnnouncements(announcementManager.listOpenAnnouncements());
			}
			if ("add announcement".equals(line)) {
				addAnnouncement();
			}
		}

	}

	private static void listCars() {
		final int tableWidth = 30;
		printHorisontalLine(tableWidth);
		System.out.println("| PlateNo | Producer | Color | # Doors | Horse Power |");
		printHorisontalLine(tableWidth);
		for (Car car : carManager.listCars()) {
			System.out.println(String.format("| %1$7s | %2$8s | %3$5s | %4$7d | %5$11d |", car.getPlateNo(),
					car.getProducer(), car.getColor(), car.getNumberOfDoors(), car.getHorsePower()));
			printHorisontalLine(tableWidth);
		}
	}

	private static void printHorisontalLine(int length) {
		for (int i = 0; i < length; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static void addCar() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Plate No.: ");
		String plateNo = br.readLine();
		System.out.println("Producer: ");
		String str = new String(br.readLine());
		Producer producer = Producer.valueOf(str.toUpperCase());
		
		System.out.println("Color");
		String color = br.readLine();
		System.out.println("Number of Doors: ");
		int doors = Integer.parseInt(br.readLine());
		System.out.println("Horse Power: ");
		int horsePower = Integer.parseInt(br.readLine());
		Car car = new Car(plateNo, producer, color, doors, horsePower);
		carManager.acquireCar(car);

	}

	private static void deleteCar() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Plate No.: ");
		String plateNo = br.readLine();
		carManager.delCar(plateNo);

	}
	
	
	private static void printAnnouncements(Collection<Announcement> announcements) {
		final int tableWidth = 80;
		printHorisontalLine(tableWidth);
		System.out.println(
				"| 						Car							 | 	Price			 | Start Date | Expire Date | open  |");
		System.out.println(
				"| PlateNo | Producer | Color | # Doors | Horse Power | Amount | Currency |			  |		        |       |");
		printHorisontalLine(tableWidth);
		for (Announcement announcement : announcements) {
			Car car = announcement.getCar();
			Price price = announcement.getPrice();
			System.out.println(String.format(
					"| %1$7s | %2$8s | %3$5s | %4$7d | %5$11d | %6$5.2f | %7$8s | %8$10s | %9$5s | %10$5s |",
					car.getPlateNo(), car.getProducer(), car.getColor(), car.getNumberOfDoors(), car.getHorsePower(),
					price.getAmount(), price.getCurrency().toString(), announcement.getStartDate().toString(),
					announcement.getExpirationDate().toString(), announcement.openAnnouncement()));
		}
	}

	private static void addAnnouncement() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Plate No.: ");
		String plateNo = br.readLine();
		System.out.println("Expiration Date (YYYY-MM-dd): ");
		String expireDateStr = br.readLine();
		System.out.println("Price (amount currency e.g. 100 HUF): ");
		String priceStr = br.readLine();

		try {
			Car car = carManager.getCarByPlateNo(plateNo);
			DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			Date expireDate = df.parse(expireDateStr);
			Price price = new Price(Double.parseDouble(priceStr.split(" ")[0]),
					Currency.getInstance(priceStr.split(" ")[1]));

			announcementManager.announce(plateNo, expireDate, price);
		} catch (UnknownCarException e) {
			// TODO			
			System.out.println("No car in database, not added");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
