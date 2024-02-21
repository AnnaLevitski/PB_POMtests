package okHttp;

import DTOforOKhttp.ContactDTO;
import DTOforOKhttp.ErrorDto;
import DTOforOKhttp.GettAllContactsDTO;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactsTestOkHttp {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibWFyYUBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcwODg3ODY1OSwiaWF0IjoxNzA4Mjc4NjU5fQ.3GLCN0yK9EyNJ8gum20_5PptTiBPOoosL0TBefpBuCk";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    @Test
    public void getAllContacts_success() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()


                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(), 200);
        GettAllContactsDTO contactsDTO = gson.fromJson(response.body().string(), GettAllContactsDTO.class);
        List<ContactDTO> list = contactsDTO.getContacts();
        for (ContactDTO c : list){
            System.out.println(c.getId());
            System.out.println(c.getEmail());
            System.out.println("===================");
        }
    }
    @Test
    public void getAllContacts_neativeWrongToken() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()
                .addHeader("Authorization", "token.gdgf.gdfgdf")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(), 401);
        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        Assert.assertEquals(errorDto.getMessage(), "Unauthorized");

    }

}
