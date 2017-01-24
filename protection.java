/*
 *  Author: Kevin Hames
 *  Date: 11/29/2016
 *  Description: Methods for password encoding (given keyword) and decoding (given encoded string)
 */

public class protection {
	
	public static String encode(String key, boolean debug)
	{
		// Variable decelerations 
		String password = key;
		String temp = "";
		char tempc = ' ';
		int i;
		
		if(debug)
			System.out.println(password);
		
		// Step 1: Extends key to 20 characters
		for(i=key.length(); i < 20; i++)
			password += key.charAt(i%key.length());
		
		if(debug)
			System.out.println(password);
		
		// Step 2: Sets capitalization based on index (logarithmic)
		temp += password.substring(0,1);
		temp += password.substring(1,2).toUpperCase();
		temp += password.substring(2,4);
		temp += password.substring(4,5).toUpperCase();
		temp += password.substring(5,8);
		temp += password.substring(8,9).toUpperCase();
		temp += password.substring(9, 16);
		temp += password.substring(16,17).toUpperCase();
		temp += password.substring(17,20);
		
		password = temp;
		temp = "";
		if(debug)
			System.out.println(password);
		
		// Step 3: Decriments characters by their index
		for(i=0; i < password.length(); i++)
			temp += (char) (password.charAt(i) - i); 
		password = temp; 
		
		if(debug)
			System.out.println(password);
		
		// Step 4: Rearrange String
		temp = password.substring(4, 8);
		temp += password.substring(12,16);
		temp += password.substring(8,12);
		temp += password.substring(16,20);
		temp += password.substring(0,4);
		
		password = temp;
		temp = "";
		if(debug)
			System.out.println(password);
		
		// Step 5: Increment characters by values 0-4 (based on new index)
		for(i=0; i < password.length(); i++)
			temp += (char) (password.charAt(i) + i%5);
		
		password = temp;
		temp = "";
		if(debug)
			System.out.println(password);
		
		// Step 6: rotate lower case letters 13 
		for(i=0; i<password.length();i++)
		{
			tempc = password.charAt(i);
			if(tempc >= 'a' && tempc <= 'm')
				temp += (char) (tempc + 13);
			else if (tempc <= 'z' && tempc >= 'n')
				temp += (char) (tempc - 13);
			else
				temp += tempc;
		}
		password = temp;
		temp = "";
		if(debug)
			System.out.println(password);
		
		// Step 7: Rearrange 
		temp = password.substring(18, 20);
		temp += password.substring(10,14);
		temp += password.substring(2,10);
		temp += password.substring(0,2);
		temp += password.substring(14,18);
		
		password = temp;
		temp = "";
		if(debug)	
			System.out.println(password);
		
		return password;
	}
	
	public static String decode(String key)
	{
		// Variable decelerations
		int i;
		String password = key;
		String temp = "";
		char tempc = ' ';
		
		// Undo Step 7
		temp = password.substring(14,16);
		temp += password.substring(6,14);
		temp += password.substring(2,6);
		temp += password.substring(16,20);
		temp += password.substring(0,2);
		
		password = temp;
		temp = "";
		
		// Undo Step 6
		for(i=0; i<password.length();i++)
		{
			tempc = password.charAt(i);
			if(tempc >= 'a' && tempc <= 'm')
				temp += (char) (tempc + 13);
			else if (tempc <= 'z' && tempc >= 'n')
				temp += (char) (tempc - 13);
			else
				temp += tempc;
		}
		password = temp;
		temp = ""; 
		
		// Undo Step 5
		for(i=0; i < password.length(); i++)
			temp += (char) (password.charAt(i) - i%5);
		
		password = temp;
		temp = "";
		
		// Undo Step 4
		temp = password.substring(16, 20);
		temp += password.substring(0,4);
		temp += password.substring(8,12);
		temp += password.substring(4,8);
		temp += password.substring(12,16);
		
		password = temp;
		temp = "";
		
		// Undo Step 3
		for(i=0; i < password.length(); i++)
			temp += (char) (password.charAt(i) + i); 
		password = temp;
		temp = "";
		
		// Undo Step 2
		temp += password.substring(0,1);
		temp += password.substring(1,2).toLowerCase();
		temp += password.substring(2,4);
		temp += password.substring(4,5).toLowerCase();
		temp += password.substring(5,8);
		temp += password.substring(8,9).toLowerCase();
		temp += password.substring(9, 16);
		temp += password.substring(16,17).toLowerCase();
		temp += password.substring(17,20);
		
		password = temp;
		temp = "";
		
		// Undo Step 1
		boolean userkey = false;
		for(i=2; i<password.length() && userkey == false;i++)
		{	
			temp = "";
			if(password.charAt(0)==password.charAt(i))
			{	
				for(int j = 0; j < i; j++)
				{
					if(password.charAt(j)==password.charAt(j+i))
					{	
						temp += password.charAt(j);
						userkey = true;
					}	
					else
						userkey = false;
				}
			}
		}	
		password = temp;
		
		return password;
	}
}
