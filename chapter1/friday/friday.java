/*
ID: imdoing1
LANG: JAVA
PROG: friday 
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class friday {
	public static void main (String args[]) {
		Scanner scan = null;
		
		try {
			scan = new Scanner(new BufferedReader(new FileReader("friday.in")));
		} catch (Exception e) {}

		int add_year = 0;
		if (scan.hasNext()) 
			add_year = scan.nextInt();

		int year_index = 1900;
		int m_index = 0;
		int t_index = 0;
		int w_index = 0;
		int th_index = 0;
		int f_index = 0;
		int sat_index = 0;
		int sun_index = 0;
		String start = "Saturday";
		for (int i = year_index; i <= (year_index+add_year-1); i ++) {
			//System.out.println("debug:" + i + " " + start);
			if (start.equals("Saturday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 1;
					t_index += 2;
					w_index += 2;
					th_index += 1;
					f_index += 2;
					sat_index += 3;
					sun_index += 1;
					start = "Monday";
				}
				else {
					m_index += 1;
					t_index += 3;
					w_index += 1;
					th_index += 2;
					f_index += 2;
					sat_index += 2;
					sun_index += 1;
					start = "Sunday";
				}
				continue;
			}
			if (start.equals("Sunday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 1;
					t_index += 1;
					w_index += 2;
					th_index += 2;
					f_index += 1;
					sat_index += 2;
					sun_index += 3;
					start = "Tuesday";
				}
				else {
					m_index += 1;
					t_index += 1;
					w_index += 3;
					th_index += 1;
					f_index += 2;
					sat_index += 2;
					sun_index += 2;
					start = "Monday";
				}
				continue;
			}
			if (start.equals("Monday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 3;
					t_index += 1;
					w_index += 1;
					th_index += 2;
					f_index += 2;
					sat_index += 1;
					sun_index += 2;
					start = "Wednesday";
				}
				else {
					m_index += 2;
					t_index += 1;
					w_index += 1;
					th_index += 3;
					f_index += 1;
					sat_index += 2;
					sun_index += 2;
					start = "Tuesday";
				}
				continue;
			}
			if (start.equals("Tuesday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 2;
					t_index += 3;
					w_index += 1;
					th_index += 1;
					f_index += 2;
					sat_index += 2;
					sun_index += 1;
					start = "Thursday";
				}
				else {
					m_index += 2;
					t_index += 2;
					w_index += 1;
					th_index += 1;
					f_index += 3;
					sat_index += 1;
					sun_index += 2;
					start = "Wednesday";
				}
				continue;
			}
			if (start.equals("Wednesday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 1;
					t_index += 2;
					w_index += 3;
					th_index += 1;
					f_index += 1;
					sat_index += 2;
					sun_index += 2;
					start = "Friday";
				}
				else {
					m_index += 2;
					t_index += 2;
					w_index += 2;
					th_index += 1;
					f_index += 1;
					sat_index += 3;
					sun_index += 1;
					start = "Thursday";
				}
				continue;
			}
			if (start.equals("Thursday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 2;
					t_index += 1;
					w_index += 2;
					th_index += 3;
					f_index += 1;
					sat_index += 1;
					sun_index += 2;
					start = "Saturday";
				}
				else {
					m_index += 1;
					t_index += 2;
					w_index += 2;
					th_index += 2;
					f_index += 1;
					sat_index += 1;
					sun_index += 3;
					start = "Friday";
				}
				continue;
			}
			if (start.equals("Friday")) {
				if (((i%100 != 0) && (i%4 == 0)) || (i%400 == 0)){
					m_index += 2;
					t_index += 2;
					w_index += 1;
					th_index += 2;
					f_index += 3;
					sat_index += 1;
					sun_index += 1;
					start = "Sunday";
				}
				else {
					m_index += 3;
					t_index += 1;
					w_index += 2;
					th_index += 2;
					f_index += 2;
					sat_index += 1;
					sun_index += 1;
					start = "Saturday";
				}
				continue;
			}
		}
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
			out.println(sat_index + " " + sun_index + " " + m_index + " " + t_index + " " + w_index + " " + th_index + " " + f_index + " ");	
			out.close();
		} catch (Exception ex) {};
	}
}