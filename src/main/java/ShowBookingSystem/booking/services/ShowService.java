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
    private static final Queue<Bookings> waitingList= new LinkedList<>();
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
           waitingList.offer(booking);
           return null;
       }
       allBookings.put(booking.getId(),booking);
       int totalSeatAvailable=booking.getSlot().getAvailableSeats();
       int bookedSeatsCount=booking.getSeatsBooked();
       booking.getSlot().setAvailableSeats(totalSeatAvailable-bookedSeatsCount);
       return booking;
    }
    public static String canceledTicket(Long bookingId) {
        Bookings canceledBooking= allBookings.remove(bookingId);
        if(canceledBooking==null){
            return "This booking doesn't exist";
        }
        int totalBooked=canceledBooking.getSeatsBooked();
        int totalWaiting=waitingList.size();
        Slots slot= canceledBooking.getSlot();
        Show canceledBookingShow=canceledBooking.getSlot().getShow();
        int remainingSeats=slot.getAvailableSeats();
        slot.setAvailableSeats(remainingSeats+totalBooked);
        int totalNow=totalBooked+remainingSeats;
        int counter=0;
        while(counter<totalWaiting){
            Bookings nextBooking= waitingList.poll();
            int requiredSeats= nextBooking.getSeatsBooked();
            if(nextBooking.getSlot().getShow().equals(canceledBookingShow) && nextBooking.getSlot().getStartTime().equals(canceledBooking.getSlot().getStartTime()) &&  requiredSeats<=totalNow){
                allBookings.put(nextBooking.getId(),nextBooking);
                nextBooking.getSlot().setAvailableSeats(totalNow-requiredSeats);
            }
            else{
                waitingList.offer(nextBooking);
            }
            counter++;
        }
        return "Ticket cancel successfully!";
    }
    public static Slots onboardShow(Slots slot) {
    }



    public List<Slots> getAllShows() {
    }

    public List<Slots> getAvialByGenre(String genreName) {
    }
}
