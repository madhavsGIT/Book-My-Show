package com.acciojob.BookMyShow.Responses;

import com.acciojob.BookMyShow.Models.Show;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShowListResponse {
    private List<Show> showList;
}
