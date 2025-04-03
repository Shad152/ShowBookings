package ShowBookingSystem.booking.controllers;

import ShowBookingSystem.booking.entities.Bookings;
import ShowBookingSystem.booking.entities.Show;
import ShowBookingSystem.booking.entities.Slots;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shows")
public class ShowController {
    Map<String,Show> availableShows= new HashMap<>();
    Map<Long, Bookings> showsBookings= new HashMap<>();
    Map<String, Slots> allAvailableShows= new HashMap<>();
    @GetMapping
    public ResponseEntity<List<Slots>> getAllAvailableShows(){
        List<Slots> allSlots= new ArrayList<>();
        for(String sTime:allAvailableShows.keySet()){
            Slots slot=allAvailableShows.get(sTime);
            if(slot.getAvailableSeats()>0){
                allSlots.add(slot);
            }
        }
        return ResponseEntity.ok(allSlots);
    }
}
