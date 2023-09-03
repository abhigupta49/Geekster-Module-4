import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class NationalizeAPIClient {
    public static void main(String[] args) {
        try {
            //Define the api url
            String apiUrl = "https://api.nationalize.io/?name=nathaniel";

            //create a URL object with the API URl
            URL url  = new URL(apiUrl);


            //Open a connextion to the URl
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Set the HTTP GET request method
            connection.setRequestMethod("GET");

            //Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            if (responseCode==HttpURLConnection.HTTP_OK){
                //Read the response code frome API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line=reader.readLine())!=null){
                    response.append(line);

                }
                reader.close();

                System.out.println("API Response");
                System.out.println(response.toString());
//                System.out.println(reader.readLine());

            }
            else{
                System.err.println("Failed to fetch data from the API. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
