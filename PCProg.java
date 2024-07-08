package a2_2101040075;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import utils.NotPossibleException;

/**
 * A program that captures data about PC objects and displays a report
 * about them on the console.
 * @author ttgiang
 */

public class PCProg {
	private Set<PC> objs;

	/**
	 * Initialise this to have an empty set of PCs
	 */
	public PCProg() {
		objs = new Set<>();
	}

	public void createObjects() {
		PCFactory factory = PCFactory
				.getFactory();
		Scanner scanner = new Scanner(System.in);
		System.out.println(
				"\u001B[1;32mWelcome to the PC creating part <3");

		while (true) {
			// ask user for input information
			System.out.println(
					// color console -> green
					"\u001B[1;32mEnter PC data:");
			System.out.print("Model: ");
			String model = scanner.nextLine()
					.trim();
			System.out.print("Year: ");
			String year = scanner.nextLine()
					.trim();
			System.out.print("Manufacturer: ");
			String manufacturer = scanner
					.nextLine().trim();
			Set<String> components = new Set<>();
			while (true) {
				System.out.print(
						"Enter component (press Enter to finish adding components): ");
				String component = scanner
						.nextLine().trim();
				// if user press Enter => break
				if (component.isEmpty()) {
					break;
				}
				components.insert(component);
			}
			do {
				try {
					PC newPC = factory.createPC(
							model, year,
							manufacturer,
							components);
					objs.insert(newPC);
					System.out.println(
							"\u001B[1;35m=> PC created successfully.");
					break;
				} catch (NotPossibleException e) {
					switch (e.getMessage()) {
						case "invalid_model" :
							System.out.println(
									"\u001B[91mError: "
											+ e.getMessage());
							System.out.print(
									"\u001B[1;32mRe-enter Model: ");
							model = scanner
									.nextLine()
									.trim();
							break;
						case "invalid_year" :
							System.out.println(
									"\u001B[91mError: "
											+ e.getMessage());
							System.out.print(
									"\u001B[1;32mRe-enter Year: ");
							year = scanner
									.nextLine()
									.trim();
							break;
						case "invalid_manufacturer" :
							System.out.println(
									"\u001B[91mError: "
											+ e.getMessage());
							System.out.print(
									"\u001B[1;32mRe-enter Manufacturer: ");
							manufacturer = scanner
									.nextLine()
									.trim();
							break;
						case "invalid_components" :
							System.out.println(
									"\u001B[91mError: "
											+ e.getMessage());
							System.out.println(
									"\u001B[1;32mRe-enter Components:");
							while (true) {
								System.out.print(
										"Enter component (press Enter to finish adding components): ");
								String component = scanner
										.nextLine()
										.trim();
								if (component
										.isEmpty()) {
									break;
								}
								components.insert(
										component);
							}
							break;
					}
				}
			} while (true);

			// Ask user whether they want to add another PC
			System.out.print(
					"\u001B[1;32mDo you want to add another PC? (Y/N): ");
			String continueInput = scanner
					.nextLine().trim();
			if (!continueInput
					.equalsIgnoreCase("Y")) {
				break;
			}
		}
	}

	/**
	 * @overview this method return the recorded PC objects.
	 */
	public PC[] getObjects() {
		return objs != null
				? objs.getElements()
						.toArray(new PC[0])
				: null;
	}

	/**
	 * If <tt>objs</tt> is not empty, displays a text-based tabular
	 * report on <tt>objs</tt> to the standard console. Displays
	 * nothing if <tt>objs</tt> is empty.
	 *
	 * @return this report if <tt>objs</tt> is not empty or
	 *         <tt>null</tt> otherwise.
	 */
	public String displayReport() {
		if (!objs.isEmpty()) {
			Vector<PC> pcs = objs.getElements();
			PCReport reportObj = new PCReport();
			return reportObj.displayReport(
					pcs.toArray(new PC[0]));
		} else {
			return null;
		}
	}

	/**
	 * Saves report to a file <tt>pcs.txt</tt> in the program's
	 * working directory.
	 */
	public void saveReport(String report) {
		String fileName = "pcs.txt";
		try (PrintWriter pw = new PrintWriter(
				fileName)) {
			pw.println(report);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Initializes an instance of <tt>PCProg</tt>. Create objects from
	 * data entered by the user. Display a report on the objects.
	 * Prompt user to save report to file. If user answers "Y", save
	 * report. Otherwise, end program.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PCProg prog = new PCProg();
		try {
			// create objects
			prog.createObjects();
			// display report
			String report = prog.displayReport();
			System.out.println(report);
			if (report != null) {
				// prompt user to save report
				System.out.println(
						"Save report to file? [Y/N]");
				String toSave = sc.nextLine();
				if (toSave.equals("Y")) {
					prog.saveReport(report);
					System.out.println(
							"report saved");
				}
			}
		} catch (NotPossibleException e) {
			System.err.printf("%s: %s%n", e,
					e.getMessage());
		}
		sc.close();
		System.out.println("~END~");
	}
}
