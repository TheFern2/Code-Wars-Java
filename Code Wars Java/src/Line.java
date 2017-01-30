/*
 * 1. Get index[x] see if change is needed, if is not needed proceed to next one
 * 2. If index is more than 25, check if there is enough change to give back
 * 3. Check if all people were serviced, and return "YES" if not return "NO"
 */
public class Line
{
	public static String Tickets(int[] peopleInLine)
	{
		// Variables
		int changeMoney = 0;
		int changeAmountNeeded = 0;

		// Constants
		final int TICKET_PRICE = 25;

		// Check if any people are standing
		if (peopleInLine == null)
		{
			System.out.println("Int array is null!");
			return "NO";
		}

		if (peopleInLine.length == 0)
		{
			System.out.println("Int array has no elements");
			return "NO";
		}

		// Check if is possible to give tickets to all people
		// in line with no starting change in the register
		for (int i = 0; i < peopleInLine.length; i++)
		{
			// Print 
			System.out.println("Index " + i + " Value " + peopleInLine[i]);
			
			// Change not needed
			if (peopleInLine[i] == 25)
			{
				changeMoney += peopleInLine[i];
				System.out.println("changeMoney = " + changeMoney + " Index " + i);
				
				// If last customer
				if (i == peopleInLine.length -1)
				{
					System.out.println("Last customer");
					return "YES";
				}
			}

			// Is change needed?
			if (peopleInLine[i] != 25)
			{
				changeAmountNeeded = peopleInLine[i] - TICKET_PRICE;
				if (changeMoney == 0)
				{
					System.out.println("Register is empty" + " ChangeMoney " + changeMoney);
					return "NO";
				} else if (changeMoney >= changeAmountNeeded)
				{
					changeMoney -= changeAmountNeeded + TICKET_PRICE;
					// If last customer
					if (i == peopleInLine.length -1)
					{
						System.out.println("Last customer");
						return "YES";
					}
				} else if (changeMoney <= changeAmountNeeded)
				{
					// If last customer
					if (i == peopleInLine.length -1)
					{
						System.out.println("Last customer");
						return "NO";
					}
				}

			}

		}

		return null;
	}
}
