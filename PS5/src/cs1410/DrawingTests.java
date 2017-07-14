package cs1410;

import graphics.GraphWindow;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * DrawingTests is a class that "hands tests" the display of the pie
 * chart and bar graph using the GraphWindow's static method.
 * 
 * @author Thuy Nguyen
 *         CS 1410
 *         October 7, 2014
 *
 */

public class DrawingTests
{
	private static ArrayList<String> column1 = new ArrayList<>(Arrays.asList(
			"Red", "Yellow", "Green", "Blue", "Magenta", "Black", "Red",
			"Blue", "Black"));
	private static ArrayList<Integer> column2 = new ArrayList<>(Arrays.asList(
			10, 43, 31, 5, 27, 5, 100, 5, 5));

	public static void main(String[] args)
	{
		/**
		 * Tests for bar graph,
		 */
		 testOneCategoryBarGraph();
		 testBarGraphSum();
		 testBarGraphAverage();
		 testBarGraphFindMax();
		 testHalfAndHalfBarGraph();
		 testQuartersBarGraph();
		 testEighthBarGraph();

		/**
		 * Tests for pie chart
		 */
		 testOneCategoryPieChart();
		 testPieChartSum1();
		 testPieChartSum2();
		 testPieChartAverage();
		 testPieChartFindMax();
		 testHalfAndHalfPieChart();
		 testQuartersPieChart();
		testEighthPieChart();
		
		
		/**
		 * Please uncomment tests for exceptions to run
		 * 
		 * 
		 * Tests for exceptions
		 */
		// testEmptyListsException();
		// testNotEnoughColorsException();
		// testTooManyColorsException();
		// testNotEnoughValuesException();
		// testNotEnoughElementsException();
		// testUndefinedCategoryException();
		// testNegativeValuesException();
		// testUndefinedOperationException();

	}

	// Tests for bar graph
	/**
	 * A non-exceptional test case for "by-hand" testing
	 */
	public static void testOneCategoryBarGraph( )
	{
		ArrayList<String> col1 = new ArrayList<String>();
		col1.add("Utah");
		ArrayList<Integer> col2 = new ArrayList<Integer>();
		col2.add(50);
		ArrayList<String> cats = new ArrayList<String>();
		cats.add("Utah");
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		new GraphWindow(col1, col2, cats, colors, 0, false);
	}

	/**
	 * Test bar graph after sum operation
	 */
	public static void testBarGraphSum( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		// Red - 110, Yellow - 43, Green - 31, 10 - blue, 27 - magenta, 24 -
		// black
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.black));
		int sum = 0;
		new GraphWindow(column1, column2, categories, colors, sum, false);
	}

	/**
	 * Test bar graph after average operation
	 */
	public static void testBarGraphAverage( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		// 55 - red, 43 - yellow, 31 - green, 5 - blue, 27 - magenta, 12 - black
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.black));
		int average = 1;
		new GraphWindow(column1, column2, categories, colors, average, false);
	}

	/**
	 * Test bar chart after find max operation
	 */
	public static void testBarGraphFindMax( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		// 100 - red, 43 - yellow, 31 - green, 5 - blue, 27 - magenta, 12 -
		// black
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.black));
		int findMax = 2;
		new GraphWindow(column1, column2, categories, colors, findMax, false);
	}

	/**
	 * Test bar graph for half and half
	 */
	public static void testHalfAndHalfBarGraph( )
	{
		ArrayList<String> col1 = new ArrayList<>(Arrays.asList("Girl", "Boy"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(50, 50));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Girl",
				"Boy"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.pink,
				Color.blue));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, false);
	}

	/**
	 * Test bar graph for quarters
	 */
	public static void testQuartersBarGraph( )
	{
		ArrayList<String> col1 = new ArrayList<>(Arrays.asList("Spring",
				"Summer", "Fall", "Winter"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(50, 50, 50, 50));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Spring",
				"Summer", "Fall", "Winter"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.green,
				Color.red, Color.orange, Color.cyan));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, false);
	}

	/**
	 * Test bar graph for eight even bars
	 */
	public static void testEighthBarGraph( )
	{
		ArrayList<String> col1 = new ArrayList<>(
				Arrays.asList("Ten", "Jack", "Queen", "King", "Ace", "Hearts",
						"Diamonds", "Clubs", "Spades"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(50, 50, 50, 50,
				50, 50, 50, 50, 50));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Ten",
				"Jack", "Queen", "King", "Ace", "Hearts", "Diamonds", "Clubs",
				"Spades"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.green,
				Color.pink, Color.orange, Color.cyan, Color.blue, Color.red,
				Color.magenta, Color.black, Color.gray));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, false);
	}

	// Tests for pie chart
	/**
	 * Test when there's only one category
	 */
	public static void testOneCategoryPieChart( )
	{
		ArrayList<String> col1 = new ArrayList<String>();
		col1.add("Utah");
		ArrayList<Integer> col2 = new ArrayList<Integer>();
		col2.add(50);
		ArrayList<String> cats = new ArrayList<String>();
		cats.add("Utah");
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		new GraphWindow(col1, col2, cats, colors, 0, true);
	}

	/**
	 * Test the display of the pie chart from sum operation
	 */
	public static void testPieChartSum1( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.black));
		int sum = 0;
		new GraphWindow(column1, column2, categories, colors, sum, true);
	}

	/**
	 * Another test of the pie chart after sum operation
	 */
	public static void testPieChartSum2( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green));
		int sum = 0;
		new GraphWindow(column1, column2, categories, colors, sum, true);
	}

	/**
	 * Test the display of the pie chart from average operation
	 */
	public static void testPieChartAverage( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green));
		int average = 1;
		new GraphWindow(column1, column2, categories, colors, average, true);
	}

	/**
	 * Test the display of the pie chart from find max operation
	 */
	public static void testPieChartFindMax( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green));
		int findMax = 2;
		new GraphWindow(column1, column2, categories, colors, findMax, true);
	}

	/**
	 * Test pie chart for half and half
	 */
	public static void testHalfAndHalfPieChart( )
	{
		ArrayList<String> col1 = new ArrayList<>(Arrays.asList("Girl", "Boy"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(50, 50));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Girl",
				"Boy"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.pink,
				Color.blue));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, true);
	}

	/**
	 * Test pie chart for quarters
	 */
	public static void testQuartersPieChart( )
	{
		ArrayList<String> col1 = new ArrayList<>(Arrays.asList("Spring",
				"Summer", "Fall", "Winter", "Spring", "Summer", "Fall",
				"Winter"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(25, 25, 25, 25,
				25, 25, 25, 25));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Spring",
				"Summer", "Fall", "Winter"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.green,
				Color.red, Color.orange, Color.cyan));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, true);
	}

	/**
	 * Test pie chart for eights even wedges
	 */
	public static void testEighthPieChart( )
	{
		ArrayList<String> col1 = new ArrayList<>(
				Arrays.asList("Ten", "Jack", "Queen", "King", "Ace", "Hearts",
						"Diamonds", "Clubs", "Spades"));
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(50, 50, 50, 50,
				50, 50, 50, 50, 50));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Ten",
				"Jack", "Queen", "King", "Ace", "Hearts", "Diamonds", "Clubs",
				"Spades"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.green,
				Color.pink, Color.orange, Color.cyan, Color.blue, Color.red,
				Color.magenta, Color.black, Color.gray));
		int findMax = 2;
		new GraphWindow(col1, col2, categories, colors, findMax, true);
	}

	// Tests for exceptions
	/**
	 * Test exception when there the lists are empty
	 */
	public static void testEmptyListsException( )
	{
		new GraphWindow(new ArrayList<String>(), new ArrayList<Integer>(),
				new ArrayList<String>(), new ArrayList<Color>(), 0, true);
	}

	/**
	 * Test exception when there are more colors than categories
	 */
	public static void testTooManyColorsException( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow, Color.green, Color.blue, Color.magenta,
				Color.black, Color.red));
		int sum = 0;
		new GraphWindow(column1, column2, categories, colors, sum, true);
	}

	/**
	 * Test exception when there are more categories than colors
	 */
	public static void testNotEnoughColorsException( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red",
				"Yellow", "Green", "Blue", "Magenta", "Black"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red,
				Color.yellow));
		int sum = 0;
		new GraphWindow(column1, column2, categories, colors, sum, true);
	}

	/**
	 * Test exception when column 2 doesn't have enough values
	 * for all the elements in column 1
	 */
	public static void testNotEnoughValuesException( )
	{
		ArrayList<String> col1 = new ArrayList<>(Arrays.asList("Utah"));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red));

		new GraphWindow(col1, column2, categories, colors, 0, true);
	}

	/**
	 * Test exception when there are more items in column 1 than values in
	 * column 2
	 */
	public static void testNotEnoughElementsException( )
	{
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5,
				6, 7, 8, 9, 10, 11, 12, 13, 14));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red));

		new GraphWindow(column1, col2, categories, colors, 0, true);
	}

	/**
	 * Test exception when category items are not in column 1
	 */
	public static void testUndefinedCategoryException( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Purple"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red));

		new GraphWindow(column1, column2, categories, colors, 0, true);
	}

	/**
	 * Test exception when column 2 has negative values
	 */
	public static void testNegativeValuesException( )
	{
		ArrayList<Integer> col2 = new ArrayList<>(Arrays.asList(-10, 43, 31, 5,
				27, 12, 100, 5, 12));
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Red"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.red));

		new GraphWindow(column1, col2, categories, colors, 0, true);
	}

	/**
	 * Test exception when there's an undefined operation
	 */
	public static void testUndefinedOperationException( )
	{
		ArrayList<String> categories = new ArrayList<>(Arrays.asList("Black"));
		ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.black));

		new GraphWindow(column1, column2, categories, colors, 4, true);
	}

}
