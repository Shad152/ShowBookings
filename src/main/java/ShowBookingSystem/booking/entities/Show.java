package ShowBookingSystem.booking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {
    private String showName;
    private String genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Objects.equals(showName, show.showName) && Objects.equals(genres, show.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showName, genres);
    }
}
