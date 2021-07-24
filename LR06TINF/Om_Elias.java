import java.util.ArrayList;
import java.util.Scanner;

public class Om_Elias {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Введите число ");
		 int num = in.nextInt();
		 //System.out.println("Код: "+Om_EliasCode(num));
		 System.out.println("Код: "+RecOm_EliasCode(num));
		 System.out.println("Декодирование: "+Om_EliasDecode(Om_EliasCode(num)));
		 TestsOm_EliasCode();
		 in.close();
	 }
	 
	 static String Om_EliasCode(int innum) {
		 StringBuffer codebuf = new StringBuffer("0");
		 if (innum == 0) {
			 System.out.println("Для числа 0 не существует кода");
			 return null;
		 }
		 if (innum == 1) {
			 return codebuf.toString();
		 }
		 StringBuffer tempbuf = new StringBuffer(IntToBin(innum));
		 codebuf.insert(0, tempbuf);
		 innum = tempbuf.length()-1;
		 while (BinToInt(tempbuf.toString()) != 2) {
			 if (innum == 1) {
				 break;
			 }	 
			 tempbuf = new StringBuffer(IntToBin(innum));
			 codebuf.insert(0, tempbuf);
			 //System.out.println(codebuf.toString());
			 innum = tempbuf.length()-1;
		 }
		 //System.out.println(codebuf.toString());
		 return codebuf.toString();
	 }
	// Рекурсионная версия 
	 static String RecOm_EliasCode(int innum) {
		 StringBuffer codebuf = new StringBuffer("0");
		 if (innum == 0) {
			 System.out.println("Для числа 0 не существует кода");
			 return null;
		 }
		 if (innum == 1) {
			 return "0";
		 }
		 StringBuffer tempbuf = new StringBuffer(IntToBin(innum));
		 //System.out.println(codebuf.toString());
		 //RecOm_EliasCode(tempbuf.length()-1);
		 codebuf.insert(0, tempbuf);
		 innum = tempbuf.length()-1;
		 return codebuf.insert(0, RecOm_EliasCodeRes(innum)).toString();
	 }
	 static String RecOm_EliasCodeRes(int innum) {
		 StringBuffer tempbuf = new StringBuffer(IntToBin(innum));
		 StringBuffer codebuf = new StringBuffer(tempbuf);
		 if (innum == 1) {
			 return "";
		 }
		 innum = tempbuf.length()-1;
		 return codebuf.insert(0, RecOm_EliasCodeRes(innum)).toString();
	 }
	 
	 static int Om_EliasDecode(String strcode) {
		 int N = 1;
		 String temp;
		 if (strcode.equals("0"))
			 return 1;
		 if (strcode.charAt(0) == '1') {
			try {
			 do {
				 temp = strcode.substring(0, N+1);
				 //System.out.println("temp = "+temp);
				 strcode = strcode.substring(N+1);
				 N = BinToInt(temp);
			 } while (!temp.equals("0"));
			} catch (IndexOutOfBoundsException e) {}
			 return N;
		 }
		 else
			 return -1;
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
	 static void TestsOm_EliasCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
		    tests.add(0, null);
	 		for (int i = 1; i <= 128; i++) { 
	 			//System.out.println(i);
	 			tests.add(i,(i == (Om_EliasDecode(RecOm_EliasCode(i)))));
	 			//System.out.println(i + " = " + (G_LoewensteinDecode(G_LoewensteinCode(i))));
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