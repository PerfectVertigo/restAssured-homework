package Models.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record BookingDatesReq(@JsonProperty("checkin") Date checkIn,
                              @JsonProperty("checkout") Date checkOut) {

}
