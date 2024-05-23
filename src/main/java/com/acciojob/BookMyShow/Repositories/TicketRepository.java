package com.acciojob.BookMyShow.Repositories;

import com.acciojob.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

}
