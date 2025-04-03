package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slots {
    private String startTime;
    private String endTime;
    private Integer availableSeats;
    private Show show;
}
