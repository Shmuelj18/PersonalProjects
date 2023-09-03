/**
 * 
 */
package project;

import java.io.*;
import java.util.*;

import structure.*;

/**
 * @author shmueljacobsen
 *
 */
public class COSC602_P3_InfixToPostfix {

	public static boolean isInvalid = false;

	// Read file and evaluate line by line
	public static void test() {
		MyStack stack = new MyStack();
		MyQueue queue = new MyQueue();

		char currentChar;
		String expression;
		File file = new File("../COSC602_P3_InfixInput.txt");
		File output = new File("../COSC602_P3_PostfixOutput");
		
		try {
			FileWriter fw = new FileWriter(output);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
				expression = scanner.nextLine();
				if (expression.trim().length() == 0) 
				{
					continue;
				}
				System.out.println(expression);
				fw.write("\nOriginal expression: " + expression);

				// Check element and place into queue or stack
				for (int i = 0; i < expression.length(); i++) 
				{
					currentChar = expression.charAt(i);

					// Skips empty space
					if (currentChar == ' ') {
						continue;
					}

					// Parentheses
					if (currentChar == '(') {
						stack.push(currentChar);
					}

					else if (currentChar == ')') {
						for (int j = 0; j <= stack.size(); j++) {
							if (stack.isEmpty()) {
								isInvalid = true;
							} else if ((char) stack.top() == '(') {
								stack.pop();
								break;
							} else {
								queue.insertBack(stack.pop());
								j--;
							}
						}
					}

					if (isInvalid == true) {
						System.out.println("\nThis expression is invalid (a).");
						break;
					}

					// Adds numbers to queue
					else if (currentChar == '0' || currentChar == '1' || currentChar == '2' || currentChar == '3'
							|| currentChar == '4' || currentChar == '5' || currentChar == '6' || currentChar == '7'
							|| currentChar == '8' || currentChar == '9') {
						queue.insertBack(currentChar - '0');
					}

					// Adds operand to either stack or queue
					else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'
							|| currentChar == '%') {

						// If empty, add operand to stack
						if (stack.isEmpty()) {
							stack.push(currentChar);
						}

						// If char is priority and top of stack is priority, then pop all priority to
						// queue and add to queue, if not add to stack
						else if (currentChar == '*' || currentChar == '/' || currentChar == '%') {
							if (stack.top().equals('*') || stack.top().equals('/') || stack.top().equals('%')) {
								while (stack.top().equals('*') || stack.top().equals('/') || stack.top().equals('%')) {
									queue.insertBack(stack.pop());

								}
								queue.insertBack(currentChar);
							} else if (stack.top().equals('+') || stack.top().equals('-') || stack.top().equals('(')) {
								stack.push(currentChar);
							}
						}

						// If char is not priority, if top of stack is priority, pop to queue until top
						// is ( or empty and push
						else if (currentChar == '+' || currentChar == '-') {

							if (stack.top().equals('+') || stack.top().equals('-') || stack.top().equals('*')
									|| stack.top().equals('/') || stack.top().equals('%')) {
								queue.insertBack(stack.pop());
							}
							stack.push(currentChar);
						}
					}
				}
				while (!stack.isEmpty()) {
					if ((char)stack.top() == '(') {
						System.out.println("mismatch");
						isInvalid = true;
						break;
					}	
					queue.insertBack(stack.pop());
				}
				
				System.out.println(queue.postFix());
				
				if (isInvalid == true) {
					fw.write("\nThis expression is invalid.\n");
					System.out.println("This expression is invalid. (b)");
					queue.clear(); stack.clear();
					isInvalid = false;
					continue;
				}
				fw.write(queue.postFix());
				
				int queueSize = queue.size;
				for (int i = 0; i <= queueSize; i++) {
					System.out.println(queue.toString());
					System.out.println(stack.toString());
					String currentInQueue = String.valueOf(queue.front());
					if (currentInQueue.equals("0") || currentInQueue.equals("1") || currentInQueue.equals("2") || currentInQueue.equals("3") || currentInQueue.equals("4") 
							|| currentInQueue.equals("5") || currentInQueue.equals("6") || currentInQueue.equals("7") || currentInQueue.equals("8") || currentInQueue.equals("9")) {
						stack.push(queue.removeFront());
					}
					else if (currentInQueue.equals("+") || currentInQueue.equals("-") || currentInQueue.equals("*") || currentInQueue.equals("/") || currentInQueue.equals("%")) {

						System.out.println(currentInQueue);
						
						if (stack.isEmpty()) {
							isInvalid = true;
							continue;
						}
						int right = (int)stack.pop();
						if (stack.isEmpty()) {
							isInvalid = true;
							continue;
						}
						int left = (int)stack.pop();
						int answer;
						if (currentInQueue.equals("+")) {
							answer = left + right;
							queue.removeFront();
							stack.push(answer);
						}
						else if (currentInQueue.equals("-")) {
							answer = left - right;
							queue.removeFront();
							stack.push(answer);
						}
						else if (currentInQueue.equals("*")) {
							answer = left * right;
							queue.removeFront();
							stack.push(answer);
						}
						else if (currentInQueue.equals("/")) {
							answer = left / right;
							queue.removeFront();
							stack.push(answer);
						}
						else if (currentInQueue.equals("%")) {
							answer = left % right;
							queue.removeFront();
							stack.push(answer);
							
						}
						
					}
				}
				if (isInvalid == true) {
					fw.write("This expression is invalid.\n");
					System.out.println("This expression is invalid. (c)");
					continue;
				}
				int finalAnswer;
				if (stack.isEmpty()) {
					continue;
				}
				else finalAnswer = (int)stack.top();
				System.out.println(stack.top());
				fw.write("Final answer: " + finalAnswer + "\n");
				stack.clear();
				}
			scanner.close();
			fw.close();
			} catch (FileNotFoundException fnfe) {
			System.err.println("Input file" + file + "not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
