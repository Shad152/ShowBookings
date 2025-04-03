package ShowBookingSystem.booking.services;

import ShowBookingSystem.booking.entities.Bookings;
import ShowBookingSystem.booking.entities.Show;
import ShowBookingSystem.booking.entities.Slots;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        return "Ticket canceled successfully!";
    }
    public static Slots onboardShow(Slots slot) {
        String sTime=slot.getStartTime();
        String eTime=slot.getEndTime();
        Integer duration=Integer.parseInt(eTime.substring(0,sTime.length()-3))-Integer.parseInt(sTime.substring(0,sTime.length()-3));
        if(duration!=1){
            return null;
        }
        return slot;
    }



    public List<Slots> getAllShows() {
        List<Slots> allSlots = new ArrayList<>();
        for (List<Slots> slots : availableSlots.values()) {
            allSlots.addAll(slots);
        }
        return allSlots;
    }

    public List<Slots> getAvialByGenre(String genreName) {
        return shows.stream()
                .filter(show -> show.getGenres().equals(genreName))
                .flatMap(show -> availableSlots.getOrDefault(show.getShowName(), List.of()).stream())
                .sorted(Comparator.comparing(Slots::getStartTime))  // Sorting by start time
                .collect(Collectors.toList());
    }
}
