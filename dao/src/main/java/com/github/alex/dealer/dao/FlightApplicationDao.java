package com.github.alex.dealer.dao;

import com.github.alex.dealer.data.FlightApplication;

import java.util.List;

public interface FlightApplicationDao {
    List<FlightApplication> getFlightApplications();

    Long save(FlightApplication flightApplication);


}
