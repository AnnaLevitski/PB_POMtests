package okHttp;

import DTOforOKhttp.AuthRequestDTO;
import DTOforOKhttp.AuthResponceDTO;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Random;

public class TestBaseOKHttp {
    Gson gson = new Gson();
    public static final MediaType JSON =  MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    String baseURL = "https://contactapp-telran-backend.herokuapp.com";
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFyYUBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwODg3ODY1OSwiaWF0IjoxNzA4Mjc4NjU5fQ.3GLCN0yK9EyNJ8gum20_5PptTiBPOoosL0TBefpBuCk";
    Boolean firstShot = true;

    public void getNewToken() throws IOException {
        AuthRequestDTO authRequestDTO = AuthRequestDTO.builder().username("mara@gmail.com").password("Mmar123456$").build();
        RequestBody body = RequestBody.create(gson.toJson(authRequestDTO) , JSON);
        Response response = (Response) postRequest(body, "/v1/user/login/usernamepassword");
        if(response.isSuccessful()){
            try{
                AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class );
                token = responceDTO.getToken();
            }catch (Exception e){

            }
        }
    }
    public Object getRequest(String additionalURL) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL+additionalURL)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if(!response.isSuccessful()){
            request = new Request.Builder()
                    .url(baseURL+additionalURL)
                    .get()
                    .addHeader("Authorization", token)
                    .build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                getNewToken();
                getRequest(additionalURL);
            }
        }
        if(response.isSuccessful()){
            try{
                AuthResponceDTO responceDTO = gson.fromJson(response.body().string(), AuthResponceDTO.class );
                token = responceDTO.getToken();
            }catch (Exception e){

            }
        }
        return response;
    }

    public Object postRequest(Object intoBody, String additionalURL) throws IOException {
        RequestBody body = RequestBody.create(gson.toJson(intoBody), JSON);
        Request request = new Request.Builder()
                .url(baseURL+additionalURL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            request = new Request.Builder()
                    .url(baseURL + additionalURL)
                    .post(body)
                    .addHeader("Authorization", token)
                    .build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful() && firstShot) {
                getNewToken();
                getRequest(additionalURL);
                firstShot = false;
            }
        }
        return response;
    }

    public Object deleteRequest(String additionalURL) throws IOException {
        Request request = new Request.Builder()
                .url(baseURL+additionalURL)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            request = new Request.Builder()
                    .url(baseURL + additionalURL)
                    .delete()
                    .addHeader("Authorization", token)
                    .build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful() && firstShot) {
                getNewToken();
                getRequest(additionalURL);
                firstShot = false;
            }
        }
        return response;
    }
    public int randomInt(){
        Random r = new Random();
        return Math.abs(r.nextInt(999999));
    }

}
