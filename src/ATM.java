import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ATM {
	private static int id;
	private static Account[] accounts;
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		accounts = collectAccounts();

		askForId();

		boolean run = true;
		while (run) {
			System.out.printf("%s\n%s\n%s\n%s\n%s\n", "Meny", "1: Saldo", "2: Uttak", "3: Innskudd", "4: Avbryt");
			System.out.printf("Ditt valg? ");

			switch (getChoice()) {
			case 1:
				printBalance();
				break;
			case 2:
				System.out.print("Oppgi uttaksverdi: ");
				double uttaksverdi = input.nextInt();
				withdraw(uttaksverdi);
				break;
			case 3:
				System.out.print("Oppgi innskudsverdi: ");
				double innskuddsverdi = input.nextInt();
				deposit(innskuddsverdi);
				break;
			case 4:
				System.out.println();
				askForId();
				break;
			default:
				System.out.printf("%s \n\n", "Vennligst oppgi et tall fra 1-4");
				break;
			}
		}
		input.close();
	}

	private static Account[] collectAccounts() {
		accounts = new Account[11];
		for (int i = 1; i < accounts.length; i++) {
			accounts[i] = new Account(i, 1000);
		}
		return accounts;
	}
	
	private static int getChoice() {
		return input.nextInt();
	}

	private static void askForId() {
		System.out.print("Oppgi konto id: ");
		boolean run = false;
		while ( ! run ) {
			int inputId = input.nextInt();
			if (inputId < 0 || (inputId > 10)) {
				System.out.println("Oppgi id mellom 1 og 10.");
			} else {
				System.out.println();
				id = inputId;
				run = true;
			}
		}
	}

	private static void printBalance() {
		System.out.println();
		System.out.printf("%s %d %s %.2f \n\n", "Saldo for konto nr", accounts[id].getId(), "er",
				accounts[id].getBalance());
	}

	private static void withdraw(double amount) {
		if (accounts[id].getBalance() < amount) {
			System.out.printf("%s \n\n", "For liten saldo.");
		} else if (amount < 0) {
			System.out.printf("%s \n\n", "Ugyldig operasjon!");
		} else {
			System.out.printf("%s %.2f %s %d \n", "Tatt ut", amount, "fra konto nr", accounts[id].getId());
			accounts[id] = new Account(accounts[id].getId(), (accounts[id].getBalance() - amount));
			if (accounts[id].getBalance() > 0) {
				System.out.printf("%s %.2f \n\n", "Ny saldo er", (accounts[id].getBalance()));
			}
		}
	}

	private static void deposit(double amount) {
		if (amount > 0) {
			System.out.printf("%s %.2f %s %d \n", "Satt inn", amount, "på konto nr", accounts[id].getId());
			accounts[id] = new Account(accounts[id].getId(),
					(accounts[id].getBalance() + amount));
			System.out.printf("%s %.2f \n\n", "Ny saldo er", (accounts[id].getBalance()));
		} else if (amount < 0)
			System.out.printf("%s \n\n", "Ugyldig operasjon!");
	}
}

class Account {
	int id;
	double balance;
	GregorianCalendar dateCreated;
	static double annualInterestRate;

	public Account(int newId, double newBalance) {
		id = newId;
		balance = newBalance;
		dateCreated = (GregorianCalendar) GregorianCalendar.getInstance();
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public String getDateCreated() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(dateCreated.getTime());
	}

	public static double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public static void setAnnualInterestRate(double newAnnualInterestRate) {
		annualInterestRate = newAnnualInterestRate;

	}

	public double getMontlyInterest() {
		return balance * (annualInterestRate / 1200);
	}

	public boolean withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			balance -= amount;
			return true;
		}

		return false;
	}

	public boolean deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			return true;
		}

		return false;
	}

}
