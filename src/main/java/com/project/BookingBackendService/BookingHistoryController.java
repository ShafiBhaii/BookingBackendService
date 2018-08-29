package com.project.BookingBackendService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://54.255.163.0:9011")
@RestController
public class BookingHistoryController {
	
	@Autowired
	private BookingHistoryRepo bRepo;
	
	@Autowired
    private JavaMailSender sender;
    
	@GetMapping("/api/booking")
	public List<BookingHistoryModel> getAllBooking() {
		return bRepo.findAll();
	}
	
	@PostMapping("/api/booking")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public BookingHistoryModel addBooking(@RequestBody BookingHistoryModel bm) {
		//bm.setBookingId(bm.getFlightNo().toString()+time);
		bRepo.save(bm);
		return bm;
	}
	
	@GetMapping("/api/booking/upcoming/{id}")
	public List<BookingHistoryModel> getUpcoming(@PathVariable("id")Integer id) throws ParseException {
		Date today = new Date();
		List<BookingHistoryModel> all = bRepo.findAll();
		List<BookingHistoryModel> sorted = new ArrayList<BookingHistoryModel>();
		
		for(BookingHistoryModel bm : all) {
			
			
		    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(bm.getDay());
		    
		if(bm.getUserNo()==id) {    
		    if(date1.after(today)) {
		    	sorted.add(bm);
		    }
		}
		    
		}
		return sorted;
		
	}
	@GetMapping("/getAll")
	public String getAll() {
		String s="";
		List<BookingHistoryModel> all = bRepo.findAll();
		for(BookingHistoryModel bm : all) {
			s = s + bm.getDay();
		}
		return s;
	}
	@GetMapping("/api/booking/{user}")
	public List<BookingHistoryModel> getBookingByUser(@PathVariable("user")Integer user)
	{
		return bRepo.findByUserNo(user);
	}
	
	@GetMapping("/api/booking/id/{id}")
	public BookingHistoryModel getBookById(@PathVariable("id")String id) {
		return bRepo.findById(id).get();
	}
	
	@DeleteMapping("/api/booking/{id}")
	public List<BookingHistoryModel> deletetBookById(@PathVariable("id")String id) {
		bRepo.deleteById(id);
		return bRepo.findAll();
	}
	
	
	@GetMapping("/api/booking/mailclient/{id}/{email}")
    @ResponseBody
    public BookingHistoryModel home(@PathVariable("id")String id, @PathVariable("email") String email) throws Exception {
        
        	BookingHistoryModel bm = bRepo.findById(id).get();
            sendEmail(email, bm);
            return bm;
        
    }
	
	@GetMapping("/api/booking/reset/{email}/{uid}")
	public BookingHistoryModel reset(@PathVariable("email")String email,@PathVariable("uid")Integer uid) throws Exception {
			sendEmail1(email, uid);
			return new BookingHistoryModel();
	}

    private void sendEmail(String email, BookingHistoryModel bm) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        
        helper.setTo(email);
     
        List<Passenger> pass = bm.getPassengers();
        
        String passengerString = "";
        for(Passenger p : pass) {
        	passengerString = passengerString + "Name : " + p.getName() + ", Age : " + p.getAge() + ", Gender : " + p.getGender() + "\n";
        }
        helper.setText(" Journey Date : " + bm.getDay() +"\n"
        		+ " PNR : " + bm.getBookingId() + "\n"
        		+ " Flight Name : " + bm.getFlightName() + "\n"
        		+ " From : " + bm.getDeparture() + "\n"
        		+ " To : " + bm.getArrival() + "\n"
        		+ " Time : " + bm.getTime() + "\n \n "
        		+ " Passenger(s) Details : \n"
        		+ passengerString + "\n\n\n"
        				+ "Amount Paid : " + bm.getAmount() + "\n\n\n"
        				+ "Thank you for using our service");
        
        helper.setSubject("King Flyer Ticket");
        
        sender.send(message);
    }
    private void sendEmail1(String email, Integer uId) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        
        helper.setTo(email);
        
        helper.setText("Click the Link Below to reset the password " + "\n" +
        "http://54.255.163.0:9011/reset/"+uId+"\n\n"
        +"For Godsake please remember your password");
        
        helper.setSubject("King Flyer Reset Password");
        
        sender.send(message);
    }
	
	
}
