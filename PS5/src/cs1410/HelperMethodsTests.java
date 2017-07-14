package cs1410;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This class serves to test all of the helper methods of the Grapher class
 * which is made public in the HelperMethods class for the purpose of testing
 * 
 * @author Thuy Nguyen
 * CS 1410
 * October 7, 2014
 *
 */

public class HelperMethodsTests
{

	private static List<String> states, categories, elements;
	private static List<Integer> data;
	private static List<List<Integer>> statesData;

	@BeforeClass
	public static void setUp( ) throws Exception
	{
		states = new ArrayList<>(Arrays.asList("Utah", "Nevada", "California",
				"Oregon", "Utah", "California", "Nevada", "Nevada", "Oregon",
				"California"));
		elements = new ArrayList<>(Arrays.asList("Utah", "Nevada",
				"California", "Oregon"));
		categories = new ArrayList<>(Arrays.asList("Utah", "Nevada",
				"California"));
		statesData = new ArrayList<List<Integer>>()
		{
			{
				add(new ArrayList<Integer>(Arrays.asList(5, 9)));
				add(new ArrayList<Integer>(Arrays.asList(6, 4, 4)));
				add(new ArrayList<Integer>(Arrays.asList(12, 10, 6)));
				add(new ArrayList<Integer>(Arrays.asList(8, 17)));
			}
		};

		data = Arrays.asList(20, 31, 4, 2, 83, 4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testColumn1Exception( )
	{
		HelperMethods.getDistinctElements(new ArrayList<String>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void performOperationException( )
	{
		HelperMethods.performOperation(4, data);
	}

	@Test(expected = IllegalArgumentException.class)
	public void sumDataException( )
	{
		HelperMethods.sumData(new ArrayList<Integer>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void averageDataException( )
	{
		HelperMethods.averageData(new ArrayList<Integer>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void findMaxException( )
	{
		HelperMethods.findMax(new ArrayList<Integer>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void categoriesDuplicatesException( )
	{
		List<String> categ = Arrays.asList("Utah", "California", "Utah");
		HelperMethods.sumTheCategories(0, categ, elements, statesData);

	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidCategoriesException( )
	{
		List<Color> colors = Arrays.asList(Color.red, Color.blue, Color.yellow);
		List<String> invalidCategories = Arrays.asList("Thuy", "Utah",
				"California");
		HelperMethods.checkValidCategoriesAndColors(invalidCategories, colors,
				elements);
	}

	@Test(expected = IllegalArgumentException.class)
	public void notEnoughColorsException( )
	{
		List<Color> colors = Arrays.asList(Color.red);
		HelperMethods.checkValidCategoriesAndColors(categories, colors,
				elements);

	}

	@Test(expected = IllegalArgumentException.class)
	public void categoriesAndColorsSizeZeroException( )
	{
		List<Color> colors = new ArrayList<>();
		List<String> categ = new ArrayList<>();
		HelperMethods.checkValidCategoriesAndColors(categ, colors, elements);
	}

	@Test(expected = IllegalArgumentException.class)
	public void column1Column2NotSameSize( )
	{
		List<Integer> values = Arrays.asList(4, 4, 3);
		HelperMethods.storeCorrespondingData(states, values, elements,
				statesData);
	}

	@Test
	public void hasNonPositiveValuesException( )
	{
		List<Integer> negativesAndZero = Arrays.asList(0, -1, 2, 3, 4);
		assertTrue(HelperMethods.hasNonPositiveValues(negativesAndZero));
	}

	@Test
	public void testGetDistinctElements( )
	{
		List<String> distinct = Arrays.asList("Utah", "Nevada", "California",
				"Oregon");
		assertTrue(distinct.containsAll(HelperMethods
				.getDistinctElements(states)));
	}

	@Test
	public void testPrepareListData( )
	{
		assertEquals(0, HelperMethods.prepareListData(0).size());

		List<List<Integer>> data = new ArrayList<List<Integer>>();
		data.add(new ArrayList<Integer>());
		assertEquals(1, HelperMethods.prepareListData(1).size());
	}

	@Test
	public void testPerformOperation( )
	{
		List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		assertEquals(15, HelperMethods.performOperation(0, data), 1e-6);
		assertEquals(3, HelperMethods.performOperation(1, data), 1e-6);
		assertEquals(5, HelperMethods.performOperation(2, data), 1e-6);
	}

	@Test
	public void testSumData( )
	{
		assertEquals(144, HelperMethods.sumData(data), 1e-6);
	}

	@Test
	public void testAverageData( )
	{
		assertEquals(24, HelperMethods.averageData(data), 1e-6);
	}

	@Test
	public void testFindMax( )
	{
		assertEquals(83, HelperMethods.findMax(data), 1e-6);
	}

	@Test
	public void testSumTheCategories( )
	{
		// sum
		assertEquals(56, HelperMethods.sumTheCategories(0, categories,
				elements, statesData), 1e-6);

		// average
		assertEquals((5 + 9) / 2.0 + (6 + 4 + 4) / 3.0 + (12 + 10 + 6) / 3.0,
				HelperMethods.sumTheCategories(1, categories, elements,
						statesData), 1e-6);

		// max
		assertEquals(9 + 6 + 12, HelperMethods.sumTheCategories(2, categories,
				elements, statesData), 1e-6);

	}

	@Test
	public void testStoreCorrespondingData( )
	{
		List<Integer> numbers = Arrays.asList(5, 6, 12, 8, 9, 10, 4, 4, 17, 6);

		List<List<Integer>> fillUp = HelperMethods.prepareListData(elements
				.size());
		HelperMethods.storeCorrespondingData(states, numbers, elements, fillUp);

		assertTrue(statesData.equals(fillUp));

	}

}
