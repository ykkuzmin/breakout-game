package com.example.breakout;

public class CallingNames {
	
	public static String response(String name, String difficulty) {
        String txt = "";
		if (difficulty.equals("Easy")) 
		{
        	txt = "You are a chicken!";
        } else if (difficulty.equals("Masochist")) 
        {
        	txt = "You must be mental!";
        }
        else if (difficulty.equals("Masochist")) 
        {
        	txt = "You must be mental!";
        }
		txt = "Welcome " + name + "! You selected " + difficulty + " difficulty. " + txt;
		return txt;
	}

}
