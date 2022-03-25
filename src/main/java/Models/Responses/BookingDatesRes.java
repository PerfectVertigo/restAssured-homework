package Models.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record BookingDatesRes(@JsonProperty("checkin") Date checkIn,
                              @JsonProperty("checkout") Date checkOut) {
}
