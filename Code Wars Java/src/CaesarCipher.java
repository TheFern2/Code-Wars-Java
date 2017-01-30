// Example shift by 4
// Plain text "fernando"
// Cipher txt "jivrerhs"

import java.util.*;

public class CaesarCipher
{
	// Variables
	static String codedMessage;
	static String plainMessage;
	static char plainCharacter;
	static char encodedCharacter;
	static List<String> codedMessageList = new ArrayList<String>();
	
	public static List<String>  movingShift(String s, int shift) 
	{
		codedMessageList.clear();
		
		System.out.println("Input String to encode " + s);
		
		
		// Take string and shift it
		for(int index = 0; index < s.length(); index++)
		{
			// shift by one on first iteration, by 2 on second...3rd..and so on....and add to List
			encodedCharacter = shiftChar(s.charAt(index), (shift + index));
			System.out.println("Char " + s.charAt(index) + ", Shift = " + shift + ", Index = " + index + " ");
			
			codedMessageList.add(Character.toString(encodedCharacter));
			System.out.println("Encoded char " + codedMessageList.get(index));
		}
		
		
		return splitString(codedMessageList);
	}
	
	public static String  demovingShift(List<String> s, int shift) 
	{
		// loc vars
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		
		// Here we are given a list of strings
		// 1. Need to append all as one big string
		for(int i = 0; i < s.size(); i++)
		{
			sb.append(s.get(i));
		}
		
		System.out.println("Appended encoded string " + sb);
		
		// 2. Need to de-shift
		for(int i = 0; i < sb.length(); i++)
		{
			plainCharacter = shiftChar(sb.charAt(i), ((shift * -1) - i)); 
			System.out.println(sb.charAt(i) + " Shift " + (shift * -1) + " -" + i);
			sb1.append(plainCharacter);
		}
		
		// 3. Return decoded string
		System.out.println("Decoded message " + sb1);
		
		
		return sb1.toString();
	}
	
	public static List<String> splitString(List<String> s)
	{
		// loc vars
		StringBuilder sb = new StringBuilder();
		List<String> listOfStrings = new ArrayList<String>();
		
		System.out.println("The size of the list string is " + s.size());
		// get the length of s, and assess how to be split
		// split in 5 equal parts. Tested. 
		// if length is 16, it will split into 4 strings of 4, and 5 will be an empty string ""
		// if multiple options are available then make 5th longer
		
		// If size is less than 10 return one string
		// Used this for testing not needed for submission
		/* if(s.size() <= 10)
		{
			for(int i = 0; i < s.size(); i++)
			{
				sb.append(s.get(i));
				
				if(i == s.size() -1)
				{
					listOfStrings.add(sb.toString());
					sb.setLength(0); // clear string builder to start new string
				}
					
			}
		}*/
		
		// Five equal parts
		if(s.size() % 5 == 0)
		{
			int stringLen = s.size() / 5;
			System.out.println("There are five equal parts " + stringLen);
			
			// 1. Add n chars to each index on the list
			// Nested loop
			
			// Iterate through all chars <- TODO Make this into a method if possible
			for(int i = 1; i < s.size() + 1; i++)
			{
				sb.append(s.get(i - 1));
				
				if((i % stringLen) == 0)
				{
					System.out.println("SB = " + sb);
					System.out.println("Remainder " + i % stringLen);
					listOfStrings.add(sb.toString());
					
					sb.setLength(0); // clear string builder to start new string
				}
				
				
			}
			
		}
		
		// Four parts equal and 5th shorter or 5th null
		// Need to think how to implement both options here
		if(s.size() % 4 == 0 || s.size() % 5 >= 1)
		{
			
			// loc vars
			float tempDivisor = 0.0f;
			int fourStringLen = 0;
			int fifthStringLen = 0;

			// If 4 equal parts, and 5th is an empty string
			if(s.size() % 4 == 0)
			{
				// Divide array size by 5
				// tempDivisor = s.size() / 5;
				fourStringLen = s.size() / 4;

				// An empty 5th string
				fifthStringLen = 0;
			}

			// If 4 equal parts and a short 5th string
			if(s.size() % 5 >= 1)
			{
				// Divide array size by 5
				tempDivisor = s.size() / 5;
				fourStringLen = Math.round(tempDivisor + 0.5f); // Round it to the next whole number

				// the remaining will be for the 5th one
				fifthStringLen = s.size() - (fourStringLen * 4);
			}

			// 1. Add X chars to each index on the list
			// Nested loop
			
			// Iterate through all chars
			for(int i = 1; i < s.size() + 1; i++)
			{
				sb.append(s.get(i - 1));
				
				if((i % fourStringLen) == 0)
				{
					System.out.println("SB = " + sb);
					System.out.println("Remainder " + i % fourStringLen);
					listOfStrings.add(sb.toString());
					
					sb.setLength(0); // clear string builder to start new string

					// If last index, add an empty string to 5th
					if(fifthStringLen == 0 && (s.size() - 1) == i)
					{
						listOfStrings.add("");
					}
				}

				if(i == s.size()) // Add short 5th string
				{
					listOfStrings.add(sb.toString());
				}
				
				
			}

		}
		
		// Print list for testing
		for(String element: listOfStrings)
		{
			System.out.println("List element " + element);
		}
		
		// Multiple options, 5th longer <- Didn't need this at all
		
		return listOfStrings;	
	}
	
	// Need to encode a character, according to shift.
	// Need to set ranges A-Z and a-z
	/*
	 * Most of my time was spent on shiftChar method and lots of testing / debuggig. By now you might now that
	 * dealing with characters shifting you will need to mess around with ASCII, and that is the whole secret here.
	 * Before encrypting/decrypting char we need to check the range, if is out then we need to calculate by looping within
	 * the range, and that is where the shift helper comes into play.
	 * Spaces, and anything outside a-z, A-Z is not encrypted/decrypted.
	 * Key thing here is that as text grows, shiftedCharacterValue increases and we need to check min, max ranges accordingly.
	 */
	public static char shiftChar(char c, int shift)
	{
		int shiftedCharacterValue = shift + c;
		
		// If space do not encode
		if(c == 32)
		{
			return c;
		}
		
		// Temp constants
		final int LOWERCASE_MIN_RANGE = 97;
		final int LOWERCASE_MAX_RANGE = 122;
		final int UPPERCASE_MIN_RANGE = 65;
		final int UPPERCASE_MAX_RANGE = 90;
		final int SHIFT_HELPER = 26;
		
		//If character is lowercase a..z
	    if ((c >= LOWERCASE_MIN_RANGE) && (c <= LOWERCASE_MAX_RANGE))
	    {
	        // If within range encode right away
	    		if((shiftedCharacterValue >= LOWERCASE_MIN_RANGE) && (shiftedCharacterValue <= LOWERCASE_MAX_RANGE))
	        {
	        		//encode
	        		return (char) (shiftedCharacterValue);
	        }
	        
	    		// If not within range check whether + or -
	        if((shiftedCharacterValue <= LOWERCASE_MIN_RANGE) || (shiftedCharacterValue >= LOWERCASE_MAX_RANGE))
	        {
	        		// Greater than...
	        		if(shiftedCharacterValue >= LOWERCASE_MAX_RANGE)
	        		{
	        			// original code if((shiftedCharacterValue - SHIFT_HELPER) > LOWERCASE_MAX_RANGE)
	        			if(shiftedCharacterValue > LOWERCASE_MAX_RANGE + SHIFT_HELPER)
	        			{
	        				
	        				// Need a while loop here as shiftedCharacterValue while grow exponentially
	        				// As the text size increases Note: Original code left for historic purpose.

	        				/*if(shiftedCharacterValue > (LOWERCASE_MAX_RANGE + (SHIFT_HELPER * 2)))
	        				{
	        					return (char) (shiftedCharacterValue - (SHIFT_HELPER * 3)); 
	        				} */
	        				
	        				
	        				int i = 1;
	        				char tempChar = 'c';

	        				while(shiftedCharacterValue > (LOWERCASE_MAX_RANGE + (SHIFT_HELPER * i)))
	        				{
	        					tempChar = (char) (shiftedCharacterValue - (SHIFT_HELPER * (i + 1)));
	        					i++; 
	        				}

	        				//return (char) (shiftedCharacterValue - (SHIFT_HELPER * 2)); 
	        				return tempChar;
	        			}
	        			
	        			return (char) (shiftedCharacterValue - SHIFT_HELPER);
	        		}
	        		
	        		// Less than...
	        		if(shiftedCharacterValue <= LOWERCASE_MIN_RANGE)
	        		{
	        			if(shiftedCharacterValue < LOWERCASE_MIN_RANGE - SHIFT_HELPER)
	        			{
	        				/*if(shiftedCharacterValue < (LOWERCASE_MIN_RANGE - (SHIFT_HELPER * 2))) // Testing
	        				{
	        					return (char) (shiftedCharacterValue + (SHIFT_HELPER * 3));
	        				}
	        				
	        				return (char) (shiftedCharacterValue + (SHIFT_HELPER * 2)); // Not tested yet */

	        				int i = 1;
	        				char tempChar = 'c';

	        				while(shiftedCharacterValue < (LOWERCASE_MIN_RANGE - (SHIFT_HELPER * i)))
	        				{
	        					tempChar = (char) (shiftedCharacterValue + (SHIFT_HELPER * (i + 1)));
	        					i++; 
	        				}

	        				return tempChar;
	        			}
	        			
	        			return (char) (shiftedCharacterValue + SHIFT_HELPER);
	        		}
	        }
	    }
	    
	    //Else if character is A..Z
	    if ((c >= UPPERCASE_MIN_RANGE) && (c <= UPPERCASE_MAX_RANGE))
	    {
	        // If within range encode right away
	    		if((shiftedCharacterValue >= UPPERCASE_MIN_RANGE) && (shiftedCharacterValue <= UPPERCASE_MAX_RANGE))
	        {
	        		//encode
	        		return (char) (shiftedCharacterValue);
	        }
	        
	    		// If not within range check whether + or -
	        if((shiftedCharacterValue <= UPPERCASE_MIN_RANGE) || (shiftedCharacterValue >= UPPERCASE_MAX_RANGE))
	        {
	        		// Greater than...
	        		if(shiftedCharacterValue >= UPPERCASE_MAX_RANGE)
	        		{
	        			// This hasn't been tested yet in any test case
	        			if(shiftedCharacterValue > UPPERCASE_MAX_RANGE + SHIFT_HELPER)
	        			{
	        				int i = 1;
	        				char tempChar = 'C';

	        				while(shiftedCharacterValue > (UPPERCASE_MAX_RANGE + (SHIFT_HELPER * i)))
	        				{
	        					tempChar = (char) (shiftedCharacterValue - (SHIFT_HELPER * (i + 1)));
	        					i++; 
	        				}

	        				return tempChar;
	        			}

	        			return (char) (shiftedCharacterValue - SHIFT_HELPER);
	        		}
	        		
	        		// Less than...
	        		if(shiftedCharacterValue < UPPERCASE_MIN_RANGE)
	        		{
	        			// This hasn't been tested yet in any test case
	        			if(shiftedCharacterValue < UPPERCASE_MIN_RANGE - SHIFT_HELPER)
	        			{
	        				int i = 1;
	        				char tempChar = 'c';

	        				while(shiftedCharacterValue < (UPPERCASE_MIN_RANGE - (SHIFT_HELPER * i)))
	        				{
	        					tempChar = (char) (shiftedCharacterValue + (SHIFT_HELPER * (i + 1)));
	        					i++; 
	        				}

	        				return tempChar;
	        			}

	        			return (char) (shiftedCharacterValue + SHIFT_HELPER);
	        		}
	        }
	    }
	    
	    //Else do not encrypt character
	    else {
	        return c;
	    }
	    
		return c;

	}
}
