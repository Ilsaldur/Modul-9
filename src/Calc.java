import java.util.Scanner;

public class Calc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Oppgi regnestykket: ");
		String equation = input.nextLine().replaceAll("\\s+", "");

		String[] numbers = equation.split("[-+*/]");
		int numberOne = 0, numberTwo = 0;

		for (int i = 0; i < numbers.length; i++) {
			numberOne = Integer.parseInt(numbers[0]);
			numberTwo = Integer.parseInt(numbers[1]);
		}

		String[] operatorArray = equation.split("[0-9]");
		String operator = "";

		for (int i = 0; i < operatorArray.length; i++) {
			operator = operatorArray[i];
		}

		int answer = 0;

		switch (operator) {
		case "+":
			answer = numberOne + numberTwo;
			break;
		case "-":
			answer = numberOne - numberTwo;
			break;
		case "*":
			answer = numberOne * numberTwo;
			break;
		case "/":
			answer = numberOne / numberTwo;
			break;
		}

		System.out.println("Resultatet: " + answer);

		input.close();

	}

}