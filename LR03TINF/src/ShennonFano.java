import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ShennonFano {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите строку символов: ");
		 String str = in.next();
		 
		 ShennonFanoDecode(toShennonFanoCode(str),chcode);
		 System.out.println("Введите длину генерируемых строки: ");
		 System.out.println(testsShennonFano(in.nextInt()));
		 in.close();
	 }
	 
	 public static <K, V extends Comparable<? super V>> Map<K, V> 
	    sortByValue(Map<K, V> map )
	{
	    List<Map.Entry<K, V>> list =
	        new LinkedList<Map.Entry<K, V>>(map.entrySet());
	    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
	    {
	        @Override
	        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
	        {
	            return (o2.getValue()).compareTo( o1.getValue() );
	        }
	    } );

	    Map<K, V> result = new LinkedHashMap<K, V>();
	    for (Map.Entry<K, V> entry : list)
	    {
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	}
	 
	 static Map<Character, String> chcode;
	 
	 static String toShennonFanoCode(String str) {
		 Map<Character, Double> chint = new LinkedHashMap<Character, Double>();
		 for (int i = 0; i < str.length(); i++) {
			 char c = str.charAt(i);
			 if (chint.containsKey(c))
			 chint.put(c, chint.get(c) + 1/(double)str.length());
			 else chint.put(c,  1/(double)str.length());
		 }
		 chint = sortByValue(chint); //

//		 for (Entry<Character, Double> entry : chint.entrySet())
//			 System.out.println(entry.getKey() + ": " + entry.getValue());

		 Map<Integer, Double> p = new LinkedHashMap<Integer, Double>();
		 Map<Integer, Character> tempp = new LinkedHashMap<Integer, Character>();
		 int count = 0;
		 for (Map.Entry<Character, Double> entry : chint.entrySet()) {
			 p.put(count, entry.getValue());
			 tempp.put(count, entry.getKey());
			 count++;
		 }
		 int k = chint.size();
		 Double[] q = new Double[k];
		 String[] qbin = new String[k];
		 int[] length = new int[k];
		 q[0] = 0.;
		 System.out.println("Символ:  Коды:      Частоты:  ");
		 for (int i = 0; i < k; i++) {
			 if (i > 0)
			 q[i] = q[i-1] + p.get(i-1);
			 length[i] = (((int)((-Math.log(p.get(i)) / Math.log(2))) -((-Math.log(p.get(i)) / Math.log(2))) == 0) ? (int)((-Math.log(p.get(i)) / Math.log(2))) : (int)((-Math.log(p.get(i)) / Math.log(2)))+1 );
			 qbin[i] = DoubletoBin(q[i],length[i]);
			 System.out.printf("%-9c%-11s%2.3f\n",tempp.get(i),qbin[i],p.get(i));
		 }
		 count = 0;
		 chcode = new LinkedHashMap<Character, String>();
		 for (Map.Entry<Character, Double> entry : chint.entrySet()) {
			 chcode.put(entry.getKey(), qbin[count]);
			 count++;
		 }
		 String result = "";	
		 for (int i = 0; i < str.length(); i++) {
			 char c = str.charAt(i);
			 if (chcode.containsKey(c)) {
				 result += chcode.get(c);
			 }
		 }
		 //String bin = String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0');
		 System.out.println("Результат кодирования: " + result);
		return result;
	 }
	 
	 static String ShennonFanoDecode(String str, Map<Character, String> alf) {
		 String result = "";
		 while (!str.equals("")) {
			 //System.out.println(str);
			 for (Map.Entry<Character, String> entry : alf.entrySet()) {
				 int j = entry.getValue().length();
				 try {
				 if (entry.getValue().equals(str.substring(0, j))) {
					 result += entry.getKey();
					 str = str.substring(j);
					 break;
				 }
				 } catch (IndexOutOfBoundsException e) {}//str.substring(0, j) может выйти за границы
			 }
		 }
			 chcode.clear();
		System.out.println("Результат декодирования: " + result);
		return result;
	 }
	 
	 static String DoubletoBin(Double value, int length) {
		 Double fractvalue = value - value.intValue();
		 int intvalue = value.intValue();
		 int intvaluetemp = intvalue;
		 String res = "";
//		 if (intvalue == 0)
//			 res = "0";
//		 while (intvalue != 0) {
//			 intvaluetemp = intvalue % 2;
//			 res = intvaluetemp + res;
//			 intvalue = intvalue / 2;
//		 }
//		 res = res + ".";
		 for (int i = 0; i < length; i++) {
			 fractvalue = fractvalue*2;
			 int fractvaluetemp = fractvalue.intValue();
			 res = res + fractvaluetemp;
			 fractvalue =  fractvalue - fractvaluetemp;
		 }
		 //System.out.println(res);
		 return res;
	 }
//tests	 
	   static Boolean testsShennonFano(int n) {
		   String str = "";
			Random randomstr = new Random();
			boolean istrue = false;
			for (int j = 0; j < 20; j++) {
			for (int i = 0; i < n; i++) {
				str += (char)('a' + randomstr.nextInt(26));
			}
			System.out.println("Генерируемая строка " + str);
			istrue = ShennonFanoDecode(toShennonFanoCode(str),chcode).equals(str);
			str = "";
			if (istrue == false) {
				System.out.print("Ошибка при строке: " + str);
				return false;
			}
			}
			System.out.print("Количество тестов 20. Результаты тестов: ");
			 return istrue; 
		   }
	   }

