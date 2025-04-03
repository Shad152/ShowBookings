package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private Long id;
    private Long organizerId;
    private String showName;
    private String genres;

}
