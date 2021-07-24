import java.util.ArrayList;
import java.util.Scanner;

public class Loewenstein {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("������� ����� ");
		 int num = in.nextInt();
		 System.out.println("���: "+LoewensteinCode(num));
		 System.out.println("�������������: "+LoewensteinDecode(LoewensteinCode(num)));
		 TestsLoewensteinCode();
		 in.close();
	 }
	 static String LoewensteinCode(int innum) {
		String binnum;
		int count = 1;
		int M = innum;
		String result = "";
		if (innum == 0) {
			//System.out.println("0");
			return "0";
		}
		do {
			binnum = IntToBin(M).substring(1);
			result = binnum + result;
			M = binnum.length();
			count++;
		} while (M != 0);
		result = "0" + result;
		for (int i = 1; i < count; i++) {
			result = "1" + result;
		}
		//System.out.println(result);
		 return result;
	 }
	 
	 static int LoewensteinDecode(String strcode) {
//		 System.out.println("����������� ���: "+strcode);
		char ch;
		int i = 0;
		int count = 0;
		do {
			ch = strcode.charAt(i);
			i++;
			if (ch == '1')
				count++;
		} while (ch != '0');
		if (count == 0)
			return 0;
		strcode = strcode.substring(count+1);
//		System.out.println("� = "+count);
//		System.out.println("������� ������� � ����: "+strcode);
		int N = 1, p = count - 1;
		String Z = strcode;
		while (p != 0) {
			Z = strcode.substring(0,N);
			strcode = strcode.substring(N);
//			System.out.println("������ ��� str "+strcode);
//			System.out.println("������ z "+Z);
			Z = "1" + Z;
			N = BinToInt(Z);
			p--;
		}
//		System.out.println("����� " + N);
		 return N; 
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
		 for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '1')
				result += Math.pow(2, value.length()-(i+1));
			//System.out.println(result);
		}
		 return result;
	 }
	 //tests
	 static void TestsLoewensteinCode() {
		    ArrayList<Boolean> tests = new ArrayList<>();
	 		for (int i = 0; i <= 50; i++) { 
	 			//System.out.println(i);
//	 			tests.add(0, null);
	 			tests.add(i,(i == (LoewensteinDecode(LoewensteinCode(i)))));
	 			//System.out.println(i + " = " + (LoewensteinDecode(LoewensteinCode(i))));
	 			if (tests.get(i) == false)
	 			{
	 				System.out.println("������ ��� �������� "+i);
	 				break;
	 			}
	 		}
	 		System.out.println("��� ����� ������� �������� "+ true);
	 }
}
	 