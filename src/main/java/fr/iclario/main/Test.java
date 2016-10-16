package fr.iclario.main;

import java.util.Arrays;

public class Test
{
	public static void main (String[] args)
	{
		String[] list = new String[5];
		list[0] = "152";
		list[1] = "5";
		list[2] = "596";
		list[3] = "63";

		Arrays.sort(list);

		for(int i = 0; i < list.length; i++)
			System.out.println(list[i]);
	}
}