package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@Data

public class WaitList {
    private Queue<Bookings>  waitingList= new LinkedList<>();

    public void AddToWaitList(Bookings booking){
        waitingList.offer(booking);
    }

}
