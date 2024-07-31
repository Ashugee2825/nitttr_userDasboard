package userProfile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class VerifyUtils {

    private static final String SECRET_KEY = "6Ld4YzwpAAAAAOfSasteL1pcc7UspP_WGyrsd894";
    private static final String RECAPTCHA_API_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        try {
            // Construct the POST request to the reCAPTCHA API
            String postParams = "secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
            byte[] postData = postParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;

            URL url = new URL(RECAPTCHA_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            connection.setUseCaches(false);

            // Write the POST data to the reCAPTCHA API
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            // Read the response from the reCAPTCHA API
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Parse the JSON response
                // In a real application, you may want to use a JSON library for parsing
                String jsonResponse = response.toString();
                return jsonResponse.contains("\"success\": true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
