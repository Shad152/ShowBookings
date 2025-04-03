package ShowBookingSystem.booking.services;

import ShowBookingSystem.booking.entities.Bookings;
import ShowBookingSystem.booking.entities.Show;
import ShowBookingSystem.booking.entities.Slots;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShowService {
    Set<Show> shows= new HashSet<>();
    Map<String,List<Slots>> availableSlots= new HashMap<>();

    public static Show registerShow(Show show) {
    }

    public static Bookings bookTicket(Bookings booking) {
    }

    public static Slots onboardShow(Slots slot) {
    }

    public List<Slots> getAllShows() {
    }

    public List<Slots> getAvialByGenre(String genreName) {
    }
}
