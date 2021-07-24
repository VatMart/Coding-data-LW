import java.util.ArrayList;
import java.util.Scanner;

public class Shennon {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 char [] inputs;
		 System.out.println("Введите через пробел длины кодовых слов: ");
		 inputs  = in.nextLine().toCharArray();
		 IntegerArray = CharArrtoDoubleArrayList(inputs);
		 toShennonCode(IntegerArray);
//		 ShennonDecode(toShennonCode(IntegerArray));
		 System.out.println("Введите количество генерируемых тестов");
		 testShennon(in.nextInt());
		 in.close();
	 }
	 static boolean isKraft(ArrayList<Integer> lengthArray) {
		Double sum = 0.;
		for (int i = 0; i < lengthArray.size(); i++)
		 sum += Math.pow(2, -lengthArray.get(i));
		if (sum <= 1)
		 return true;
		else return false;
	 }
	 
	 static boolean isASCSort(ArrayList<Integer> length) {
		 Integer temp = length.get(0);
		 for(int i = 1; i < length.size(); i++) {
			if (temp > length.get(i))
				return false;
			if (temp < length.get(i)) {
				temp = length.get(i);
			}
		 }
		return true;
	 }
	 
	 static String[] toShennonCode(ArrayList<Integer> lengthArr) {
		// Проверка длин на соответствие условиям
		if (isASCSort(lengthArr) == false) {
			System.out.println("Ошибка! Длины не упорядочены по убыванию!");
			return null;
		}
		if (isKraft(lengthArr) == false) {
			System.out.println("Ошибка! Неравенство Крафта не выполнено!");
			return null;
		}
		Double[] q = new Double[lengthArr.size()];
		String[] qbin = new String[lengthArr.size()];
		q[0] = 0.; //q0 = q1
		for (int i = 0; i < lengthArr.size(); i++) {
			if (i > 0)
			q[i] = q[i-1] + 1 / Math.pow(2,(lengthArr.get(i-1)));
			qbin[i] = DoubletoBin(q[i],lengthArr.get(i));
//			System.out.println(q[i]);
			System.out.println("v(" + (i+1) + ") = " + qbin[i]);
		}
		return qbin;
	 }
	 
	 static String DoubletoBin(Double value, int length) {
		 Double fractvalue = value - value.intValue();
		 int intvalue = value.intValue();
		 //int intvaluetemp = intvalue;
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
//			 if (fractvalue - fractvaluetemp == 0.)
//				 break;
		 }
		 //System.out.println(res);
		 return res;
	 }
	 
	 
	  static ArrayList<Integer> IntegerArray = new ArrayList<Integer>();
	  static ArrayList<Integer> CharArrtoDoubleArrayList(char[] ch) { // перевод из char[] в массив int
		 	ArrayList<Integer> IntegerArrayList = new ArrayList<Integer>();
		    String textgran = "";
		    int count1 = 0;
		    for (int i=0; i<ch.length; i++)
		    {
		    	if (ch[i] == ' ' && i != 0) 
		    	{
		    		IntegerArrayList.add(count1, Integer.parseInt(textgran));
		    		count1++;
		    		textgran = "";
		    	}
		    	else if ((((int)ch[i] >= 48) && ((int)ch[i] <= 57)) || (ch[i] == '.'))// если символ i пирнадлежит символам 1..9
		    	{
		    		textgran = textgran + ch[i];
		    		if (i == (ch.length-1)) IntegerArrayList.add(count1, Integer.parseInt(textgran));
		    	}
		    }
		    return IntegerArrayList;
	 }
	  //Метод для генерация псевдослучайного числа в заданном диапазоне
	  static int getRandomIntegerBetweenRange(double min, double max){
		    double x = (int)(Math.random()*((max-min)+1))+min;
		    return (int)x;
		}
	   static ArrayList<Integer> ShennonDecode(String[] qbin) {
		   ArrayList<Integer> lengthArr = new ArrayList<Integer>();
		   for (int i = 0; i < qbin.length; i++) {
			   lengthArr.add(qbin[i].length());
		   }
		   System.out.println(lengthArr);
		   return lengthArr;
	   }
// tests 	  
	   static ArrayList<Integer> fillArr(int sizearr) {
		   ArrayList<Integer> randArr = new ArrayList<Integer>();
		   for (int i = 0; i < sizearr; i++) {
			   randArr.add(getRandomIntegerBetweenRange(1, 8));
		   } 
		   randArr = bubbleSort(randArr);
		   System.out.println(randArr);
		   return randArr;
	   }
	   
	 static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
		    for(int i = arr.size()-1 ; i > 0 ; i--){
		        for(int j = 0 ; j < i ; j++){
		            if (arr.get(j) > arr.get(j+1) ){
		                int tmp = arr.get(j);
		                arr.set(j,arr.get(j+1));
		                arr.set(j+1,tmp);
		            }
		        }
		    }
		    return arr;
		}
	   
	   static void testShennon(int kol) {
		   for (int i = 0; i < kol; i++) {
			   toShennonCode(fillArr(getRandomIntegerBetweenRange(2, 7)));
		   }
	    }
	  
	  
} 