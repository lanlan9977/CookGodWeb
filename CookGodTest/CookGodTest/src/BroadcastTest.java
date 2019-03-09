
public class BroadcastTest {

	public static void main(String[] args) {
		// This registration token comes from the client FCM SDKs.
		String registrationToken = "YOUR_REGISTRATION_TOKEN";

		// See documentation on defining a message payload.
		Message message = Message.builder()
		    .putData("score", "850")
		    .putData("time", "2:45")
		    .setToken(registrationToken)
		    .build();

		// Send a message to the device corresponding to the provided
		// registration token.
		String response = FirebaseMessaging.getInstance().send(message);
		// Response is a message ID string.
		System.out.println("Successfully sent message: " + response);
	}

}