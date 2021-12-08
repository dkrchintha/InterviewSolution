package com.dkr.interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
  
[Bread, Bean Patty, Bean Patty]
[Lettuce, Lettuce, Lettuce, Bread, Bread]
[Lettuce, Lettuce, Chicken]
[Cheese, Cheese, Bread, Bread, Bread]
[Lettuce, Lettuce, Cheese, Bean Patty]
[Cheese, Cheese, Cheese, Cheese, Cheese]
[Lettuce, Cheese, Bread, Bread, Bread]
[Lettuce, Bread, Bread, Bean Patty]
[Lettuce, Cheese, Cheese, Cheese, Cheese]
[Lettuce, Chicken, Bread]
[Bread, Bread, Bread, Bread, Bread]
[Cheese, Cheese, Cheese, Bean Patty]
[Lettuce, Cheese, Cheese, Cheese, Bread]
[Lettuce, Cheese, Chicken]
[Cheese, Chicken, Bread]
[Lettuce, Cheese, Cheese, Bean Patty]
[Lettuce, Lettuce, Lettuce, Lettuce, Bread]
[Lettuce, Lettuce, Lettuce, Cheese, Cheese]
[Cheese, Bread, Bread, Bean Patty]
[Cheese, Cheese, Cheese, Cheese, Bread]
[Lettuce, Lettuce, Lettuce, Lettuce, Cheese]
[Lettuce, Lettuce, Lettuce, Cheese, Bread]
[Lettuce, Lettuce, Cheese, Bread, Bread]
[Lettuce, Lettuce, Bread, Bean Patty]
[Lettuce, Bean Patty, Bean Patty]
[Lettuce, Lettuce, Bread, Bread, Bread]
[Chicken, Bean Patty]
[Lettuce, Lettuce, Lettuce, Lettuce, Lettuce]
[Lettuce, Lettuce, Cheese, Cheese, Cheese]
[Cheese, Bread, Bread, Bread, Bread]
[Lettuce, Bread, Bread, Bread, Bread]
[Cheese, Cheese, Chicken]
[Cheese, Cheese, Bread, Bean Patty]
[Lettuce, Cheese, Cheese, Bread, Bread]
[Lettuce, Cheese, Bread, Bean Patty]
[Bread, Bread, Bread, Bean Patty]
[Chicken, Bread, Bread]
[Lettuce, Lettuce, Lettuce, Bean Patty]
[Cheese, Bean Patty, Bean Patty]
[Lettuce, Lettuce, Cheese, Cheese, Bread]
[Cheese, Cheese, Cheese, Bread, Bread]

  
 
  */

public class TestMain {

	public static void main(String[] args) {

		int[] prices = new int[] { 1, 1, 3, 1, 2 };
		String productNames[] = new String[] { "Lettuce", "Cheese", "Chicken", "Bread", "Bean Patty" };
		findCombination4(prices, 5, productNames);
	}

	public static Set<List<String>> findCombination4(int[] prices, int targetPrice, String[] productNames) {
		Set<List<Integer>> resultPrices = new HashSet<>();
		Set<List<String>> resultNames = new HashSet<>();
		withRecurrsive(prices, 0, targetPrice, resultPrices, new ArrayList(), resultNames, new ArrayList(), productNames);
		printListNewLine(resultNames);
		return resultNames;
	}

	public static void withRecurrsive(int[] prices, int start, int target, Set<List<Integer>> resultPrices,
			List<Integer> priceslist, Set<List<String>> resultNames, List<String> nameslist, String[] productNames) {
		if (target < 0)
			return;
		if (target == 0) {
			resultPrices.add(new ArrayList(priceslist));
			resultNames.add(new ArrayList(nameslist));
		}
		for (int i = start; i < prices.length; i++) {
			priceslist.add(prices[i]);
			nameslist.add(productNames[i]);
			withRecurrsive(prices, i, target - prices[i], resultPrices, priceslist, resultNames, nameslist, productNames);
			priceslist.remove(priceslist.size() - 1);
			nameslist.remove(nameslist.size() - 1);
		}
	}

	public static void printListNewLine(Set<List<String>> namesList) {
		for (List<String> names : namesList) {
			System.out.println(names);
		}
	}
}