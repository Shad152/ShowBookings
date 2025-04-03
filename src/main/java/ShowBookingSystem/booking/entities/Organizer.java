package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizer {
    private Long id;
    private String email;
    private String name;

}
