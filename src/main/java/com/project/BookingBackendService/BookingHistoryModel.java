package com.project.BookingBackendService;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.project.BookingBackendService.Passenger;

@Document
public class BookingHistoryModel {
	@Id
	private String bookingId;
	private Integer userNo;
	private Integer flightNo;
	private String flightName;
	private String departure;
	private String arrival;
	private String time;
	private String day;
	private List<Passenger> passengers;
	private Integer amount;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFlightName() {
		return flightName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public BookingHistoryModel() {}
	
	public BookingHistoryModel(String bookingId, Integer flightNo, String flightName,Integer userNo, String departure, String arrival,
			String day, List<Passenger> passengers, Integer amount, String time) {
		super();
		this.bookingId = bookingId;
		this.flightNo = flightNo;
		this.userNo = userNo;
		this.departure = departure;
		this.arrival = arrival;
		this.day = day;
		this.time = time;
		this.passengers = passengers;
		this.flightName = flightName;
		this.amount = amount;
	}
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(Integer flightNo) {
		this.flightNo = flightNo;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	};
	

}
