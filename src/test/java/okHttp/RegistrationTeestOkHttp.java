package okHttp;

import DTOforOKhttp.AuthRequestDTO;
import DTOforOKhttp.AuthResponceDTO;
import DTOforOKhttp.ErrorDto;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTeestOkHttp extends TestBaseOKHttp {
    String additionalLink = "/v1/user/registration/usernamepassword";
    @Test
    public void registration_success() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("fa"+randomInt()+"@gmail.com").password("Mgr"+randomInt()+"$").build();
        Response response = (Response) postRequest(authRequestDTO, additionalLink);
        Assert.assertTrue(response.isSuccessful());
        AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class );
        Assert.assertNotNull(responceDTO.getToken());
    }
    @Test
    public void registration_negativeDuplicateUser() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("mara@gmail.com").password("Mmar123456$").build();
        Response response = (Response) postRequest(authRequestDTO, additionalLink);
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 409);
        ErrorDto responceDTO = gson.fromJson(response.body().string(), ErrorDto.class );
        Assert.assertNotNull(responceDTO.getStatus());
    }
}
