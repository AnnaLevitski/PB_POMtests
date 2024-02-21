package DTOforOKhttp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//{
//        "token": "string"
//        }
@Getter
@Setter
@ToString
@Builder
public class AuthResponceDTO {
    private String token;
}
