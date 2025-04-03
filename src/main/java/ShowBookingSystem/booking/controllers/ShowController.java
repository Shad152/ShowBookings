package ShowBookingSystem.booking.controllers;

import ShowBookingSystem.booking.entities.Bookings;
import ShowBookingSystem.booking.entities.Show;
import ShowBookingSystem.booking.entities.Slots;
import ShowBookingSystem.booking.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;
    @GetMapping(path = "/getAllShows")
    public ResponseEntity<List<Slots>> getAllShows(){
        List<Slots> allAvailableShows=showService.getAllShows();
        return ResponseEntity.ok(allAvailableShows);
    }
    @GetMapping(path = "/getAllShows/genreName")
    public ResponseEntity<List<Slots>> getShowByGenre(@PathVariable String genreName){
        List<Slots> genreAllShow= showService.getAvialByGenre(genreName);
        if(genreAllShow.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genreAllShow);
    }
    @PostMapping(path = "/registerShow")
    public ResponseEntity<String> registerShow(@RequestBody Show show){
       Show registerShow= ShowService.registerShow(show);
       if(registerShow==null){
           return ResponseEntity.badRequest().body("This show already registered");
       }
       return ResponseEntity.ok(registerShow.getShowName()+" is successfully registered");

    }
    @PostMapping(path = "/bookShow")
    public ResponseEntity<String> bookTicket(@RequestBody Bookings booking){
        Bookings bookedTicket= ShowService.bookTicket(booking);
        if(bookedTicket==null){
            return ResponseEntity.badRequest().body("Requested booking can not be confirmed");
        }
        return ResponseEntity.ok("Ticket Id:" +bookedTicket.getId()+" is booked Successfully");
    }
    @PostMapping(path = "/onboardShowSlot")
    public ResponseEntity<String> onboardShowSlot(@RequestBody Slots slot){
        Slots onboardedSlot=ShowService.onboardShow(slot);
    }
    @DeleteMapping(path = "/cancelTicket/bookingId")
    public ResponseEntity<String> cancelBookingId(@PathVariable Long bookingId){

    }



}
