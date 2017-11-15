import java.util.Scanner;

public class CaesarCipher {

	public static String cipher(String message, int shift) {
		String cipher = "";
		for (int i = 0; i < message.length(); i++) {
			int charPosition = message.charAt(i);
			if (Character.isUpperCase(charPosition)) {
				charPosition = charPosition + (shift % 26);
				if (charPosition > 'Z')
					charPosition = charPosition - 26;
			}

			cipher = cipher + (char) charPosition;
		}
		return cipher;
	}

	public static String decipher(String message, int shift) {
		String decipher = "";
		for (int i = 0; i < message.length(); i++) {
			int charPosition = message.charAt(i);
			if (Character.isUpperCase(charPosition)) {
				charPosition = charPosition - (shift % 26);
				if (charPosition < 'A')
					charPosition = charPosition + 26;
			}
			decipher = decipher + (char) charPosition;
		}
		return decipher;
	}

	public static void bruteForce(String message) {
		for (int i = 0; i < 25; i++) {
			System.out.println(decipher(message, i));
		}
	}
}

class TestCaesarCipher {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("*********************************************************************");
		while (true) {
			System.out.printf("%s\n%s\n%s\n%s\n", "1: Cipher Text", "2: Decipher Text.", "3: Brute Force Text.",
					"4: Exit.");
			System.out.println("*********************************************************************");
			System.out.print("Your choice?: ");
			int userChooses = input.nextInt();
			System.out.println("*********************************************************************");

			
			switch (userChooses) {
			case 1:
				encode(getShift(), getMessage());
				break;
			case 2:
				decode(getShift(), getMessage());
				break;
			case 3:
				bruteForce(getMessage());
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}

	private static String getMessage() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter message: ");
		String message = input.nextLine().toUpperCase();

		return message;
	}

	private static int getShift() {
		System.out.print("Please enter the shift factor 1-25: ");
		int shift = input.nextInt();

		return shift;
	}

	private static void bruteForce(String message) {
		System.out.println("Decrypted message is:");
		CaesarCipher.bruteForce(message);
		System.out.println("*********************************************************************");
	}

	private static void decode(int shift, String message) {
		System.out.println("Decrypted message is:");
		System.out.println(CaesarCipher.decipher(message, shift));
		System.out.println("*********************************************************************");
	}

	private static void encode(int shift, String message) {
		System.out.println("Decrypted message is:");
		System.out.println(CaesarCipher.cipher(message, shift));
		System.out.println("*********************************************************************");

	}

}