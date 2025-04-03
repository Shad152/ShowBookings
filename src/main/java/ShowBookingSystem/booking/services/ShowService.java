package ShowBookingSystem.booking.services;

import ShowBookingSystem.booking.entities.Bookings;
import ShowBookingSystem.booking.entities.Show;
import ShowBookingSystem.booking.entities.Slots;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowService {
    private static final Set<Show> shows= new HashSet<>();
    private static final Map<String,List<Slots>> availableSlots= new HashMap<>();
    private static final Queue<Bookings> canceledTicket= new LinkedList<>();
    private static final Map<Long,Bookings>  allBookings= new HashMap<>();

    public static Show registerShow(Show show) {
        if(shows.contains(show)){
            return null;
        }
        shows.add(show);
        return show;
    }

    public static Bookings bookTicket(Bookings booking) {
       if(booking.getSeatsBooked()>booking.getSlot().getAvailableSeats()){
           canceledTicket.offer(booking);
           return null;
       }
       allBookings.put(booking.getId(),booking);
       int totalSeatAvailable=booking.getSlot().getAvailableSeats();
       int bookedSeatsCount=booking.getSeatsBooked();
       booking.getSlot().setAvailableSeats(totalSeatAvailable-bookedSeatsCount);
       return booking;
    }
    public static String canceledTicket(Long bookingId) {

    }
    public static Slots onboardShow(Slots slot) {
    }



    public List<Slots> getAllShows() {
    }

    public List<Slots> getAvialByGenre(String genreName) {
    }
}
