package com.nttdata;

import java.util.*;

public class PolicyMain {
	static int ch=0,result;
	static Scanner scan=new Scanner(System.in);
	public static void main(String[] args) {
		
	
	try {
		do {
			
			System.out.println("-------Choose below one---------");
			System.out.println("1.Insert Details");
			System.out.println("2.Fetch Details");
			System.out.println("3.Delete Details");
			System.out.println("4.Update details");
			
			System.out.println("Please choose any one opertaion to perform.");
			ch=scan.nextInt();
			switch(ch)
			{
			case 1:
				Policy.Insert();
				break;
			case 2:
				Policy.Fetch();
				
			case 3:
				Policy.Delete();
				
			
			case 4:
				Policy.Update();
				
			}
			
		}while(ch != 0);
		System.out.println("Wrong selection...");
	}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
