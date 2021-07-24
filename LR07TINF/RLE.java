import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RLE {
	public static void main(String[] args) throws FileNotFoundException {
		//nnnnmmmmsdaaaaddd
		Scanner in = new Scanner(System.in);
		System.out.println("������� ������ ��������: ");
		String s = in.next();
		
		PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // ����� � ����
		printWriter.println("������������ ����������� ������ ��.:");
		printWriter.println("���: ");
		RLECode(s).printFile();
		printWriter.println("�������������: "+RLEDecode(RLECode(s)));
		
		System.out.print("���: ");
		RLECode(s).print();
		RLECode(s).printHex();
		RLECode(s).printBin();
		System.out.println("�������������: "+RLEDecode(RLECode(s)));
		TestsRLECode();
		in.close();
		printWriter.close();
	}

	public static CharInt RLECode(String stroke) {
		int i = 1;
		char current = stroke.charAt(0);
		int count = 1;
		CharInt rlecode = new CharInt();
		if (stroke.length() == 1) {
			rlecode.c.add(current);
			rlecode.kol.add(count);
		}
		while (i < stroke.length()) {
			if (current == stroke.charAt(i)) {
				count++;
				if (i == stroke.length() - 1) {
					rlecode.c.add(current);
					rlecode.kol.add(count);
				}
			} else {
				rlecode.c.add(current);
				rlecode.kol.add(count);
				current = stroke.charAt(i);
				count = 1;
				if (i == stroke.length() - 1) {
					rlecode.c.add(current);
					rlecode.kol.add(count);
				}
			  }
			i++;
		}
		return rlecode;
	}

	public static String RLEDecode(CharInt code) {
		StringBuffer stroke = new StringBuffer("");
		for (int e = 0; e < code.c.size(); e++) {
			int count = 0;
			while (count < code.kol.get(e)) {
				stroke.append(code.c.get(e));
				count++;
			}
		}
		return stroke.toString();
	}
	//tests	 
	   static Boolean TestsRLECode() throws FileNotFoundException {
		    
		    PrintWriter printtests = new PrintWriter("OUT.txt");
			Random randomstr = new Random();
			boolean istrue = false;
			for (int j = 0; j < 20; j++) {
				StringBuffer str = new StringBuffer("");
				for (int i = 0; i < 14; i++) {
					str.append((char)('a' + randomstr.nextInt(13)));
				}
				printtests.println("������������ ������ " + str);
				printtests.println("���: ");
				RLECode(str.toString()).printFile();
				printtests.println("�����: " + RLEDecode(RLECode(str.toString())));
				printtests.println();
				istrue = RLEDecode(RLECode(str.toString())).equals(str.toString());
				if (istrue == false) {
					System.out.println("������ ��� ������������! ������: " + str);
					System.out.println("���������� � ����� OUT.txt" );
					printtests.println("������ ��� ������: " + str);
					printtests.close();
					return false;
				}
			}
			System.out.println("���������� ������ 20. ���������� ������: " + istrue);
			System.out.println("���������� � ����� OUT.txt" );
			printtests.println("���������� ������ 20. ���������� ������: " + istrue);
			printtests.close();
			 return istrue; 
		   }
}

class CharInt {
	ArrayList<Character> c = new ArrayList<Character>();
	ArrayList<Integer> kol = new ArrayList<Integer>();

	void print() {
		for (int e = 0; e < c.size(); e++) {
			System.out.print("(" + kol.get(e) + "," + c.get(e) + ") ");
		}
		System.out.println();
	}
	void printHex() {
		for (int e = 0; e < c.size(); e++) {
			System.out.print("(" + Integer.toHexString(kol.get(e)) + "," + Integer.toHexString(c.get(e)) + ") ");
		}
		System.out.println();
	}
	void printBin() {
		for (int e = 0; e < c.size(); e++) {
			System.out.print("(" + Integer.toBinaryString(kol.get(e)) + "," + Integer.toBinaryString(c.get(e)) + ") ");
		}
		System.out.println();
	}
	void printFile() throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter("OUT.txt");
		for (int e = 0; e < c.size(); e++) {
			printWriter.print("(" + kol.get(e) + "," + c.get(e) + ") ");
		}
		printWriter.println();
		printWriter.close();
	}
}