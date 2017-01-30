

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

//**************************************************************
// Author: Fernando Balandran
// Date: Jan 25, 2017
// 
// Program: BasicEncryption.java
// 
// 
// 
//**************************************************************

public class BasicEncrypt {

		public static void main(String[] args) {
			BasicEncrypt enc = new BasicEncrypt();
			enc.encrypt("ywghggmlzgflhpxzkksbbym", 374);
			enc.encrypt("nkhkkltsmtmvmwxxpsvwi", 87);

			System.out.printf("Test string " + "%c%c%c\n", 128, 129, 130);
			
			//for(int i = 0; i < 255; i++)
			//{
			//	System.out.printf("Test string " + "%c%d\n", i, i);
			//}
			
			Charset oem = Charset.forName("Cp437");
			// 0xFF = 255, 0x20 = 32, 
			ByteBuffer b = ByteBuffer.allocate(0xFF - 0x20 - 1); // Alloc 222bytes?
			int testByte = 175;
			/*for (int i = 0x20; i < 0xFF; i++) 
			{
			    if (i == 0x7F) // 127
			    {
			        // skip DEL
			        continue;
			    }

			    b.put((byte) i);
			}*/
			
			b.put((byte) testByte);
			b.flip();
			CharBuffer c = oem.decode(b);
			
			System.out.println(c.toString());
			
			int d = 0x2561;
			String symbol = "\u02E6";
			String s = Character.toString((char)d);	
			
			System.out.println("\u0220");
		}
		
		public String encrypt(String text, int rule) {
			
			char encodedCharacter;
			int encodedCharacterInt;
			String tempString = "";
			Charset oem = Charset.forName("Cp437");
			// 0xFF = 255, 0x20 = 32, 
			ByteBuffer b = ByteBuffer.allocate(0xFF - 0x20 - 1); // Alloc 222bytes?
			
			System.out.println("Input string " + text);
			System.out.println("Input rule " + rule);
			 
			// Turn string into sb
			StringBuilder sb = new StringBuilder();
			//sb.append(text);
			
			// Take string and shift it
			for(int i = 0; i < text.length(); i++)
			{
				encodedCharacterInt = shiftChar(text.charAt(i), rule);
				
				// Here we need to check if encodedCharacterInt is between 128-255
				if(encodedCharacterInt >= 127 && encodedCharacterInt <= 255)
				{
					b.put((byte) encodedCharacterInt);
					b.flip();
					CharBuffer c = oem.decode(b);
					System.out.println(c.toString());
					
					tempString = c.toString();
					sb.append(tempString);
					b.clear();	
				}
				
				if(encodedCharacterInt >= 0 && encodedCharacterInt <= 127)
				{
					encodedCharacter = (char)encodedCharacterInt;
					sb.append(encodedCharacter);
				}
				
				
			}
		       
			System.out.println("Encrypted string is = " + sb.toString());
	        //return encrypted text;
			return sb.toString();
	    }

	
	public static int shiftChar(char c, int shift)
	{
		
		final int START_RANGE = 32;
		final int END_RANGE = 255;
		int tempCount = 0;
		int additionalCount = 0;
		int startingIndex;
		int indexInitCheck = 0;
		
		//startingIndex = (char) (c + shift);
		startingIndex = (char) (c);
		indexInitCheck = (char) (c + shift);
		
		// System.out.println("Index Init Check" + indexInitCheck);

		if(indexInitCheck >= END_RANGE)
		{
			// need to start counting the shift from ascii character
			// Example: y = 121, and it equals X = 88 with a rule of 223
			
			int endCount = END_RANGE - startingIndex;
			
			for(int i = 0; i < endCount; i++)
			{
				tempCount = tempCount + 1;
			}
			
			// here we subtract tempCount from initial shift
			additionalCount = shift - tempCount;
			
			if (additionalCount <= 256) {
				System.out.println("Additional Count if less than 256 = "
						+ additionalCount);
			}
			if(additionalCount >= 256)
			{
				additionalCount = shift - additionalCount;
				System.out.println("Additional Count if more than 256 = " + additionalCount);

			}
			
		}
		
		// if Index is greater than 256
		// Example: ilofchegysobzcaaydtzqavrrpcsvrg 287
		// i = 105, + 287 = 392
		
		
		if(indexInitCheck <= END_RANGE)
		{
			// if we know that the initial index won't be above 255
			// then we can just add
			return (char)(c + shift);
		}
				
		return (char)(additionalCount);
	}


}
