package parctice;



//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.http.HttpResponse.BodyHandlers;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Main {
//
//	private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
//		StringBuilder builder = new StringBuilder();
//        for (Map.Entry<Object, Object> entry : data.entrySet()) {
//            if (builder.length() > 0) {
//                builder.append("&");
//            }
//            builder.append(entry.getKey());
//            builder.append("=");
//            builder.append(entry.getValue());
//        }
//        return HttpRequest.BodyPublishers.ofString(builder.toString());
//    }
//	
//	public static void sendWhatappMessage(String number,String textMessage) {
//		
//        String apiKey = "p2qar7nlfdsyioplfkihij5ch3crtdhq";
//        String appName = "LalitCustmerSupport";
//        String message = "{type: 'text', text: '"+textMessage+"'}";
//        String phone = number.toString(); // Enter the phone number in the format +country_code_phone_number
//
//        String url = "https://api.gupshup.io/sm/api/v1/msg";
//        String contentType = "application/x-www-form-urlencoded";
//        
//        Map<Object, Object> body = new HashMap<Object, Object>();
//        body.put("channel", "whatsapp");
//        body.put("source", "917834811114"); // Enter your Gupshup virtual number here
//        body.put("destination", phone);
//        body.put("message", message);
//        body.put("src.name", appName);
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("Content-Type", contentType)
//                .header("apikey", apiKey)
//                .POST(buildFormDataFromMap(body))
//                .build();
//
//        HttpClient client = HttpClient.newHttpClient();
//        try {
//            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//            System.out.println(response.body());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	}
//	
//    public static void main(String[] args) throws InterruptedException {
//    
//        sendWhatappMessage( "919766961594","this dummy mesaage to support ");
//    	
//    }
//
//    
//}
//
//
//import java.io.IOException;
//
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        String textMessage="with okhttp 3";
//        String apiKey = "vci0tvstuddz6hrxyrnrjktlrbippqtz";
//        String appName = "ARIHANTREPORTS";
//        String message = "{type: 'text', text: '"+textMessage+"'}";
//        String phone = "917499293044"; // Enter the phone number in the format +country_code_phone_number
//
//        String url = "https://api.gupshup.io/sm/api/v1/msg";
//        String contentType = "application/x-www-form-urlencoded";
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = RequestBody.create(MediaType.parse(contentType),
//                "channel=whatsapp&source=917834811114&destination=" + phone + "&message=" + message + "&src.name=" + appName);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .addHeader("Content-Type", contentType)
//                .addHeader("Api-Key", apiKey)
//                .addHeader("url", url)
//                .addHeader("APIKEY", apiKey)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//    }
//}


import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class Main {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "ACe5cc87c447d9c835cf13837f0bd987bc";
  public static final String AUTH_TOKEN = "[AuthToken]";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+917499293044"),
      "Your message")
    .create();

    System.out.println(message.getSid());
  }
}