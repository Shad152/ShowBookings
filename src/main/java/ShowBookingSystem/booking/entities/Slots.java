package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slots {
    private String startTime;
    private String endTime;
    private Integer availableSeats;
    private Show show;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slots slots = (Slots) o;
        return Objects.equals(startTime, slots.startTime) && Objects.equals(endTime, slots.endTime) && Objects.equals(availableSeats, slots.availableSeats) && Objects.equals(show, slots.show);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, availableSeats, show);
    }


}
