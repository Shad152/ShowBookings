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
    public int hashCode(){
        int hash=Objects.hash(showName,genres);
        return hash;
    }
    @Override
    public boolean equals(Object other){
        if(this.getClass()!=other.getClass()){
            return false;
        }
        if(other==null || this==null){
            return false;
        }
        if(this.showName!=((Show) other).showName || this.genres!=((Show) other).genres ){
            return false;
        }
        return true;
    }
}
