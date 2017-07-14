package cs1410;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import graphics.GraphWindow;

public class Main
{
	/**
	 * This main class provides sample data to the Grapher class
	 */
	public static void main(String[] args)
	{
		String[] column1 = { "Utah", "Nevada", "California", "Oregon", "Utah",
				"California", "Nevada", "Nevada", "Oregon", "California" };
		Integer[] column2 = { 5, 6, 12, 8, 9, 10, 4, 4, 17, 6 };
		String[] categories = { "Utah", "Nevada", "California" };
		Color[] colors = { Color.red, Color.blue, Color.green };
		new GraphWindow(new ArrayList<String>(Arrays.asList(column1)),
				new ArrayList<Integer>(Arrays.asList(column2)),
				new ArrayList<String>(Arrays.asList(categories)),
				new ArrayList<Color>(Arrays.asList(colors)), 0, false);
		
		new GraphWindow(new ArrayList<String>(Arrays.asList(column1)),
				new ArrayList<Integer>(Arrays.asList(column2)),
				new ArrayList<String>(Arrays.asList(categories)),
				new ArrayList<Color>(Arrays.asList(colors)), 0, true);
	}
}
