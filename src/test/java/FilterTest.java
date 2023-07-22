import com.gridnine.testing.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class FilterTest {

    private final Filter filter = new Filter();

    private LocalDateTime currentDateAndTime;

    private List<Flight> flightList1;

    private List<Flight> flightList;

    @Before
    public void before() {

        currentDateAndTime = LocalDateTime.now();

        flightList1 = Arrays.asList(new Flight(Arrays.asList(
                        new Segment(currentDateAndTime.minusMinutes(2), currentDateAndTime.plusHours(2)),
                        new Segment(currentDateAndTime.plusHours(1), currentDateAndTime.plusHours(2)),
                        new Segment(currentDateAndTime.plusHours(2), currentDateAndTime.plusHours(1)),
                        new Segment(currentDateAndTime.plusHours(4), currentDateAndTime.plusHours(6)))),

                new Flight(Arrays.asList(
                        new Segment(currentDateAndTime.minusMinutes(2), currentDateAndTime.plusHours(3)),
                        new Segment(currentDateAndTime.plusHours(2), currentDateAndTime.plusHours(1)))),

                new Flight(Arrays.asList(
                        new Segment(currentDateAndTime.plusHours(1), currentDateAndTime.plusHours(2)),
                        new Segment(currentDateAndTime.plusHours(3), currentDateAndTime.plusHours(1))))
        );

    }

    @Test
    public void testBeforeCurrentTimeFilter() {

        flightList = filter.beforeCurrentTimeFilter(flightList1);

        Assert.assertEquals(flightList1.size(), 3);
        Assert.assertEquals(flightList.size(), 1);

    }

    @Test
    public void testSegmentsBeforeDeputeFilter() {

        flightList = filter.segmentsBeforeDeputeFilter(flightList1);

        Assert.assertEquals(flightList1.size(), 3);
        Assert.assertEquals(flightList.size(), 0);

    }

    @Test
    public void testTimeOnEarthMoreTwoHoursFilter() {

        flightList = filter.timeOnEarthMoreTwoHoursFilter(flightList1);

        Assert.assertEquals(flightList1.size(), 3);
        Assert.assertEquals(flightList.size(), 2);

    }
}