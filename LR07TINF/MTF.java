import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MTF {
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner in = new Scanner(System.in);
		System.out.println("Введите строку символов: ");
		String s = in.next();
		
		PrintWriter printWriter = new PrintWriter(new File("OUT.txt")); // Вывод в файл
		printWriter.println("Коды MTF сп.:");
		MTFCode(s);
		printWriter.println("Код: "+MTFCode(s));
		printWriter.println("Декодирование: "+MTFDecodeHuf(strokecode,ABC));
		
		System.out.println("Код MTF: " + MTFCode(s));
		System.out.println("Декодирование MTF: " + MTFDecodeHuf(strokecode, ABC));
		printWriter.close();
		TestsMTFCode();
		in.close();
	}

	public static LinkedList<Character> ABC;
	public static HashMap<Character, String> CharCode;
	public static String strokecode;
	
	public static ArrayList<Integer> MTFCode(String stroke) throws FileNotFoundException {
		PrintWriter printer = new PrintWriter("OUT.txt");
		StringBuffer strbuf = new StringBuffer(stroke);
		LinkedList<Character> abc = new LinkedList<Character>(BuildABC(stroke));
		System.out.println("Первичный алфавит: " + abc);
		printer.println("Первичный алфавит: " + abc);
		ArrayList<Integer> indxcode = new ArrayList<Integer>();
		while (strbuf.length() > 0) {
			char c = strbuf.charAt(0);
			strbuf.deleteCharAt(0);
			indxcode.add(abc.indexOf(c));
			abc.remove(abc.indexOf(c));
			abc.add(0, c);
		}
		System.out.println("Новый алфавит: " + abc);
		printer.println("Новый алфавит: " + abc);
		ABC = abc;
		ArrayList<Character> abcarr = new ArrayList<Character>(abc);
		Collections.reverse(abcarr);
		TreeHuf treehuf = new TreeHuf(abcarr);
		HashMap<Character, String> chCode = new HashMap<Character, String>();
		for (int i = abcarr.size() - 1; i >= 0; i--) {
			chCode.put(abcarr.get(i), treehuf.BuildTreeHuf().get(abcarr.get(i)).Code);
		}
		CharCode = chCode;
		strbuf.append(stroke);
		System.out.println("Коды Хаффмана для букв: " + abc);
		printer.println("Коды Хаффмана для букв: " + abc);
		for (Map.Entry<Character, String> entry : chCode.entrySet()) {
			stroke = stroke.replaceAll(entry.getKey().toString(), entry.getValue());
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		strokecode = stroke;
		System.out.println("Encoded message to Huffman code: " + stroke);
		printer.println("Encoded message to Huffman code: " + stroke);
		printer.close();
		return indxcode;
	}

	public static String MTFDecodeHuf(String strcode, LinkedList<Character> abc) {
		ArrayList<Character> abcarr = new ArrayList<Character>(abc);
		Collections.reverse(abcarr);
		TreeHuf treehuf = new TreeHuf(abcarr);
		String result = treehuf.HuffmanDecode(strcode,treehuf.BuildTreeHuf());
		return result;
	}

	private static ArrayList<Character> BuildABC(String str) {
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
		// System.out.print(abc);
		return abc;
	}
	
	//tests	 
	   static Boolean TestsMTFCode() throws FileNotFoundException, InterruptedException {
		   String str = "";
		    PrintWriter printtests = new PrintWriter("OUT.txt");
			Random randomstr = new Random();
			boolean istrue = false;
			for (int j = 0; j < 20; j++) {
				for (int i = 0; i < 28; i++) {
					str += (char)('a' + randomstr.nextInt(26));
				}
				printtests.println("Генерируемая строка " + str);
				printtests.println("Код: " + MTFCode(str));
				printtests.println("Декод: " + MTFDecodeHuf(strokecode,ABC));
				printtests.println();
				istrue = MTFDecodeHuf(strokecode,ABC).equals(str);
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

class TreeHuf {
	ArrayList<Character> charabc;
	Map<Character, Node> charNodes = new HashMap<Character, Node>();
	ArrayDeque<Node> states = new ArrayDeque<Node>();

	public TreeHuf(ArrayList<Character> charabc) {
		this.charabc = charabc;
	}

	public Map<Character, Node> BuildTreeHuf() {
		// System.out.print(charabc);
		for (int i = 0; i < charabc.size(); i++) {
			LeafNode leafnode = new LeafNode(charabc.get(i));
			states.add(leafnode);
			charNodes.put(charabc.get(i), leafnode);
		}
		while (states.size() > 1) {
			Node first = states.poll();
			Node second = states.poll();
			InternalNode internalnode = new InternalNode(first, second);
			states.add(internalnode);
		}
		Node rootnode = states.poll(); // корневой узел
		if (charabc.size() == 1)
			rootnode.Code = "0";
		else
			rootnode.BuildCode("");
//		public TreeHuf(ArrayList<Character> charabc) {
//			this.charabc = charabc;
//		}
		return charNodes;
	}
	
	public String HuffmanDecode(String str, Map<Character, Node> mapin) {
		String result = "";
		while (!str.equals("")) {
			// System.out.println(str);
			for (Map.Entry<Character, Node> entry : mapin.entrySet()) {
				int j = mapin.get(entry.getKey()).Code.length();
				try {
					if ((mapin.get(entry.getKey()).Code).equals(str.substring(0, j))) {
						result += entry.getKey();
						str = str.substring(j);
						break;
					}
				} catch (IndexOutOfBoundsException e) {
				} // str.substring(0, j) может выйти за границы
			}
		}
		//System.out.println("Результат декодирования: " + result);
		return result;

	}

	class Node { // Узел
		String Code; // Код узла

		void BuildCode(String code) {
			this.Code = code;
		}
	}

	class LeafNode extends Node {
		char symb;

		public LeafNode(char symb) {
			this.symb = symb;
		}

		@Override
		void BuildCode(String code) {
			super.BuildCode(code);
		}
	}

	class InternalNode extends Node {
		Node leftChild;
		Node rightChild;

		public InternalNode(Node leftChild, Node rightChild) {
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		@Override
		void BuildCode(String code) {
			super.BuildCode(code);
			leftChild.BuildCode(code + "0");
			rightChild.BuildCode(code + "1");
		}
	}
}
