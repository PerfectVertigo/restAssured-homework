package Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingDatesRes(@JsonProperty("checkin") String checkIn,
                              @JsonProperty("checkout") String checkOut) {
}
