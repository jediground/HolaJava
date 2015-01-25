package com.foobar.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringClass {

	@Test
	public void testMyTrim() {
		String str = "   as  gds ";
		System.out.println(myTrim(str));
	}

	@Test 
	public void testReverseString() {
		String str = "lasd9966jgl";
		System.out.println(reverseString(str, 4, 7));
	}
	
	@Test
	public void testRepeatTime() {
		String max = "44asl44sjad44lgj44lu14445uo144444asdgals44";
		String min = "44";
		System.out.println(repeatTimes(max, min));
	}
	 
	@Test
	public void testGetMaxSubString() {
		String str = "akga333333nbaab444444noaoejlanbaabdnba";
		String str2 = "nbab333333abnnbaabkdj444444lnbaailo";
		List<String > max_sub = getMaxSubString(str, str2);
		System.out.println("the max sub string is :" + max_sub);
	}

	public String myTrim(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end && ' ' == str.charAt(start)) {
			start++;
		}
		while (start < end && ' ' == str.charAt(end)) {
			end--;
		}
		return str.substring(start, end);
	}
	
	public String reverseString(String str, int start, int end) {
		char[] chs = str.toCharArray();
		return reverseArray(chs, start, end);
	}
	
	public String reverseArray(char[] chs, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			char temp = chs[i];
			chs[i] = chs[j];
			chs[j] = temp;
		}
		
		return new String(chs);
	}
	
	public int repeatTimes(String str1, String str2) {
		String max = str1.length() > str2.length() ? str1 : str2;
		String min = (max.equals(str1)) ? str2 : str1;
		
		int times = 0;
		int location = 0;
		while (-1 != (location = max.indexOf(min))) {
			times++;
			max = max.substring(location + min.length());
		}
		
		return times;
	}
	
	public List<String> getMaxSubString(String str1, String str2) {
		String max = str1.length() > str2.length() ? str1 : str2;
		String min = (max.equals(str1)) ? str2 : str1;

		List<String> list = new ArrayList<String>();
		int minLength = min.length();
		for (int i = 0; i < minLength; i++) {
			for (int j = 0, k = minLength - i; k != minLength + 1; j++, k++) {
				String subString = min.substring(j, k); 
				if (max.contains(subString)) {
					list.add(subString);
				}
			}
			if (0 != list.size()) {
				return list;
			}
		}
		
		return null;
	}
}
