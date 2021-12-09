package com.dkr.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
  Problem: Given a list of Items that make up a Product, associated prices for each of the items and a set of rules specifying which Items can be configured to make up a
  product; identify the possible product configurations that are under $5.
  
  
  Inputs:
  
  An array of strings containing names of items
  
  An array of integers containing the prices of the items. (Array index
  matches)
  
  A 2D array containing which other items can be combined with a given item.
  (Array index matches)
  
  
  Example Inputs:
  
  [“Lettuce", “Cheese", “Chicken", “Bread", “Bean Patty"]
  
  [1, 1, 3, 1, 2]
  
  
  
  [1-“Lettuce", 1-“Cheese", 3-“Chicken", 1-“Bread", 2-“Bean Patty"]
  
[Cheese, Chicken, Bread]  == Sum :5
[Bean Patty, Chicken]  == Sum :5
[Lettuce, Cheese, Chicken]  == Sum :5
[Lettuce, Chicken, Bread]  == Sum :5
[Lettuce, Bean Patty, Cheese, Bread]  == Sum :5

  
 
  */

public class TestMainWithOutDuplicateCombinations {

	public static void main(String[] args) {

		int[] prices = new int[] { 1, 1, 3, 1, 2 };
		String productNames[] = new String[] { "Lettuce", "Cheese", "Chicken", "Bread", "Bean Patty" };

		Map<String, Integer> priceNameMap = new HashMap<String, Integer>();
		priceNameMap.put("Lettuce", 1);
		priceNameMap.put("Cheese", 1);
		priceNameMap.put("Chicken", 3);
		priceNameMap.put("Bread", 1);
		priceNameMap.put("Bean Patty", 2);

		printAndFilterResults(findCombination4(prices, 5, productNames), priceNameMap);
	}

	public static Set<Set<String>> findCombination4(int[] prices, int targetPrice, String[] productNames) {
		Set<List<Integer>> resultPrices = new HashSet<>();
		Set<Set<String>> resultNames = new HashSet<>();
		withRecurrsive(prices, 0, targetPrice, resultPrices, new ArrayList(), resultNames, new ArrayList(),
				productNames);
//		printListNewLine(resultNames);
		return resultNames;
	}

	public static void withRecurrsive(int[] prices, int start, int target, Set<List<Integer>> resultPrices,
			List<Integer> priceslist, Set<Set<String>> resultNames, List<String> nameslist, String[] productNames) {
		if (target < 0)
			return;
		if (target == 0) {
			resultPrices.add(new ArrayList(priceslist));
			resultNames.add(new HashSet(nameslist));
		}
		for (int i = start; i < prices.length; i++) {
			priceslist.add(prices[i]);
			nameslist.add(productNames[i]);
			withRecurrsive(prices, i, target - prices[i], resultPrices, priceslist, resultNames, nameslist,
					productNames);
			priceslist.remove(priceslist.size() - 1);
			nameslist.remove(nameslist.size() - 1);
		}
	}

	public static void printListNewLine(Set<List<String>> namesList) {
		for (List<String> names : namesList) {
			System.out.println(names);
		}
	}

	public static Set<Set<String>> printAndFilterResults(Set<Set<String>> namesList, Map<String, Integer> priceNames) {

		Set<Set<String>> finalResult = new HashSet<>();
		for (Set<String> names : namesList) {
			int sum = 0;
			for (String name : names) {

				// System.out.print( " "+name +" :"+priceNames.get(name));
				sum = sum + priceNames.get(name);
			}
			if (sum == 5) {
				finalResult.add(names);
				System.out.println(names + "  == Sum :" + sum);
			}

		}

		return finalResult;
	}
}