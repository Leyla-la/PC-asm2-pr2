package a2_2101040075;

/**
 * @overview PCReport generates written reports of PC objects and
 *           offers a function to present them in a table format.
 * 
 * @object A typical PCReport is {objs} where objs is an array of PCs.
 * 
 * @abstract_properties mutable(objs)=false /\ optional(objs)=true
 * 
 * @author ttgiang
 */
public class PCReport {

	// Define the width of columns
	private static final int ID_WIDTH = 3;
	private static final int MODEL_WIDTH = 20;
	private static final int YEAR_WIDTH = 6;
	private static final int MFTURER_WIDTH = 15;
	private static final int REPORT_WIDTH = 99;
	private static final int COMPS_WIDTH = REPORT_WIDTH - ID_WIDTH
			- MODEL_WIDTH - YEAR_WIDTH - MFTURER_WIDTH - 6;
	// 6 is for spaces and brackets

	// Method to display the report
	public String displayReport(PC[] objs) {
		StringBuilder report = new StringBuilder();

		// Build the table header
		report.append(dashLine(99));
		report.append(
				String.format("%" + ((REPORT_WIDTH - 13) / 2) + "s",
						"PCPROG REPORT"))
				.append("\n");
		report.append(dashLine(99));

		for (int i = 0; i < objs.length; i++) {
			PC pc = objs[i];

			// %Nums => right adjustify
			// %-Nums => left adjustify
			String index = String.format("%" + ID_WIDTH + "s",
					String.valueOf(i + 1));
			String model = String.format("%" + MODEL_WIDTH + "s",
					pc.getModel());
			String year = String.format("%" + YEAR_WIDTH + "s",
					String.valueOf(pc.getYear()));
			String manufacturer = String.format(
					"%" + MFTURER_WIDTH + "s", pc.getManufacturer());
			String comps = String.format("%-" + (COMPS_WIDTH) + "s",
					formatComps(pc.getComps()));

			// Add the data row to the report
			report.append(String.format("%s %s %s %s %s%n", index,
					model, year, manufacturer, comps));
		}
		report.append(dashLine(99));

		return report.toString();
	}

	// Method to truncate and format components
	private static String formatComps(Set<String> components) {
		StringBuilder displayComps = new StringBuilder("[");
		int maxLength = COMPS_WIDTH;

		for (String component : components) {
			String truncCmps = component;
			if (truncCmps.length() > maxLength - 3) {
			    int availableLength = Math.max(maxLength - 3, 0); // Ensure available length is non-negative
			    truncCmps = truncCmps.substring(0, availableLength) + "...";
			}
			
			displayComps.append(truncCmps).append(", ");
			maxLength -= truncCmps.length() + 2; // Account for the
													// component
													// length and ", "
													// separator
			if (maxLength <= 0) {
				break;
			}
		}

		if (components.size() > 0) {
			displayComps.setLength(displayComps.length() - 2); // Remove
																// the
																// last
																// ",
																// "
		}

		displayComps.append("]");
		return displayComps.toString();
	}
	// => to build sample: [A, B, C] or [A, B, C, D, E, F,...]

	// Method to generate hyphen line
	private static String dashLine(int reportWidth) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < reportWidth; i++) {
			line.append("-");
		}
		line.append("\n");
		return line.toString();
	}

}
