package Models.Responses;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
public class UsersResponse {
    @JsonProperty("name")
    public String userName;

    @JsonProperty("job")
    public String userJob;

    @JsonProperty("id")
    public String userId;

    @JsonProperty("createdAt")
    public String userCreatedAt;
}
