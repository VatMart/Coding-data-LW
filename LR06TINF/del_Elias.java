import java.util.ArrayList;
import java.util.Scanner;
public class del_Elias {
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
		 System.out.print("������� ����� ");
		 int num = in.nextInt();
		 System.out.println("���: "+del_EliasCode(num));
		 System.out.println("�������������: "+del_EliasDecode(del_EliasCode(num)));
		 Testsdel_EliasCode(); //
		 in.close();
	 }
	 
	 static String del_EliasCode(int innum) {
		 int lowbound = (int) Math.pow(2, 0), upbound = (int) Math.pow(2, 1)-1;
		 int k, count = 0;
		 if (innum == 0) {
			 System.out.println("��� ����� 0 �� ���������� ����");
			 return null;
		 }
		 while (!(innum >= lowbound && innum <= upbound)) {
			 count++;
			 lowbound = (int) Math.pow(2, count);
			 upbound = (int) Math.pow(2, count+1)-1;			 
		 }
		 k = count;
		 StringBuffer result = new StringBuffer(G_EliasCode(UnaryCode(k).length()));
		 result.append(IntToBin(innum).substring(1));
		 return result.toString();
	 }
	 
	 static int del_EliasDecode(String strcode) {
		 StringBuffer bufcode = new StringBuffer(strcode);
		 int result;
		 if (strcode == null) {
			 System.out.println("������! ���������� ������������ null!");
			 return -1;
		 }
		 StringBuffer strunary = new StringBuffer(bufcode.substring(0,bufcode.indexOf("1")+1));
		 bufcode = new StringBuffer(bufcode.substring(bufcode.indexOf("1")+1));
		 //System.out.println("������ ������� ��� " + bufcode);
		 int k = UnaryDecode(strunary.toString());
		 result = BinToInt(bufcode.substring(0,IntToBin((int) Math.pow(2, k)).length()-1)) + (int) Math.pow(2, k); // 
		 bufcode = new StringBuffer(bufcode.substring(IntToBin((int) Math.pow(2, k)).length()-1));
		 //System.out.println("������ ����� ��� " + bufcode);
		 k = --result;
		 result = BinToInt(bufcode.toString()) + (int) Math.pow(2, k);
		 return result;
	 }
	 
	 
	 static String G_EliasCode(int innum) {
		 int lowbound = (int) Math.pow(2, 0), upbound = (int) Math.pow(2, 1);
		 int k, count = 0;
		 if (innum == 0) {
			 System.out.println("��� ����� 0 �� ���������� ����");
			 return null;
		 }
		 while (!(innum >= lowbound && innum < upbound)) {
			 count++;
			 lowbound = (int) Math.pow(2, count);
			 upbound = (int) Math.pow(2, count+1);			 
		 }
		 k = count;
		 //System.out.println(k);
		 String result = IntToBin(innum).substring(1);
		 result = UnaryCode(k) + result;
		 return result;
	 }
	 
	 static int G_EliasDecode(String strcode) {
		 int result;
		 if (strcode == null) {
			 System.out.println("������! ���������� ������������ null!");
			 return -1;
		 }
		 String strunary = strcode.substring(0,strcode.indexOf('1')+1);
		 strcode = strcode.substring(strcode.indexOf('1')+1);
		 int k = UnaryDecode(strunary);
		 result = BinToInt(strcode) + (int) Math.pow(2, k);
		 return result;
	 }
	 
	 static String UnaryCode (int innum) {
		 String result = "";
		 if (innum == 0)
			 return "1";
		 for (int i = 0; i < innum; i++) {
			 result += "0";
		 }
		 result += "1";
		 return result;
	 }
	 
	 static int UnaryDecode (String strcode) {
		 int result = 0;
		 char c;
		 for (int i = 0; i < strcode.length(); i++) {
			 c = strcode.charAt(i);
			 if (c == '0') {
				 result++;
			 }
			 if (c == '1' && i != strcode.length()-1) {
				 System.out.println("������! ��� �� �������� �������");
				 return -1;
			 }	 
		 }
		 if (result == strcode.length()-1)
			 return result;
		 else {
			 System.out.println("������! ��� �� �������� �������");
			 return -1;
		 }
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
	 static void Testsdel_EliasCode() {
		    ArrayList<Boolean> tests = new ArrayList<Boolean>();
		    tests.add(0, null);
	 		for (int i = 1; i <= 128; i++) { 
	 			//System.out.println(i);
	 			tests.add(i,(i == (del_EliasDecode(del_EliasCode(i)))));
	 			//System.out.println(i + " = " + (del_EliasDecode(del_EliasCode(i))));
	 			if (tests.get(i) == false)
	 			{
	 				System.out.println("������ ��� �������� "+i);
	 				break;
	 			}
	 			if (i == 128)
	 				System.out.println("��� ����� ������� �������� "+ true);
	 		}
	 }
}