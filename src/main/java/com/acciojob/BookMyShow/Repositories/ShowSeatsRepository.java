package com.acciojob.BookMyShow.Repositories;

import com.acciojob.BookMyShow.Models.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeats, Integer> {

}
