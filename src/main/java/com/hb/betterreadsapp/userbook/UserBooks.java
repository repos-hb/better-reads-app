package com.hb.betterreadsapp.userbook;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "book_by_user_and_bookid")
public class UserBooks {
	
	@PrimaryKey
	private UserBooksPrimaryKey key;
	
	@Column("start_date")
	@CassandraType(type = Name.DATE)
	private LocalDate startDate;
	
	@Column("end_date")
	@CassandraType(type = Name.DATE)
	private LocalDate endDate;
	
	@Column("status")
	@CassandraType(type = Name.TEXT)
	private String status;
	
	@Column("rating")
	@CassandraType(type = Name.INT)
	private int rating;

	public UserBooksPrimaryKey getKey() {
		return key;
	}

	public void setKey(UserBooksPrimaryKey key) {
		this.key = key;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
}
