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

/**
 * This class makes public all of the private helper methods of the Grapher
 * class in order to test them individually with the HelperMethodsTest
 * class.
 * 
 * @author Thuy Nguyen
 *         CS 1410
 *         October 7, 2014
 *
 */
public class HelperMethods
{

	/**
	 * listElements method puts all of column1 through a HashSet in order to
	 * get a collection of distinct objects.
	 * 
	 * @param column1
	 *            a list of String objects
	 * @return a list of String objects that are unique in column1, no
	 *         duplicates
	 */
	public static List<String> getDistinctElements(List<String> column1)
	{
		if (column1.size() < 1)
		{
			throw new IllegalArgumentException(
					"Column 1 must contain at least one element");
		}

		Set<String> distinctStates = new HashSet<>(column1);
		return new ArrayList<String>(distinctStates);
	}

	/**
	 * prepareListData method makes a List<List<Integer>> and adds to it
	 * as many List<Integer> as the size dictates.
	 * 
	 * @param size
	 *            how many List<Integer> to add the List<List<Integer>>
	 * @return List<List<Integer>> containing as many lists as parameter size
	 *         dictates
	 */
	public static List<List<Integer>> prepareListData(int size)
	{
		List<List<Integer>> data = new ArrayList<List<Integer>>();
		for (int i = 0; i < size; i++)
		{
			data.add(new ArrayList<Integer>());
		}

		return data;
	}

	/**
	 * performOperation takes a list of integer and either sums, averages or
	 * finds the max
	 * of all the data
	 * 
	 * @param selection
	 * @param targetData
	 * @return
	 * 
	 */
	public static double performOperation(int selection,
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
	public static double sumData(List<Integer> targetData)
	{
		checkTargetData(targetData);

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
	public static double averageData(List<Integer> targetData)
	{
		checkTargetData(targetData);
		return sumData(targetData) / targetData.size();
	}

	/**
	 * findMax finds the maximum value in the list
	 * 
	 * @param targetData
	 *            list containing integer values
	 * @return the maximum value in the list
	 */
	public static double findMax(List<Integer> targetData)
	{
		checkTargetData(targetData);
		double max = Integer.MIN_VALUE;
		for (Integer num : targetData)
		{
			max = Math.max(num, max);
		}

		return max;
	}

	/**
	 * sumTheCategories method takes each category's data
	 * and sums up their operation results.
	 * 
	 * @param operation
	 *            0 -> sum, 1 -> average, 2 -> find max
	 * @param categories
	 *            the selected items we want to compare
	 * @param elementsIndex
	 *            the list containing the selected items
	 * @param data
	 *            a list containing lists of values, each list index is the
	 *            same as its representing item in the elementsIndex list.
	 * @return the summation of all the categories data, according to the
	 *         operation
	 */
	public static double sumTheCategories(int operation,
			List<String> categories, List<String> elementsIndex,
			List<List<Integer>> data)
	{
		checkExceptionsBeforeSumming(categories, elementsIndex, data);

		double categoriesSum = 0;
		for (String item : categories)
		{
			int index = elementsIndex.indexOf(item);
			categoriesSum += performOperation(operation, data.get(index));
		}

		return categoriesSum;
	}

	/**
	 * hasNonPositiveValue checks to see if there are any non-positive
	 * values in the list.
	 * 
	 * @param column2
	 *            the list to check for non-positive values
	 * @return true if the list has non-positive, false otherwise
	 */
	public static boolean hasNonPositiveValues(List<Integer> column2)
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
	 * checkValidCategoriesAndColors method ensures that each category has a
	 * color
	 * and that the categories are contained in the list.
	 * 
	 * @param categories
	 *            the items we want to represent in the pie chart or bar graph
	 * @param colors
	 *            the colors for each item in the pie chart or bar graph
	 * @param elementsIndex
	 *            the list of items containing the categories
	 */
	public static void checkValidCategoriesAndColors(List<String> categories,
			List<Color> colors, List<String> elementsIndex)
	{
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
	 * storeCorrespondingData method groups integers of column2 into their
	 * correct groups,
	 * corresponding to each distinct item of column1
	 * 
	 * The list of integers that result from grouping, are placed in the data
	 * list.
	 * The index of each group of integers in the data list is the same index of
	 * the corresponding
	 * item in the elements list
	 * 
	 * @param column1
	 *            the list of items
	 * @param column2
	 *            the data corresponding to each item in column1
	 * @param elementsIndex
	 *            the distinct items from column1
	 * @param data
	 *            contains the list of integers, each list corresponding to an
	 *            item in elements list
	 */
	public static void storeCorrespondingData(List<String> column1,
			List<Integer> column2, List<String> elementsIndex,
			List<List<Integer>> data)
	{

		if (column1.size() != column2.size())
		{
			throw new IllegalArgumentException(
					"Each element must have a corresponding value");
		}

		if (hasNonPositiveValues(column2))
		{
			throw new IllegalArgumentException(
					"Only positive values for the elements");
		}

		int next = 0;
		for (String item : column1)
		{
			int index = elementsIndex.indexOf(item);
			data.get(index).add(column2.get(next));
			next++;
		}
	}

	// private helper methods
	/**
	 * checkExceptionsBeforeSumming ensures that all the lists have
	 * at least one element, and that each element has a corresponding
	 * list of integer (its data) in the data list. Also, that there are no
	 * duplicates in the categories.
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
		if (categories.size() != new HashSet<String>(categories).size())
		{
			throw new IllegalArgumentException(
					"No duplicates in the category, only distinct items");
		}

		if (data.size() != elementsIndex.size())
		{
			throw new IllegalArgumentException(
					"Unique list of elements and list of corresponding data must be same size");
		}

		if (categories.size() < 1 || elementsIndex.size() < 1
				|| data.size() < 1)
		{
			throw new IllegalArgumentException(
					"All the lists passed in must have at least one element");
		}
	}

	private static void checkTargetData(List<Integer> targetData)
	{
		if (targetData.size() < 1)
		{
			throw new IllegalArgumentException(
					"There needs to be data in order to calculate the average");
		}
	}

}
