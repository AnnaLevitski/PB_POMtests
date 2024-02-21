package DTOforOKhttp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//
//{
//        "username": "string",
//        "password": "oINsOnjZ:YYRj_,~lZkt^A'{zv>`R_RgF`yLqhGMrn\"0NB3<ov01 >2/p[H5w-|0?xQV9SU/Y!B2R/i\""
//        }
@Getter
@Setter
@ToString
@Builder
public class AuthRequestDTO {
    private String username;
    private String password;
}
