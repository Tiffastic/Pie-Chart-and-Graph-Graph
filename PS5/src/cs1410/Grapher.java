package cs1410;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grapher
{
	/**
	 * This class serves to display pie charts and bar graphs of the categories
	 * contained in column 1. Each category is represented with a pie wedge or
	 * rectangular bar, the size of which is based on their percentage -
	 * calculated from the sum of operation performed on all the categories.
	 * 
	 * The operation done on each category's data can either be a summation,
	 * an averaging, or finding the max value. The operational result of each
	 * category is compared to the total from all the categories. The resulting
	 * percentage is used to draw the correct size of the pie wedge or
	 * rectangular bar.
	 * 
	 * Thuy Nguyen
	 * CS 1410
	 * October 6, 2014
	 * 
	 */

	// public methods
	/**
	 * drawGraph method displays a pie chart or bar graph to represent the
	 * categories
	 * 
	 * @param g
	 *            the Graphics object with which to draw
	 * @param column1
	 *            the items
	 * @param column2
	 *            the data for each item in column1
	 * @param categories
	 *            the items that we want to represent in a pie chart or bar
	 *            graph
	 * @param colors
	 *            the colors for each category
	 * @param operation
	 *            how to compare the data in each category to all the
	 *            categories: sum, average, or find max
	 * @param usePieChart
	 *            indicates whether to represent data as a pie chart or bar
	 *            graph
	 * @param width
	 *            width of the JFrame
	 * @param height
	 *            height of the JFrame
	 */
	public static void drawGraph(Graphics g, ArrayList<String> column1,
			ArrayList<Integer> column2, ArrayList<String> categories,
			ArrayList<Color> colors, int operation, boolean usePieChart,
			int width, int height)
	{

		// make a list containing only the unique elements
		// group together each element's data and store that data into a list
		List<String> elementsIndex = getDistinctElements(column1);
		List<List<Integer>> data = prepareListData(elementsIndex.size());
		storeCorrespondingData(column1, column2, elementsIndex, data);

		// total up each operational result performed on each category
		double categoriesSum = sumTheCategories(operation, categories,
				elementsIndex, data);

		// prepare Graphics object
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw a pie chart or a bar graph according to selection
		if (usePieChart)
		{
			drawPieChart(g2, categories, colors, operation, width, height,
					elementsIndex, data, categoriesSum);
		}
		else
		{
			drawBarGraph(g2, categories, colors, operation, width, height,
					elementsIndex, data, categoriesSum);
		}

	}

	// private helper methods
	/**
	 * checkAllListsSize takes a vararg(unknown) parameter of lists
	 * and pass each list through the checkListSize method in order
	 * to ensure that each list contains at least one element.
	 * 
	 * @param lists
	 *            the array containing all the lists we want to check
	 */
	private static void checkAllListsSize(List<?>... lists)
	{
		for (List<?> list : lists)
		{
			checkListSize(list);
		}
	}

	/**
	 * checkListSize method checks the size of the list
	 * to ensure that it contains at least one item
	 * 
	 * * @param targetData
	 * a list of integers representing the data for an item in
	 * column 1
	 * 
	 */
	private static void checkListSize(List<?> targetList)
	{
		if (targetList.size() < 1)
		{
			throw new IllegalArgumentException("List(s) cannot be empty");
		}
	}

	/**
	 * getDistinctElements method puts all of column 1 through a HashSet in
	 * order to get a collection of distinct objects.
	 * 
	 * @param column1
	 *            a list of String objects
	 * @return a list of String objects that are unique in column1, no
	 *         duplicates
	 */
	private static List<String> getDistinctElements(List<String> column1)
	{
		Set<String> distinctStates = new HashSet<>(column1);
		return new ArrayList<String>(distinctStates);
	}

	/**
	 * prepareListData method makes a List<List<Integer>> and adds to it
	 * as many List<Integer> as dictated by the variable size.
	 * 
	 * @param size
	 *            how many List<Integer> to add the List<List<Integer>>
	 * @return List<List<Integer>> containing as many lists as parameter size
	 *         dictates
	 */
	private static List<List<Integer>> prepareListData(int size)
	{
		List<List<Integer>> data = new ArrayList<List<Integer>>();
		for (int i = 0; i < size; i++)
		{
			data.add(new ArrayList<Integer>());
		}

		return data;
	}

	/**
	 * hasNonPositiveValue checks to see if there are any non-positive
	 * values in the list.
	 * 
	 * @param column2
	 *            the list that's checked for non-positive values
	 * @return true if the list has non-positive, false otherwise
	 */
	private static boolean hasNonPositiveValues(List<Integer> column2)
	{
		for (Integer num : column2)
		{
			if (num <= 0)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * checkExceptionsBeforeStoringData method ensures that all the lists are of
	 * appropriate size, that column 1 and column 2 are of same size, that there
	 * are no negative values in column 2 and that each unique item in column 1
	 * has a corresponding group of data.
	 * 
	 * @param column1
	 *            contains string elements to represent data
	 * @param column2
	 *            contains a numerical value for each element in column 1
	 * @param elementsIndex contains only unique elements from column 1
	 * @param data contains the groups of integers that correspond to each
	 * unique element in elementsIndex
	 */
	private static void checkExceptionsBeforeStoringData(List<String> column1,
			List<Integer> column2, List<String> elementsIndex,
			List<List<Integer>> data)
	{
		checkAllListsSize(column1, column2, elementsIndex, data);

		if (column1.size() != column2.size())
		{
			throw new IllegalArgumentException(
					"Column 1 and column 2 must be same size");
		}

		if (elementsIndex.size() != data.size())
		{
			throw new IllegalArgumentException(
					"Each element must have a corresponding list of data");
		}

		if (hasNonPositiveValues(column2))
		{
			throw new IllegalArgumentException(
					"Only positive values for the elements");
		}
	}

	/**
	 * storeCorrespondingData method groups values of column2 into their
	 * correct groups, each group is a List<Integer>
	 * 
	 * The lists that result from grouping, are stored in the data
	 * list passed into the parameter.
	 * 
	 * The index of each group in the data list is the same index as
	 * their corresponding item in the elements list
	 * 
	 * @param column1
	 *            the list of items
	 * @param column2
	 *            the values corresponding to each item in column1
	 * @param elementsIndex
	 *            the distinct items from column1
	 * @param data
	 *            contains the list of integers, each list is a group of values
	 *            that
	 *            corresponds to its item in the elements list
	 */
	private static void storeCorrespondingData(List<String> column1,
			List<Integer> column2, List<String> elementsIndex,
			List<List<Integer>> data)
	{

		checkExceptionsBeforeStoringData(column1, column2, elementsIndex, data);

		int next = 0;
		for (String item : column1)
		{
			// get the index of where the item is in the list
			int index = elementsIndex.indexOf(item);

			// store the current value from column 2 into
			// the list at the specified index
			data.get(index).add(column2.get(next));

			// update variable for next value in column 2
			next++;
		}
	}

	/**
	 * performOperation takes a list of integers and either sums, averages or
	 * finds the max of all the data from that list.
	 * 
	 * @param selection
	 *            the operation to perform on the list: 0 = sum, 1 = average,
	 *            2 = find max
	 * @param targetData
	 *            the list that the operation will be performed on
	 * @return the sum, average or max of the data
	 * 
	 */
	private static double performOperation(int selection,
			List<Integer> targetData)
	{
		switch (selection)
		{
		case 0:
			return sumData(targetData);

		case 1:
			return averageData(targetData);

		case 2:
			return findMax(targetData);

		default:
			throw new IllegalArgumentException("Invalid operation");

		}
	}

	/**
	 * sumData sums up the integer values of the list
	 * 
	 * @param targetData
	 *            list containing integer values
	 * @return the sum of all the numbers in the list
	 */
	private static double sumData(List<Integer> targetData)
	{
		checkListSize(targetData);

		int sum = 0;
		for (Integer num : targetData)
		{
			sum += num;
		}

		return sum;
	}

	/**
	 * averageData calculates the average value of the list by summing
	 * all the values together and dividing the sum by the total items
	 * 
	 * @param targetData
	 *            list containing integer values
	 * @return the average value of the list
	 */
	private static double averageData(List<Integer> targetData)
	{
		checkListSize(targetData);
		return sumData(targetData) / targetData.size();
	}

	/**
	 * findMax finds the maximum value in the list
	 * 
	 * @param targetData
	 *            list containing integer values
	 * @return the maximum value in the list
	 */
	private static double findMax(List<Integer> targetData)
	{
		checkListSize(targetData);

		double max = Integer.MIN_VALUE;
		for (Integer num : targetData)
		{
			max = Math.max(num, max);
		}

		return max;
	}

	/**
	 * checkExceptionsBeforeSumming ensures that all the lists have
	 * an appropriate size, and that each element in column1 has a corresponding
	 * list of integers (its data). Also, that there are no duplicates in the
	 * categories.
	 * 
	 * @param categories
	 *            the items that we're interested in
	 * @param elementsIndex
	 *            the list containing the categories
	 * @param data
	 *            contains lists of integers, each list corresponding to
	 *            an item in the elementIndex.
	 */
	private static void checkExceptionsBeforeSumming(List<String> categories,
			List<String> elementsIndex, List<List<Integer>> data)
	{

		checkAllListsSize(categories, elementsIndex, data);

		if (categories.size() != new HashSet<String>(categories).size())
		{
			throw new IllegalArgumentException(
					"No duplicates in the category, only distinct items");
		}

		if (elementsIndex.size() != data.size())
		{
			throw new IllegalArgumentException(
					"Each element in column 1 must have a corresponding set of data");
		}

		if (!elementsIndex.containsAll(categories))
		{
			throw new IllegalArgumentException(
					"All of the categories must be in column 1");
		}

	}

	/**
	 * sumTheCategories method totals up the operational result of each category
	 * 
	 * @param operation
	 *            0 = sum, 1 = average, 2 = find max
	 * @param categories
	 *            the selected items we want to compare
	 * @param elementsIndex
	 *            the list containing the selected items
	 * @param data
	 *            a list containing lists of integers, each list index is the
	 *            same as its corresponding item in the elementsIndex list.
	 * @return the summation of all the categories data, according to the
	 *         operation
	 */
	private static double sumTheCategories(int operation,
			List<String> categories, List<String> elementsIndex,
			List<List<Integer>> data)
	{
		checkExceptionsBeforeSumming(categories, elementsIndex, data);

		double categoriesSum = 0;
		for (String item : categories)
		{
			// get the index of where the category is in the list
			int index = elementsIndex.indexOf(item);

			// get the data according to that index and do the selected
			// operation on it
			// add the result to the total
			categoriesSum += performOperation(operation, data.get(index));
		}

		return categoriesSum;
	}

	/**
	 * checkValidCategoriesAndColors method ensures that the categories are
	 * contained in the list and that each category has a color.
	 * 
	 * @param categories
	 *            the items we want to represent in the pie chart or bar graph
	 * @param colors
	 *            the colors for each item in the pie chart or bar graph
	 * @param elementsIndex
	 *            the list of items containing the categories
	 */
	private static void checkValidCategoriesAndColors(List<String> categories,
			List<Color> colors, List<String> elementsIndex)
	{
		checkAllListsSize(categories, colors, elementsIndex);

		if (!elementsIndex.containsAll(categories))
		{
			throw new IllegalArgumentException(
					"At least one of the categories is not in the list");
		}

		if (categories.size() != colors.size())
		{
			throw new IllegalArgumentException(
					"Category list and color list must be of same size");
		}

		if (categories.size() == 0 || colors.size() == 0)
		{
			throw new IllegalArgumentException(
					"There must be at least one category and color");
		}

	}

	/**
	 * drawBarGraph is a method that draws rectangles for each category passed
	 * in.
	 * 
	 * The size of each rectangle is based on the percentage of its
	 * corresponding category from the total of all the other categories whose
	 * data were operated on
	 * 
	 * The name of the category is listed right after its rectangle bar
	 * representation.
	 * 
	 * @param g
	 *            the graphics object with which to draw
	 * @param categories
	 *            the list of items we want to represent with a bar graph
	 * @param colors
	 *            the colors for each category
	 * @param operation
	 *            what we want to do with the data: sum, average, or find max
	 * @param width
	 *            the width of the JFrame
	 * @param height
	 *            the height of the JFrame
	 * @param elementsIndex
	 *            the list of distinct items that the category list can contain
	 * @param data
	 *            contains lists of integers, where each index of the data
	 *            corresponds to a list for a specific category
	 * @param categoriesSum
	 *            the resulting sum of the operation performed on each list in
	 *            data
	 */
	private static void drawBarGraph(Graphics2D g,
			ArrayList<String> categories, ArrayList<Color> colors,
			int operation, int width, int height, List<String> elementsIndex,
			List<List<Integer>> data, double categoriesSum)
	{

		checkValidCategoriesAndColors(categories, colors, elementsIndex);

		// the height and width of the bars and where to draw them
		int barWidth = (int) (width * .60);
		int barHeight = (int) (barWidth * 25.0 / 300);
		int xCoord = (int) (width * .20);
		int yCoord = (int) (.165 * height);
		int gap = 7;
		int next = 0;
		Font font = new Font("Times New Roman", Font.BOLD, 25);

		// This for loop iterates through each category and gets their
		// percentage. The percentage determines the width of the
		// category's bar in the graph.

		for (String item : categories)
		{
			// get index of the category
			int index = elementsIndex.indexOf(item);

			// get the data of the category according to its index
			// process the data based on operation selection
			// calculate the percentage of the category
			double fraction = performOperation(operation, data.get(index))
					/ categoriesSum;

			// draw category's bar according to its percentage and color
			double barSize = Math.round(barWidth * fraction);
			g.setColor(colors.get(next));
			g.fillRect(xCoord, yCoord, (int) barSize, barHeight);

			// draw the string of the category's name
			g.setFont(font);
			g.drawString(item, xCoord + (int) barSize + gap * 2, yCoord
					+ barHeight / 2 + gap);

			// update variables for next category
			next++;
			yCoord += barHeight;
		}
	}

	/**
	 * drawPieChart method creates a pie chart containing each category of the
	 * list passed in.
	 * 
	 * Each wedge of the pie chart corresponds to each category's fraction out
	 * of all the other categories according to the operation formed on the
	 * categories data.
	 * 
	 * @param g
	 *            the Graphics object with which to draw
	 * @param categories
	 *            the list of categories that the pie chart will contain
	 * @param colors
	 *            the colors of each category
	 * @param operation
	 *            what to do to the data: sum, average or find max
	 * @param width
	 *            the width of the JFrame
	 * @param height
	 *            the height of the JFrame
	 * @param elementsIndex
	 *            the list of distinct items that the category list can contain
	 * @param data
	 *            contains lists of integers, where each index of the data
	 *            corresponds to a list for a specific category
	 * @param categoriesSum
	 *            the resulting sum of the operation performed on each list in
	 *            data
	 */
	private static void drawPieChart(Graphics2D g,
			ArrayList<String> categories, ArrayList<Color> colors,
			int operation, int width, int height, List<String> elementsIndex,
			List<List<Integer>> data, double categoriesSum)
	{

		checkValidCategoriesAndColors(categories, colors, elementsIndex);

		// size of the pie chart and where to draw it
		int pieSize = 500;
		int xCoord = width / 2 - pieSize / 2;
		int yCoord = height / 2 - pieSize / 2;
		final int circleDegree = 360;
		double startDegree = 0;

		// for loop determines how big the pie wedge is for each category
		int next = 0;
		double circleSum = 0;
		for (String item : categories)
		{
			// get index of where category is in the list
			int index = elementsIndex.indexOf(item);

			// get category's data at specified index and process that data with
			// selected operation. Calculate the percentage of the category.
			double fraction = performOperation(operation, data.get(index))
					/ categoriesSum;

			// get the wedge size of the category based on its percentage
			double wedgeSize = Math.round(circleDegree * fraction);
			circleSum += wedgeSize;

			// ensure that the last wedge fills up the circle
			if (next == categories.size() - 1 && circleSum < 360)
			{
				double diff = Math.ceil(360 - circleSum);
				wedgeSize += diff;
			}

			// draw pie wedge
			g.setColor(colors.get(next));
			g.fillArc(xCoord, yCoord, pieSize, pieSize, (int) startDegree,
					(int) wedgeSize);

			// adjust start degree to where last pie wedge ended
			startDegree += wedgeSize;
			next++;
		}

		// annotate the category of each pie wedge
		drawLegend(g, categories, colors, pieSize, xCoord, yCoord);
	}

	/**
	 * drawLegend method draws a legend for the pie chart
	 * to let users see which color of the pie chart corresponds to what
	 * category
	 * 
	 * @param g
	 *            the Graphics object o draw with
	 * @param categories
	 *            the categories that we want to represent in the legend
	 * @param colors
	 *            the colors that correspond to each category
	 * @param pieSize
	 *            the circle size of the pie chart
	 * @param xCoord
	 *            start x coordinate of legend
	 * @param yCoord
	 *            start y coordinate of legend
	 */
	private static void drawLegend(Graphics2D g, ArrayList<String> categories,
			ArrayList<Color> colors, int pieSize, int xCoord, int yCoord)
	{
		// the size of the legend and where to draw it
		int legendGap = 20;
		int square = (int) (pieSize * .10);
		int legendX = xCoord + pieSize + 55;
		int legendY = yCoord + legendGap + 10;
		Font font = new Font("Times New Roman", Font.BOLD, 25);
		g.setFont(font);

		// draw a square with the category's color and then draw a string of the
		// category name
		for (int i = 0; i < categories.size(); i++)
		{
			g.setColor(colors.get(i));
			g.fillRect(legendX, legendY, square, square);
			g.drawString(categories.get(i), legendX + square + legendGap,
					legendY + (int) (square * .65));
			legendY += (int) square + legendGap;
		}
	}
}
