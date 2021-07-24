package LZ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LZ77 {
	int vocabularySize;
	int bufferSize;
	StringBuffer input;

	public LZ77(int vocabularySize, int bufferSize, String input) {
		this.vocabularySize = vocabularySize;
		this.bufferSize = bufferSize;
		this.input = new StringBuffer(input);
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Тест: 1)Иванов_Иван_Иванович; 2) красная_краска; 3) абракадабра;
		long startTime = System.currentTimeMillis();
		Scanner in = new Scanner(System.in);
		System.out.println("Введите строку символов: ");
		String s = in.next();
		LZ77 lz77 = new LZ77(8, 5, s);
		printWriter = new PrintWriter(new File("OUT.txt"));
		printWriter.println("LZ77 ТЕСТЫ:");
		System.out.println("Код: ");
		printWriter.println("Код: ");
		ArrayList<LZCodeStructure> tr = lz77.LZ77Code();
		for (LZCodeStructure entry : tr) {
			entry.print();
			entry.printFile();
		}
		System.out.println("Декодирование: " + lz77.LZ77Decode(tr));
		printWriter.println("Декодирование: " + lz77.LZ77Decode(tr));
		lz77.TestsLZ77Code();
		in.close();
		printWriter.close();
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + "ms");
	}

	public static PrintWriter printWriter; // Вывод в файл

	public ArrayList<LZCodeStructure> LZ77Code() {
		// String savedinput = input.toString();
		ArrayList<LZCodeStructure> code = new ArrayList<LZCodeStructure>();
		StringBuffer vocabulary = new StringBuffer(initialVoc());
		StringBuffer buffer = new StringBuffer("");
		while (input.length() >= 0) {
//			if (input.length() != 0)
			buffer = FillBuffer(buffer);
//			System.out.println(buffer+ ":Буффер");
			char seq = buffer.charAt(0);
//			System.out.println(seq+ ":первый символ");
			if (seq == 126) // ~ - конец строки
				break;
			if (vocabulary.toString().contains(Character.toString(seq))) {
				StringBuffer charseq = new StringBuffer(Character.toString(seq));
				int i = 0;
				while (vocabulary.toString().contains(charseq.toString())) {
					i++;
					charseq.append(buffer.charAt(i));
				}
				char symb = charseq.charAt(charseq.length() - 1);
				charseq.deleteCharAt(charseq.length() - 1);
				if (symb == '~') { // конец строки
					int position = vocabulary.length() - vocabulary.lastIndexOf(charseq.toString());
					int quanSymbs = charseq.length();
					code.add(new LZCodeStructure(position, quanSymbs, '\0'));
//					System.out.println("<" + 0 + "," + 0 + "," + charseq.charAt(0) + ">");
					break;
				}
				int position = vocabulary.length() - vocabulary.lastIndexOf(charseq.toString());
				int quanSymbs = charseq.length();
//				System.out.println("<" + position + "," + quanSymbs + "," + symb + ">");
				code.add(new LZCodeStructure(position, quanSymbs, symb));

				vocabulary.append(charseq);
				vocabulary.append(symb);
				vocabulary = TrimtoSizeVoc(vocabulary);
				buffer.delete(0, charseq.length() + 1);
			} else {
//				System.out.println("<" + 0 + "," + 0 + "," + seq + ">");
				code.add(new LZCodeStructure(0, 0, seq));

				vocabulary.append(seq);
				vocabulary = TrimtoSizeVoc(vocabulary);
				buffer.deleteCharAt(0);
				// input.deleteCharAt(0);
			}
		}
		return code;
	}

	public String LZ77Decode(ArrayList<LZCodeStructure> code) {
		StringBuffer vocabulary = new StringBuffer(initialVoc());
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < code.size(); i++) {
			if (code.get(i).pos == 0 && code.get(i).quan == 0) {
				vocabulary.append(code.get(i).symb);
				result.append(code.get(i).symb);
//				System.out.println("result" + result);
				vocabulary = TrimtoSizeVoc(vocabulary);
			} else {
				StringBuffer charseq = new StringBuffer(vocabulary.substring((vocabulary.length()) - (code.get(i).pos),
						(vocabulary.length() - (code.get(i).pos)) + code.get(i).quan));
				vocabulary.append(charseq);
				vocabulary.append(code.get(i).symb);
				result.append(charseq);
				result.append(code.get(i).symb);
				vocabulary = TrimtoSizeVoc(vocabulary);
			}
//			System.out.println("vocabulary" + vocabulary);
		}
		return result.toString();
	}

	private StringBuffer TrimtoSizeVoc(StringBuffer stroke) {
		while (stroke.length() > vocabularySize) {
			stroke = new StringBuffer(stroke.substring(1));
		}
		return stroke;
	}

	private String initialVoc() {
		StringBuffer voc = new StringBuffer("");
		while (voc.length() < vocabularySize) {
			voc.append('~');
		}
		return voc.toString();
	}

	private StringBuffer FillBuffer(StringBuffer buffer) {
		while (buffer.length() < bufferSize) {
			if (input.length() == 0) {
				buffer.append('~'); // ascii code = 126
			} else {
				buffer.append(input.charAt(0));
				input.deleteCharAt(0);
			}
		}
		return buffer;
	}

	// tests
	public Boolean TestsLZ77Code() throws FileNotFoundException {

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
			LZ77 lz77 = new LZ77(8, 5, str.toString());
			ArrayList<LZCodeStructure> tr = lz77.LZ77Code();
			for (LZCodeStructure entry : tr) {
				entry.printFile();
			}
			istrue = (LZ77Decode(tr).substring(0,
					(LZ77Decode(tr).charAt(LZ77Decode(tr).length() - 1) == '\0' ? LZ77Decode(tr).length() - 1
							: LZ77Decode(tr).length()))).equals(str.toString());
			printWriter.println("Декод: " + LZ77Decode(tr));
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

class LZCodeStructure {
	int pos;
	int quan;
	char symb;

	public LZCodeStructure(int pos, int quan, char symb) {
		this.pos = pos;
		this.quan = quan;
		this.symb = symb;
	}

	public void print() {
		System.out.println("<" + pos + "," + quan + "," + symb + ">");
	}

	public void printFile() throws FileNotFoundException {
		// PrintWriter printWriter = new PrintWriter(new
		// FileOutputStream("OUT.txt",true));
		// printWriter.println();
		LZ77.printWriter.println("<" + pos + "," + quan + "," + symb + ">");
		// LZ77.printWriter.close();
	}

}