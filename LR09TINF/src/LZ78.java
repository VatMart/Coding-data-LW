
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class LZ78 {
	int vocabularySize;
	StringBuffer input;

	public LZ78(int vocabularySize, String input) {
		this.vocabularySize = vocabularySize;
		this.input = new StringBuffer(input);
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Тест: 1)Иванов_Иван_Иванович; 2) красная_краска; 3) абракадабра;
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		System.out.println("Введите строку символов: ");
		String s = in.next();
		LZ78 lz78 = new LZ78(16, s);
		printWriter = new PrintWriter(new File("OUT.txt"));
		printWriter.println("LZ78 ТЕСТЫ:");
		System.out.println("Код: ");
		printWriter.println("Код: ");
		ArrayList<LZ78CodeStructure> tr = lz78.LZ78Code();
		for (LZ78CodeStructure entry : tr) {
			entry.print();
			entry.printFile();
		}
		System.out.println("Декодирование: " + lz78.LZ78Decode(tr));
		printWriter.println("Декодирование: " + lz78.LZ78Decode(tr));
		lz78.TestsLZ78Code();
		in.close();
		printWriter.close();
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "ms");
	}

	public static PrintWriter printWriter; // Вывод в файл

	public ArrayList<LZ78CodeStructure> LZ78Code() {
		// String savedinput = input.toString();
		ArrayList<LZ78CodeStructure> code = new ArrayList<LZ78CodeStructure>();
		HashMap<String, Integer> vocabulary = new HashMap<String, Integer>();
		vocabulary.put("", 0);
		while (input.length() > 0) {
//			if (input.length() != 0)
			//buffer = FillBuffer(buffer);
//			System.out.println(buffer+ ":Буффер");
			StringBuffer seq = new StringBuffer(Character.toString(input.charAt(0)));
			input.deleteCharAt(0);
//			System.out.println(seq+ ":первый символ");
			if (seq.charAt(0) == 126) // ~ - конец строки
				break;
			if (vocabulary.containsKey(seq.toString())) {
				while (vocabulary.containsKey(seq.toString()) && input.length() > 0) {
					seq.append(input.charAt(0));
					input.deleteCharAt(0);
				}
				char symb = seq.charAt(seq.length() - 1);
//				if (input.length() == 0 && vocabulary.containsKey(seq.toString())) { // конец строки
//					int position = vocabulary.get(seq.substring(0, seq.length()-1).toString());
//					code.add(new LZ78CodeStructure(position, '\0'));
////					System.out.println("<" + 0 + "," + 0 + "," + charseq.charAt(0) + ">");
//					break;
//				}
				int position = vocabulary.get(seq.substring(0, seq.length()-1).toString());
//				System.out.println("<" + position + "," + quanSymbs + "," + symb + ">");
				code.add(new LZ78CodeStructure(position, symb));
				vocabulary.put(seq.toString(), Collections.max(vocabulary.values()) + 1);
				//vocabulary = TrimtoSizeVoc(vocabulary);
			} else {
//				System.out.println("<" + 0 + "," + 0 + "," + seq + ">");
				code.add(new LZ78CodeStructure(0, seq.charAt(0)));
				vocabulary.put(seq.toString(), Collections.max(vocabulary.values()) + 1);
				//vocabulary = TrimtoSizeVoc(vocabulary);
				// input.deleteCharAt(0);
			}
		}
		return code;
	}

	public String LZ78Decode(ArrayList<LZ78CodeStructure> code) {
		HashMap<Integer, String> vocabulary = new HashMap<Integer, String>(); // !!!ключ теперь позиция
		vocabulary.put(0, "");
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < code.size(); i++) {
			if (code.get(i).pos == 0) {
				vocabulary.put(i+1, Character.toString(code.get(i).symb));
				result.append(code.get(i).symb);
//				System.out.println("result" + result);
//				vocabulary = TrimtoSizeVoc(vocabulary);
			} else {
				StringBuffer charseq = new StringBuffer(vocabulary.get(code.get(i).pos) + Character.toString(code.get(i).symb));
				vocabulary.put(i+1, charseq.toString());
				result.append(charseq);
				//vocabulary = TrimtoSizeVoc(vocabulary);
			}
//			System.out.println("vocabulary" + vocabulary);
		}
		return result.toString();
	}

//	private StringBuffer TrimtoSizeVoc(StringBuffer stroke) { //ПЕРЕДЕЛАТЬ!!!
//		while (stroke.length() > vocabularySize) {
//			stroke = new StringBuffer(stroke.substring(1));
//		}
//		return stroke;
//	}

	// tests
	public Boolean TestsLZ78Code() throws FileNotFoundException {

		// PrintWriter printWriter = new PrintWriter(new
		// FileOutputStream("OUT.txt",true));
		Random randomstr = new Random();
		boolean istrue = false;
		for (int j = 0; j < 20; j++) {
			StringBuffer str = new StringBuffer("");
			for (int i = 0; i < 14; i++) {
				str.append((char) ('a' + randomstr.nextInt(13)));
			}
			printWriter.println("Генерируемая строка " + str);
			printWriter.println("Код: ");
			LZ78 lz78 = new LZ78(16, str.toString());
			ArrayList<LZ78CodeStructure> tr = lz78.LZ78Code();
			for (LZ78CodeStructure entry : tr) {
				entry.printFile();
			}
			istrue = (LZ78Decode(tr).substring(0,
					(LZ78Decode(tr).charAt(LZ78Decode(tr).length() - 1) == '\0' ? LZ78Decode(tr).length() - 1
							: LZ78Decode(tr).length()))).equals(str.toString());
			printWriter.println("Декод: " + LZ78Decode(tr));
			printWriter.println();
			if (istrue == false) {
				System.out.println("Ошибка при тестировании! Строка: " + str);
				System.out.println("Результаты в файле OUT.txt");
				printWriter.println("Ошибка при строке: " + str);
				printWriter.close();
				return false;
			}
		}
		System.out.println("Количество тестов 20. Результаты тестов: " + istrue);
		System.out.println("Результаты в файле OUT.txt");
		printWriter.println("Количество тестов 20. Результаты тестов: " + istrue);
		// printWriter.close();
		return istrue;
	}
}

class LZ78CodeStructure {
	int pos;
	char symb;

	public LZ78CodeStructure(int pos, char symb) {
		this.pos = pos;
		this.symb = symb;
	}

	public void print() {
		System.out.println("<" + pos + "," + symb + ">");
	}

	public void printFile() throws FileNotFoundException {
		// PrintWriter printWriter = new PrintWriter(new
		// FileOutputStream("OUT.txt",true));
		// printWriter.println();
		LZ78.printWriter.println("<" + pos + ","  + symb + ">");
		// LZ78.printWriter.close();
	}

}