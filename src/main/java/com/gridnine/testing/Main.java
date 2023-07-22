package com.gridnine.testing;

import com.gridnine.testing.model.Flight;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();

        Filter filter = new Filter();

        System.out.println("Список всех рейсов:");
        flightList.forEach(System.out::println);


        System.out.printf("%nВылет до текущего момента%n");
        filter.beforeCurrentTimeFilter(flightList).forEach(System.out::println);

        System.out.printf("%nСегменты с датой прилета раньше даты вылета%n");
        filter.segmentsBeforeDeputeFilter(flightList).forEach(System.out::println);

        System.out.printf("%nОбщее время на земле превышает 2 часа%n");
        filter.timeOnEarthMoreTwoHoursFilter(flightList).forEach(System.out::println);
    }
}
