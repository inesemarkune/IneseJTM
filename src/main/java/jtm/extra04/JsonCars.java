package jtm.extra04;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;

public class JsonCars {

	/*- TODO #1
	 * Implement method, which returns list of cars from generated JSON string
	 */
	// json String
	public List<Car> getCars(String jsonString) {
		/*- HINTS:
		 * You will need to use:
		 * - https://stleary.github.io/JSON-java/org/json/JSONObject.html
		 * - https://stleary.github.io/JSON-java/org/json/JSONArray.html
		 * You will need to initialize JSON array from "cars" key in JSON string
		 */


		List<Car> result = new ArrayList<Car>();
		JSONObject jo = new JSONObject(jsonString);// json object created
		JSONArray ja = jo.getJSONArray("cars");// json array created that uses json object"jo"
		Iterator<Object> iterator = ja.iterator(); // traverses the JSONArray.
		while (iterator.hasNext()) { // while iterator has next value,
			Object next = iterator.next(); // a new object is created
			if (next instanceof JSONObject) {
				JSONObject jObject = (JSONObject) next; // parsing/converting "next" to jsonobject

				// from json object reading key-by-key all attributes and
				// use constructor "car" and pass attributes in brackets
				Car car = new Car((String) jObject.get("model"), (Integer) jObject.get("year"),
						(String) jObject.get("color"), ((Integer) jObject.get("price")).floatValue());
				result.add(car);
			}
		}

		return result;
	}

	/*- TODO #2
	 * Implement method, which returns JSON String generated from list of cars
	 */
	public String getJson(List<Car> cars) {
		/*- HINTS:
		 * You will need to use:
		 * - https://docs.oracle.com/javase/8/docs/api/index.html?java/io/StringWriter.html
		 * - http://static.javadoc.io/org.json/json/20180130/index.html?org/json/JSONWriter.html
		 * Remember to add "car" key as a single container for array of car objects in it.
		 */
		StringWriter stringWriter = new StringWriter(); // using stringwriter from java.io
		JSONWriter writer = new JSONWriter(stringWriter); // initiate jsonWriter

		try {
			writer.object().key("cars").array();// with a writer we create a new object and populate it with
			// key "cars"(array of objects cars) and open an array. (will look like
			// {"cars":[ ... ])
			for (Car car : cars) { // (for each car in cars )
				writer.object().key("model").value(car.getModel()); // output: ( {"cars":[{"model":"Bentley"...)
				writer.key("year").value(car.getYear());
				writer.key("color").value(car.getColor());
				writer.key("price").value(car.getPrice());
				writer.endObject(); // will end the first object - ({"cars":[{"model":"Bentley",
									// "year":1997,"color":"Magenta","price":10900})
			}
			writer.endArray();// writer closes the array with square bracket: (..."price":10900}] )
		} catch (JSONException e) {
		}
		writer.endObject(); // ends creating entire JSON (..."price":10900}]} )
		return stringWriter.toString(); // getting string
	}

}