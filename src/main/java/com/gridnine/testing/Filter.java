package com.gridnine.testing;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    /**
     * вылет до текущего момента времени
     * @param flightList
     * @return
     */

    public List<Flight> beforeCurrentTimeFilter (List<Flight> flightList) {

        List<Flight> filter = new ArrayList<>();
        filter.addAll(flightList);

        for (int flight = 0; flight < flightList.size(); flight++) {

            if (flightList.get(flight).getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())) {
                filter.remove(flightList.get(flight));
            }

        }
        return filter;
    }

    /**
     * имеются сегменты с датой прилёта раньше даты вылета
     * @param flightList
     * @return
     */

    public List<Flight> segmentsBeforeDeputeFilter (List<Flight> flightList) {

        List<Flight> filter = new ArrayList<>();
        filter.addAll(flightList);

        for (int flight = 0; flight < flightList.size(); flight++) {
            for (int segment = 0; segment < flightList.get(flight).getSegments().size(); segment++) {
                LocalDateTime departureDate = flightList.get(flight).getSegments().get(segment).getDepartureDate();
                LocalDateTime arrivalDate = flightList.get(flight).getSegments().get(segment).getArrivalDate();
                if (departureDate.isAfter(arrivalDate)) {
                    filter.remove(flightList.get(flight));
                    break;
                }
            }
        }
        return filter;
    }

    /**
     * общее время, проведённое на земле превышает два часа
     * (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
     * @param flightList
     * @return
     */

    public List<Flight> timeOnEarthMoreTwoHoursFilter (List<Flight> flightList) {

        List<Flight> filter = new ArrayList<>();
        filter.addAll(flightList);

        for (int flight = 0; flight < flightList.size(); flight++) {
            for (int segment = 0; segment < flightList.get(flight).getSegments().size()-1; segment++) {
                LocalDateTime arrivalDate = flightList.get(flight).getSegments().get(segment).getArrivalDate();
                LocalDateTime departureDate = flightList.get(flight).getSegments().get(segment + 1).getDepartureDate();
                if (arrivalDate.plusHours(2).isBefore(departureDate)) {
                    filter.remove(flightList.get(flight));
                    break;
                }
            }
        }
        return filter;
    }

}
