package okHttp;

import DTOforOKhttp.ContactDTO;
import DTOforOKhttp.DeleteByIDResponceMessageDTO;
import DTOforOKhttp.ErrorDto;
import DTOforOKhttp.ResponseMessageDTO;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

//b8dcb171-553c-422e-8cec-7c0f23ba2fc3
//harry1564@gmail.com
//===================
//        40abce18-4ba2-4d74-8cf0-04fc1c72f00a
//stark201@gmail.com
//===================
//ec37070e-ec37-4c22-bee1-3a4eeb6f445c
//stark486@gmail.com
//===================
//bfebb946-046d-4a54-bf3b-e863224ad566
//stark1999@gmail.com
//===================
public class DeleteContactByIDOKHttp extends TestBaseOKHttp {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFyYUBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwODg3ODY1OSwiaWF0IjoxNzA4Mjc4NjU5fQ.3GLCN0yK9EyNJ8gum20_5PptTiBPOoosL0TBefpBuCk";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String contactID;
    @BeforeMethod
    public void preCondition() throws IOException {
        ContactDTO contactDTO = ContactDTO.builder().id("").name("Jhon"+randomInt()).lastName("Dojo"+randomInt()).phone("12345"+randomInt()).email("jhon"+randomInt()+"@gmail.com").address("London "+randomInt()).description("").build();
        Response response = (Response) postRequest(contactDTO, "/v1/contacts");
        if(response.isSuccessful()){
            ResponseMessageDTO responseMessageDTO = gson.fromJson(response.body().string(), ResponseMessageDTO.class);
            contactID = responseMessageDTO.getMessage().split(": ")[1];
        }
    }
    @Test
    public void deleteContact_success() throws IOException {
        Response response = (Response) deleteRequest("/v1/contacts/"+contactID);
        Assert.assertTrue(response.isSuccessful());
        DeleteByIDResponceMessageDTO dto = gson.fromJson(response.body().string(), DeleteByIDResponceMessageDTO.class);
        Assert.assertEquals(dto.getMessage(), "Contact was deleted!");
    }
    @Test
    public void deleteContact_negativeContactNotFound() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/ec37070e-ec37-4c22-bee1-3a4eeb6f445c")
                .delete()
                .addHeader("Authorization", token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getStatus(), 400);
    }

}
