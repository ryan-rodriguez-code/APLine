package lines;

import java.util.ArrayList;

public class Line {
	private ArrayList<Double> a = new ArrayList<Double>();
	private ArrayList<Double> b = new ArrayList<Double>();
	private ArrayList<Double> c = new ArrayList<Double>();
//Line constructor used to control and interpret user input and display program options.
	public Line() {
		while(true) {
			String input = JOP.in("Do you want to edit line list type 1, to find information about a line type 2, to compare lines type 3: ");
			String[] options = {"1", "2", "3"};
			while(JOP.waitUntil(input, options)) {input = JOP.in("Do you want to edit line list type 1, to find information about a line type 2, to compare lines type 3: ");}
			
			if(input.equals("1")) {
				String input2 = JOP.in("Do you want to enter a line to the list type 1, if you want to remove a line type 2: ");
				String[] options2 = {"1", "2"};
				while(JOP.waitUntil(input2, options2)) {input2 = JOP.in("Do you want to enter a line to the list type 1, if you want to remove a line type 2: ");}
				
				if(input2.equals("1")) {
					String input3 = JOP.in("Do you want to enter your line in (ax + by + c = 0) form type 1, to enter your line in (mx + b = y) form type 2: ");
					String[] options3 = {"1", "2"};
					while(JOP.waitUntil(input3, options3)) {input3 = JOP.in("Do you want to enter your line in (ax + by + c = 0) form type 1, to enter your line in (mx + b = y) form type 2: ");}
					
					if(input3.equals("1")) {
						String a = JOP.in("Type a decimal number (must include decimal point) for the value a: ");
						while(!JOP.isDouble(a)) {a = JOP.in("Type a decimal number (must include decimal point) for the value a: ");}
						
						String b = JOP.in("Type a decimal number (must include decimal point) for the value b: ");
						while(!JOP.isDouble(b)) {b = JOP.in("Type a decimal number (must include decimal point) for the value b: ");}
						
						String c = JOP.in("Type a decimal number (must include decimal point) for the value c: ");
						while(!JOP.isDouble(c)) {c = JOP.in("Type a decimal number (must include decimal point) for the value c: ");}
						
						this.addLineABC(Double.valueOf(a).doubleValue(), Double.valueOf(b).doubleValue(), Double.valueOf(c).doubleValue());
					}
					else if(input3.equals("2")) {
						String m = JOP.in("Type a decimal number (must include decimal point) for the value m: ");
						while(!JOP.isDouble(m)) {m = JOP.in("Type a decimal number (must include decimal point) for the value m: ");}
						
						String b = JOP.in("Type a decimal number (must include decimal point) for the value b: ");
						while(!JOP.isDouble(b)) {b = JOP.in("Type a decimal number (must include decimal point) for the value b: ");}
						
						this.addLineMB(Double.valueOf(m).doubleValue(), Double.valueOf(b).doubleValue());
					}
				}
				else if(input2.equals("2")) {
					String input3 = JOP.in("What line do you want to remove: \n" + this.displayLines());
					while(!JOP.isInt(input3) || Integer.valueOf(input3) < 0 || Integer.valueOf(input3) > this.a.size() - 1) {input3 = JOP.in("What line do you want to remove: \n" + this.displayLines());}
					this.a.remove(Integer.valueOf(input3).intValue());
					this.b.remove(Integer.valueOf(input3).intValue());
					this.c.remove(Integer.valueOf(input3).intValue());
				}
			}
			else if(input.equals("2")) {
				String input2 = JOP.in("What line do you want to get information about: \n" + this.displayLines());
				while(!JOP.isInt(input2) || Integer.valueOf(input2) < 0 || Integer.valueOf(input2) > this.a.size() - 1) {input2 = JOP.in("What line do you want to get information about: \n" + this.displayLines());}
				JOP.msg(this.printInfo(Integer.valueOf(input2).intValue()));
			}
			else if(input.equals("3")){
				String input2 = JOP.in("Do you want to compare a line and a point type 1, to solve a system of 2D equations type 2: ");
				String[] options2 = {"1", "2"};
				while(JOP.waitUntil(input2, options2)) {input2 = JOP.in("Do you want to compare a line and a point type 1, to solve a system of 2D equations type 2: ");}
				
				if(input2.equals("1")) {
					String input3 = JOP.in("What line do you want to compare: \n" + this.displayLines());
					while(!JOP.isInt(input3) || Integer.valueOf(input3) < 0 || Integer.valueOf(input3) > this.a.size() - 1) {input3 = JOP.in("What line do you want to compare: \n" + this.displayLines());}
					int line = Integer.valueOf(input3).intValue();
					
					input3 = JOP.in("What decimal valued point do you want to use (enter in the form (x,y) and both x and y must include decimal point): ");
					while(!this.isPoint(input3)) {input3 = JOP.in("What decimal valued point do you want to use (enter in the form (x,y) and both x and y must include decimal point): ");}
					double x = Double.valueOf(input3.substring(1, input3.indexOf(","))).doubleValue();
					double y = Double.valueOf(input3.substring(input3.indexOf(",") + 1, input3.length() - 1)).doubleValue();
					
					JOP.msg(this.pointLineData(line, x, y));
				}
				else if(input2.equals("2")) {
					String input3 = JOP.in("Pick one line to compare: \n" + this.displayLines());
					while(!JOP.isInt(input3) || Integer.valueOf(input3) < 0 || Integer.valueOf(input3) > this.a.size() - 1) {input3 = JOP.in("Pick one line to compare: \n" + this.displayLines());}
					int line1 = Integer.valueOf(input3).intValue();
					
					input3 = JOP.in("Pick another line to compare: \n" + this.displayLines());
					while(!JOP.isInt(input3) || Integer.valueOf(input3) < 0 || Integer.valueOf(input3) > this.a.size() - 1) {input3 = JOP.in("Pick another line to compare: \n" + this.displayLines());}
					int line2 = Integer.valueOf(input3).intValue();
					
					JOP.msg(this.lineCross(line1, line2));
				}
			}
		}
	}
//adds a new line to the list of lines in the form ax + by + c = 0.
	private void addLineABC(double a, double b, double c) {
		this.a.add(a);
		this.b.add(b);
		this.c.add(c);
	}
//adds a new line to the list of lines in the form y = mx + b.
	private void addLineMB(double m, double b) {
		this.a.add(-1 * m);
		this.b.add(1.0);
		this.c.add(-1 * b);
	}
//displays each line in the list in the form ax + by + c = 0 and y = mx + b.
	private String displayLines() {
		String s = "";
		for(int i = 0; i < this.a.size(); i++) {
			s += "Line " + i + ": " + this.sLineABC(i) + " or " + this.sLineMB(i) + "\n";
		}
		return s;
	}
//creates a String to display line in the form ax + by + c = 0.
	private String sLineABC(int i) {
		return this.a.get(i) + "x + " + this.b.get(i) + "y + " + this.c.get(i) + " = 0";
	}
//creates a String to display line in the form y = mx + b.
	private String sLineMB(int i) {
		return (-1 * this.a.get(i) / this.b.get(i)) + "x + " + (-1 * this.c.get(i) / this.b.get(i)) + " = y";
	}
//creates String to display information about a line on the list.
	private String printInfo(int i) {
		return "Line " + i + ": y-intercept: " + (-1 * this.c.get(i) / this.b.get(i)) + " x-intercept: " + (-1 * this.c.get(i) / this.a.get(i)) + " slope: " + (-1 * this.a.get(i) / this.b.get(i));
	}
//checks to see if the String parameter is in proper point notation.
	private boolean isPoint(String point) {
		if(!(point.indexOf("(") >= 0 && point.indexOf(")") >= 0 && point.indexOf(",") >= 0)) {
			return false;
		}
		else if(!(point.substring(0, 1).equals("(") && JOP.isDouble(point.substring(1, point.indexOf(","))) && JOP.isDouble(point.substring(point.indexOf(",") + 1, point.length() - 1)) && point.substring(point.length() - 1, point.length()).equals(")"))) {
			return false;
		}
		return true;
	}
//checks to see if the point described by x and y is on, below, or above the line described by the int line.
	private String pointLineData(int line, double x, double y) {
		if(y == (-1 * this.a.get(line) / this.b.get(line)) * x + (-1 * this.c.get(line) / this.b.get(line))) {
			return "The point (" + x + "," + y + ") is on the line "  + this.a.get(line) + "x + " + this.b.get(line) + "y + " + this.c.get(line) + " = 0 ";
		}
		else if(y > (-1 * this.a.get(line) / this.b.get(line)) * x + (-1 * this.c.get(line) / this.b.get(line))) {
			return "The point (" + x + "," + y + ") is above the line "  + this.a.get(line) + "x + " + this.b.get(line) + "y + " + this.c.get(line) + " = 0 ";
		}
		else {
			return "The point (" + x + "," + y + ") is below the line "  + this.a.get(line) + "x + " + this.b.get(line) + "y + " + this.c.get(line) + " = 0 ";
		}
	}
//checks to see if the 2 lines cross, are the same, or parallel.
	private String lineCross(int line1, int line2) {
		if((-1 * this.a.get(line1) / this.b.get(line1)) == (-1 * this.a.get(line2) / this.b.get(line2))) {
			if((-1 * this.c.get(line1) / this.b.get(line1)) == (-1 * this.c.get(line2) / this.b.get(line2))) {
				return "The lines intersect at every point all solutions: ";
			}
			return "The lines never intersect no solution: ";
		}
		else {
			return "The lines intersect at one point (" + ((this.c.get(line1) * this.b.get(line2) - this.c.get(line2) * this.b.get(line1)) / (this.a.get(line2) * this.b.get(line1) - this.a.get(line1) * this.b.get(line2))) + ", " + ((-1 * this.a.get(line1) / this.b.get(line1)) * ((this.c.get(line1) * this.b.get(line2) - this.c.get(line2) * this.b.get(line1)) / (this.a.get(line2) * this.b.get(line1) - this.a.get(line1) * this.b.get(line2))) - this.c.get(line1) / this.b.get(line1)) + ") and is the only solution: ";
		}
	}
}
