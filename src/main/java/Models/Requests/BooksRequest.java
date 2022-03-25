package Models.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BooksRequest(@JsonProperty("firstname") String firstName,
                           @JsonProperty("lastname") String lastName,
                           @JsonProperty("totalprice") int totalPrice,
                           @JsonProperty("depositpaid") boolean depositPaid,
                           @JsonProperty("bookingdates") BookingDatesReq bookingDatesReq,
                           @JsonProperty("additionalneeds") String additionalNeeds){

}
