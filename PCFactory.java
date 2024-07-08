package a2_2101040075;

/**
 * @overview PCFactory is a singleton class tasked with creating PC
 *           objects.
 * @author ttgiang
 */
public class PCFactory {
	// Singleton instance of the factory
	private static PCFactory factory = new PCFactory();

	/**
	 * @effects Constructs a new PCFactory object. It is made private
	 *          to ensure the singleton pattern.
	 */
	private PCFactory() {

	}

	/**
	 * @effects Returns the singleton instance of the PCFactory.
	 * @return the singleton instance of the PCFactory
	 */
	public static PCFactory getFactory() {
		return factory;
	}

	/**
	 * @effects Creates and returns a new PC object with the specified
	 *          attributes.
	 * @param model
	 *            - the model of the PC
	 * @param year
	 *            - the year of manufacture
	 * @param manufacturer
	 *            - the manufacturer of the PC
	 * @param comps
	 *            - a set of components for the PC
	 * @return a new PC object
	 */

	// change year's type to String to corresponding
	// with PC and user input
	// => new PC will convert to Integer
	public PC createPC(String model, String year,
			String manufacturer,
			Set<String> comps) {
		return new PC(model, year, manufacturer,
				comps);
	}
}