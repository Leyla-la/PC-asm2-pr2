package a2_2101040075;

import java.time.Year;
import utils.NotPossibleException;

/**
 * @author ttgiang
 * @overview This class defines a PC obj with key attributes: model,
 *           year, manufacturer, components.
 * @attributes model         String PC's model. 
 * 			   year          Integer PC's manufacture year. 
 * 			   manufacturer  String PC's manufacturer. 
 * 			   components    Set<String> PC's components set.
 * @object A standard PC entity including a model, manufacturing year,
 *         manufacturer, and components.
 * @abstract_properties 
 * mutable(model) = true /\ optional(model) = false /\ length(model) = 20 
 * mutable(year) = false /\ optional(year) = false /\ min(year) = 1984 
 * mutable(manufacturer) = false /\ optional(manufacturer) = false /\ length(manufacturer) = 15 
 * mutable(components)= true /\ optional(components) = false /\ type(components) = Set<String>
 */
public class PC {
	private String model;
	private Integer year;
	private String manufacturer;
	private Set<String> comps;

	// change year to String for catch MisMatchInputException
	// more straightforward, ex when user input String which cannot
	// convert to Integer
	public PC(String model, String year,
			String manufacturer,
			Set<String> comps)
			throws NotPossibleException {
		if (!isModelValid(model)) {
			throw new NotPossibleException(
					"invalid_model");
		}
		if (!isYearValid(year)) {
			throw new NotPossibleException(
					"invalid_year");
		}
		if (!isManufacturerValid(manufacturer)) {
			throw new NotPossibleException(
					"invalid_manufacturer");
		}
		
		if (!isCompsValid(comps)) {
			throw new NotPossibleException(
					"invalid_components");
		}

		this.model = model;
		this.year = Integer.parseInt(year);
		this.manufacturer = manufacturer;
		this.comps = comps;
	}

	public String getModel() {
		return model;
	}

	public boolean setModdel(String model) {
		if (!isModelValid(model)) {
			return false;
		}
		this.model = model;
		return true; 
	}

	public Integer getYear() {
		return year;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public Set<String> getComps() {
		return comps;
	}

	public boolean setComps(Set<String> comps) {
		if(!isCompsValid(comps)) {
			return false;
		}
		this.comps = comps;
		return true;
	}

	//validate Model
	public static boolean isModelValid(
			String model) {
		return model != null && !model.isEmpty()
				&& model.length() <= 20;
	}

	//validate Manufacturer
	public static boolean isManufacturerValid(
			String manufacturer) {
		return manufacturer != null
				&& !manufacturer.isEmpty()
				&& manufacturer.length() <= 15;
	}

	//validate Year
	public static boolean isYearValid(
			String year_) {
		try {
			Integer year = Integer.parseInt(year_);
		//
			return year >= 1984 && year <= Year
					.now().getValue();
		// catch e when user input String that cannot
		// convert to Integer
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	//validate Comps
	public static boolean isCompsValid(Set<String> Comps) {
	    return Comps != null && !Comps.isEmpty();
	}


	@Override
	public String toString() {
		return "PC<" + model + ", " + year + ", "
				+ manufacturer + ", Set {" + comps
				+ "}>";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PC))
			return false;
		PC pc = (PC) o;
		return model.equals(pc.model)
				&& (year == null
						? pc.year == null
						: year.equals(pc.year))
				&& manufacturer
						.equals(pc.manufacturer)
				&& (comps == null
						? pc.comps == null
						: comps.equals(pc.comps));
	}

	// Override hashCode() for consistency in hash-based collections.
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (model != null
				? model.hashCode()
				: 0);
		result = 31 * result + (year != null
				? year.hashCode()
				: 0);
		result = 31 * result
				+ (manufacturer != null
						? manufacturer.hashCode()
						: 0);
		result = 31 * result + (comps != null
				? comps.hashCode()
				: 0);
		return result;
	}

}