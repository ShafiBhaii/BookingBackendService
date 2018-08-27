package com.project.BookingBackendService;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingHistoryRepo extends MongoRepository<BookingHistoryModel, String>{
	
	public List<BookingHistoryModel> findByUserNo(Integer user);
	
}
