/**
 * Created by Kapil Gehlot on 7/16/2017.
 */

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.resource.instance.Sms;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
public class SMSBackend {
    public static void main(String[] args) {
        try {
            get("/", (req, res) -> "Helloo, World!");
            TwilioRestClient client = new TwilioRestClient("AC89e62ced39160f9eb7b66d20f5b6683a", "33b59fece9e45c1a780f071b50dcf8ce");

            post("/sms", (req, res) -> {
                String body = req.queryParams("Body");
                String to = req.queryParams("To");
                String from = "+12563882754";
                Map<String, String> callParams = new HashMap<>();
                callParams.put("To", to);
                callParams.put("From", from);
                callParams.put("Body", body);
                Sms message = client.getAccount().getSmsFactory().create(callParams);
                return message.getSid();

            });

        }catch (Exception e){
        }
    }
}
