package hu.uni.miskolc.iit.java.technologies.carshop.api.model;

public class Car {

	public static enum Producer {
		AUDI, FERRARI, OPEL, BMW, KIA
	}

	private String plateNo;
	private Producer producer;
	private String color;
	private int numberOfDoors;
	private int horsePower;

	public Car() {
		super();
	}

	public Car(String plateNo, Producer producer, String color, int numberOfDoors, int horsePower) {
		super();
		this.plateNo = plateNo;
		this.producer = producer;
		this.color = color;
		this.numberOfDoors = numberOfDoors;
		this.horsePower = horsePower;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plateNo == null) ? 0 : plateNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (plateNo == null) {
			if (other.plateNo != null)
				return false;
		} else if (!plateNo.equals(other.plateNo))
			return false;
		return true;
	}

	
	
}
