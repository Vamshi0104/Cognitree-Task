package car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

class Car {
	String carname;
	double horsepower;
	String origin;

	Car(String carname, String origin, double horsepower) { // Parameterized Constructor
		this.carname = carname;
		this.origin = origin;
		this.horsepower = horsepower;
	}
}

public class Main {

	private static final String FILENAME = "cars_input1.txt";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		Vector<Car> carlist = new Vector<>();
		try (BufferedReader br1 = new BufferedReader(new FileReader(FILENAME))) {

			br1.readLine();// Skipping first line in the text file since headings are not part of output
							// data-set
			String strCurrentLine = null;

			while ((strCurrentLine = br1.readLine()) != null) {// Scan till EOF(End of the file)
				String[] carprop = strCurrentLine.split(",");
				carlist.add(new Car(carprop[0], carprop[1], Double.parseDouble(carprop[2])));
				/*
				 * Split the current line and adding the instance of constructor repeat the
				 * for-each line in the file
				 */
			}

		} catch (IOException e) { // if any exception occurs in read/write of file contents
			e.printStackTrace();
		}
		System.out.println("Enter N value ?");
		int N = Integer.parseInt(br.readLine());
		System.out.println("Enter Origin ?");
		String orgin = br.readLine();
		double orginsumhorsepow = 0.0, horsepowavg = 0.0;
		int count = 0, tempN = 0; // tempN is counter variable which breaks out of loop when tempN is equal toN
		for (Car item : carlist) {
			if (item.origin.equals(orgin)) {
				orginsumhorsepow += item.horsepower;
				count++;
			}
		}
		horsepowavg = (orginsumhorsepow / (double) count); // calculating the average
		System.out.println("\nOutput Dataset :\n");
		for (Car item : carlist) { // Iterating through Car instance
			if (tempN == N)
				break;
			if ((item.origin.equals(orgin) && item.horsepower > horsepowavg) && tempN != N - 1) {
				/*
				 * appending elements into StringBuilder as per given condition (current
				 * horsepower of car > average horse power )
				 */
				output.append(item.carname + "," + item.horsepower + "," + item.origin + "\n");
				tempN++;
			} else if ((item.origin.equals(orgin) && item.horsepower > horsepowavg) && tempN == N - 1) {
				// Last Element to be appended
				output.append(item.carname + "," + item.horsepower + "," + item.origin);
				tempN++;
			}
		}
		System.out.println(output.toString());// Converting StringBuilder back to String
	}
}
