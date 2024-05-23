package com.acciojob.BookMyShow.Repositories;


import com.acciojob.BookMyShow.Models.Show;
import com.acciojob.BookMyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {



}
