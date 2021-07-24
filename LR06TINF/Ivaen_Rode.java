import java.util.ArrayList;
import java.util.Scanner;
public class Ivaen_Rode {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число ");
		 int num = in.nextInt();
		 System.out.println("Код: "+RodeCode(num));
		 System.out.println("Декодирование: "+RodeDecode(RodeCode(num)));
		 TestsRodeCode(); //
		 in.close();
	 }
	 
	 static String RodeCode(int n) {
		 switch (n) {
		 case 0:
			 return "000";
		 case 1:
			 return "001";
		 case 2:
			 return "010";
		 case 3:
			 return "011";
		 }
		 return RodeCodeRes(n) + "0";
	 }
	 
	 static String RodeCodeRes(int n) {
		 if (n <= 3)
			 return "";
		 return RodeCodeRes(IntToBin(n).length())+IntToBin(n);
		 }
	 
	 static int RodeDecode(String code) {
		 switch (code) {
		 case "000":
			 return 0;
		 case "001":
			 return 1;
		 case "010":
			 return 2;
		 case "011":
			 return 3;
		 }
		 return RodeDecodeRes(code, 3);
	 }
	 
	 static int RodeDecodeRes(String code, int n) {
		 if (code.charAt(0) == '0')
			 return n;
		 return RodeDecodeRes(code.substring(n), BinToInt(code.substring(0,n)) );
		 }
	 
	 static String IntToBin(int value) {
		 int intvaluetemp = value;
		 String res = "";
		 if (value == 0) {
			 res = "0";
			 return res;
		 }
		 while (value != 0) {
			 intvaluetemp = value % 2;
			 res = intvaluetemp + res;
			 value = value / 2;
		 }
		 //System.out.println(res);
		 return res;
	 }
	 
	 static int BinToInt(String value) {
		 int result = 0;
		 for (int i = value.length()-1; i >= 0; i--) {
			char c = value.charAt(i);
			if (c == '1')
				result += Math.pow(2, value.length()-(i+1));
			//System.out.println(result);
		}
		 return result;
	 }
	 //tests
	 static void TestsRodeCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
		    //tests.add(0, null);
	 		for (int i = 0; i <= 128; i++) { 
	 			//System.out.println(i);
	 			tests.add(i,(i == (RodeDecode(RodeCode(i)))));
	 			//System.out.println(i + " = " + (del_EliasDecode(del_EliasCode(i))));
	 			if (tests.get(i) == false)
	 			{
	 				System.out.println("Ошибка при значении "+i);
	 				break;
	 			}
	 			if (i == 128)
	 				System.out.println("Все тесты вернули значение "+ true);
	 		}
	 }
}