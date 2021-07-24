import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class Range_1 {
	 public static void main(String[] args) throws FileNotFoundException {
		 Scanner in = new Scanner(System.in);
		 System.out.println("Введите строку символов: ");
		 String s = in.next();
		 PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // Вывод в файл
		 printWriter.println("Интервальное кодирование ПЕРВЫЙ сп.:");
		 printWriter.println("Код: "+RangeCode1(s));
		 printWriter.println("Декодирование: "+RangeDecode1(RangeCode1(s),ABC));
		 System.out.println("Код: "+RangeCode1(s));
		 System.out.println("Декодирование: "+RangeDecode1(RangeCode1(s),ABC));
		 TestsRangeCode1() ;
		 in.close();
		 printWriter.close();
	 }
	 
	 private static String ABC;
	 private static String BuildABC(String str) {
		 ArrayList<Character> abc = new ArrayList<Character>();
		 for (int i = 0; i < str.length(); i++) {
			 char c = str.charAt(i);
			 if (!abc.contains(c))
				 abc.add(c);
		 }
		 Collections.sort(abc, new Comparator<Character>() {
				public int compare(Character o1, Character o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
		 //System.out.print(abc);
		 StringBuffer abcstr = new StringBuffer("");
		 for (int i = 0; i < abc.size(); i++) {
			 char c = abc.get(i);
			 abcstr.append(c);
		 }
		 ABC = abcstr.toString();
		 return ABC;
	 }
	 
	 static ArrayList<Integer> RangeCode1(String str) {
		 //ArrayList<Character> abc = BuildABC(str);
		 StringBuffer codestr = new StringBuffer(BuildABC(str));
		 //System.out.println(codestr);
		 ArrayList<Integer> code = new ArrayList<Integer>();
		 for (int i = 0; i < str.length(); i++) {
			 Character c = str.charAt(i);
			 int numb = (codestr.length())-(codestr.lastIndexOf(c.toString())+1);
			 code.add(i,numb);
			 codestr.append(c);
//			 System.out.println(code);
//			 System.out.println(codestr);
		 }
		 return code;
	 }
	 
	 static String RangeDecode1(ArrayList<Integer> code, String abc) {
		 StringBuffer result = new StringBuffer(abc);
		 for (int i = 0; i < code.size(); i++) {
			 result.append(result.charAt((result.length() - code.get(i))-1));
		 }
		 ABC = "";
		 return result.substring(abc.length()).toString();
	 }
	 //abcdabcddcba = 333333330246
	 //cabbbabbac = 0330031028
	//tests	 
	   static Boolean TestsRangeCode1() throws FileNotFoundException {
		   String str = "";
		    PrintWriter printtests = new PrintWriter("OUT.txt");
			Random randomstr = new Random();
			boolean istrue = false;
			for (int j = 0; j < 20; j++) {
				for (int i = 0; i < 28; i++) {
					str += (char)('a' + randomstr.nextInt(26));
				}
				printtests.println("Генерируемая строка " + str);
				printtests.println("Код: " + RangeCode1(str));
				printtests.println("Декод: " + RangeDecode1(RangeCode1(str),ABC));
				printtests.println();
				istrue = RangeDecode1(RangeCode1(str),ABC).equals(str);
				str = "";
				if (istrue == false) {
					System.out.println("Ошибка при тетстировании! Строка: " + str);
					System.out.println("Результаты в файле OUT.txt" );
					printtests.println("Ошибка при строке: " + str);
					printtests.close();
					return false;
				}
			}
			System.out.println("Количество тестов 20. Результаты тестов: " + istrue);
			System.out.println("Результаты в файле OUT.txt" );
			printtests.println("Количество тестов 20. Результаты тестов: " + istrue);
			printtests.close();
			 return istrue; 
		   }
	   }