package com.acciojob.BookMyShow.Repositories;

import com.acciojob.BookMyShow.Models.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeats,Integer> {

}
