package Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingDatesReq(@JsonProperty("checkin") String checkIn,
                              @JsonProperty("checkout") String checkOut) {

}
