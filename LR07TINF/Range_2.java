import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class Range_2 {
	 public static void main(String[] args) throws FileNotFoundException {
		 Scanner in = new Scanner(System.in);
		 System.out.println("Введите строку символов: ");
		 String s = in.next();
		 PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // Вывод в файл
		 printWriter.println("Интервальное кодирование ВТОРОЙ сп.:");
		 printWriter.println("Код: "+RangeCode2(s));
		 printWriter.println("Декодирование: "+RangeDecode2(RangeCode2(s),ABC));
		 System.out.println("Код: "+RangeCode2(s));
		 System.out.println("Декодирование: "+RangeDecode2(RangeCode2(s),ABC));
		 TestsRangeCode2();
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
	 static ArrayList<Integer> RangeCode2(String str) {
		 //ArrayList<Character> abc = BuildABC(str);
		 StringBuffer codestr = new StringBuffer(BuildABC(str));
		 String codestrtemp = BuildABC(str);
//		 System.out.println(codestr);
		 ArrayList<Integer> code = new ArrayList<Integer>();
		 for (int i = 0; i < str.length(); i++) {
			 Character c = str.charAt(i);
			 codestrtemp = codestr.substring(codestr.lastIndexOf(c.toString())+1); // промежуток
			 int num = lenABC(codestrtemp);
			 code.add(i,num);
			 codestr.append(c);
			 //System.out.println(code);
//			 System.out.println("Промежуток: "+codestrtemp);
//			 System.out.println(codestr);
		 }
		 return code;
	 }
	 
	 static String RangeDecode2(ArrayList<Integer> code, String abc) {
		 //System.out.println("abc: "+ABC);
		 StringBuffer result = new StringBuffer(abc);
		 StringBuffer tempresult = new StringBuffer(abc);
		 for (int i = 0; i < code.size(); i++) {
			 if (code.get(i) == 0) {
				 result.append(result.charAt((result.length() - code.get(i))-1));
				 tempresult.append(result.charAt((result.length() - code.get(i))-1));
			 }	
			 else {
			 tempresult = tempresult.reverse();
			 int j = -1;
			 int count = 0;
			 //try {
			 int kolsymb = 0;
			 while (count <= code.get(i)) {
				 j++;
				 count = 0;
				 kolsymb = 0;
				 ArrayList<Character> abctemp = new ArrayList<Character>();
				 for (int o = 0; o < tempresult.substring(0,j).length(); o++) {
					 char c = tempresult.substring(0,j).charAt(o);
					 if (!abctemp.contains(c)) {
						 abctemp.add(c);
						 count++;
					 }
					 kolsymb++;
				 }
			 }
			 //System.out.println("Длина алфавита: " +lenABC(tempresult.substring(0, j))+" при j ="+ j);
			 kolsymb--;
			 //System.out.println(kolsymb);
			 char ch = tempresult.charAt(kolsymb);
			 //System.out.println("tempresult.substring(0, j): "+tempresult.substring(0, j));
			 result.append(ch);
			 //System.out.println("result: "+result);
			 tempresult = tempresult.reverse();
			 tempresult.append(ch);
			 //System.out.println("tempresult: "+tempresult);
			 //} catch(IndexOutOfBoundsException e) {}
			 }
		 }
		 ABC = "";
		 return tempresult.substring(abc.length()).toString();
	 }
	 //abcdabcddcba = 333333330246
	 //cabbbabbac = 0330031028
	 private static int lenABC(String str) {
		 ArrayList<Character> abctemp = new ArrayList<Character>();
		 int cou = 0;
		 for (int i = 0; i < str.length(); i++) {
			 char c = str.charAt(i);
			 if (!abctemp.contains(c)) {
				 abctemp.add(c);
				 cou++;
			 }
		 }
		 //System.out.print(abc);
		 return cou;
	 }
	//tests	 
	   static Boolean TestsRangeCode2() throws FileNotFoundException {
		   String str = "";
		    PrintWriter printtests = new PrintWriter("OUT.txt");
			Random randomstr = new Random();
			boolean istrue = false;
			for (int j = 0; j < 20; j++) {
				for (int i = 0; i < 28; i++) {
					str += (char)('a' + randomstr.nextInt(26));
				}
				printtests.println("Генерируемая строка " + str);
				printtests.println("Код: " + RangeCode2(str));
				printtests.println("Декод: " + RangeDecode2(RangeCode2(str),ABC));
				printtests.println();
				istrue = RangeDecode2(RangeCode2(str),ABC).equals(str);
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