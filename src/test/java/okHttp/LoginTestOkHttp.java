package okHttp;

import DTOforOKhttp.AuthRequestDTO;
import DTOforOKhttp.AuthResponceDTO;
import DTOforOKhttp.ErrorDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHttp {
        Gson gson = new Gson();
        public static final MediaType JSON =  MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("mara@gmail.com").password("Mmar123456$").build();
        RequestBody body = RequestBody.create( gson.toJson(authRequestDTO) , JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);
        AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class );
        System.out.println(responceDTO.getToken());
    }
    @Test
    public void loginNegative_unvalideEmail() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("maragmail.com").password("Mmar123456$").build();
        RequestBody body = RequestBody.create( gson.toJson(authRequestDTO) , JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);
        ErrorDto responceErrorDto = gson.fromJson(response.body().string(), ErrorDto.class );
        Assert.assertEquals(responceErrorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(responceErrorDto.getPath(), "/v1/user/login/usernamepassword");
        Assert.assertEquals(responceErrorDto.getStatus(), 401);
    }
    @Test
    public void loginNegative_unvalidePassword() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("mara@gmail.com").password("mar123456$").build();
        RequestBody body = RequestBody.create( gson.toJson(authRequestDTO) , JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);
        ErrorDto responceErrorDto = gson.fromJson(response.body().string(), ErrorDto.class );
        Assert.assertEquals(responceErrorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(responceErrorDto.getPath(), "/v1/user/login/usernamepassword");
        Assert.assertEquals(responceErrorDto.getStatus(), 401);
    }
    @Test
    public void loginNegative_unregistredUser() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("mara102Sd@gmail.com").password("Mmar123456$").build();
        RequestBody body = RequestBody.create( gson.toJson(authRequestDTO) , JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);
        ErrorDto responceErrorDto = gson.fromJson(response.body().string(), ErrorDto.class );
        Assert.assertEquals(responceErrorDto.getMessage(), "Login or Password incorrect");
        Assert.assertEquals(responceErrorDto.getPath(), "/v1/user/login/usernamepassword");
        Assert.assertEquals(responceErrorDto.getStatus(), 401);
    }



}
