import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Example 1: write a method that fills an ArrayList from 
	data in a file
Example 2: print the elements in an ArrayList, one per line
Example 3: write a method with the following pre and post conditions and header:
Precondition: ArrayList list contains Integer values sorted in increasing order
Postcondition: value inserted in its correct position in list
public static void insert(ArrayList<Integer> list, Integer value)
Example 4: write a method that returns an ArrayList (of random size from 1-100) 
of random integers from -50 to 50
Example 5: write a method that swaps two values in list, indexed at i and j
Example 6: write a method that prints all negatives in list a 
(assume that list contains Integer values)
Example 7: write a method that changes every even-indexed element of 
strList to the empty String (assume that strList contains String values)
Example 8: write a method that removes all elements from an ArrayList
 of type String that have the value of "and", "the", "a", "or", "an", "is", "are" 
*/

public class Notes 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		//types of declarations
		ArrayList anything = new ArrayList();
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<>();
		
		/*
		ArrayList Functions
		int size()
		boolean add(E obj)
		void add(int index, E obj)
		E get(int index)
		E set(int index, E obj)
		E remove(int index)
		 */
		
		//tracing example 1
		words.add("apple");
		words.add("orange");
		words.add(1, "banana");
		words.add(0, "pomegranate");
		words.set(1, "mango");
		
		//tracing example 2
		nums.add(6);//6
		nums.add(100);//6 100
		nums.add(42);//6 100 42
		nums.add(2, 3);//6 100 3 42
		nums.set(1, -4);//6 -4 3 42
		nums.add(0, 4);//4 6 -4 3 42
		nums.set(nums.size() - 1, 7);//4 6 -4 100 3 7
		nums.add(nums.size() - 1, 0);//4 6 -4 100 3 0 7
		
		ArrayList<String> fileWords = fillWordList("words");
		System.out.println(fileWords);
		
		ArrayList<Integer> filenums = fillWordListInt("data");
		System.out.println(filenums);
		
		printList(fileWords);
		printList(filenums);
	}
	
	public static void removeSmallWords(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			if(isSmallWord(list.get(i))) {
				list.remove(i);
				i--;
			}
		}
	}
	
	public static boolean isSmallWord(String word) {
		String [] smallWords = {"a", "are", "the", "is", "an", "or", "and"};
		for(String temp : smallWords)
			if(word.equalsIgnoreCase(temp))
				return true;
		return false;
	}
	
	public static void printNegatives(ArrayList<Integer> list) {
		for(Integer num : list) {
			if(num < 0)
				System.out.println(num);
		}
	}
	
	public static int findMaxIndex(ArrayList list) {
		ArrayList<Comparable> compList = (ArrayList<Comparable>)list;
		int maxIndex = 0;
		for(int i = 1; i < compList.size(); i++) {
			if(compList.get(i).compareTo(compList.get(maxIndex)) > 0) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public static ArrayList<Integer> getRandomList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < ((int)(100*Math.random())) +1; i++) {
			list.add(((int)(100*Math.random())) - 50);
		}
		return list;
	}
	
	public static void insert(ArrayList<Integer> list, Integer value) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) > value) {
				list.add(i, value);
				return;
			}	
		}
		list.add(value);
	}
	
	public static void printList(ArrayList list) {
		for(Object o : list)
			System.out.println(o);
	}
	
	public static ArrayList<String> fillWordList(String fileName) throws FileNotFoundException{
		Scanner fileScan = new Scanner(new File(fileName));
		ArrayList<String> words = new ArrayList<String>();
		
		while(fileScan.hasNext())
			words.add(fileScan.next());
		
		return words;
	}
	
	public static ArrayList<Integer> fillWordListInt(String fileName) throws FileNotFoundException{
		Scanner fileScan = new Scanner(new File(fileName));
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		while(fileScan.hasNextInt())
			numbers.add(fileScan.nextInt());
		
		return numbers;
	}
}