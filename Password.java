/*
 *  Author: Kevin Hames
 *  Date: 11/29/2016
 *  Description: Allows a user to enter a keyword, returning a 20 character password string
 *  			 or decode an already encoded password
 */

import java.util.Scanner;

public class Password {

	public static void main(String[] args) {
		
		// Variable deceleration 
		int menu=0;
		String key = "";
		Scanner in = new Scanner(System.in);
		
		// UI
		System.out.println("Welcome to the Password Protection Program");
		while(menu!=4)
		{	
		System.out.print("(1) Encode \n(2) Step by Step Encoding \n(3) Decode \n(4) Exit\n");
		System.out.println("Option: ");
		menu = in.nextInt();
		switch(menu) {
		// Encode
		case 1: 
			System.out.println("Enter a key to encode: ");
			key = in.next();
			System.out.println("Your new password is: " + protection.encode(key, false));	
			break;	
		// Encoding step by step (for demonstration and debugging)
		case 2:
			System.out.println("Enter a key to encode: ");
			key = in.next();
			System.out.println("Your new password is: " + protection.encode(key, true));
			break;
		// Decode
		case 3:
			System.out.println("Enter password to decode: ");
			key = in.next();
			System.out.println("Your original key was: " + protection.decode(key));
			break;
		// Exit
		case 4:
			System.out.println("Have a good day!");
			break;
		// Invalid option
		default: 
			System.out.println("Invalid Command");
			break;
		}
		}
		in.close();
	}
}
