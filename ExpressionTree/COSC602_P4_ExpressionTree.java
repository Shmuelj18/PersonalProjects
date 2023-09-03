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
public class COSC602_P4_ExpressionTree {
	public static boolean isInvalid = false;

	public static void test() {
		MyStack stack = new MyStack();
		MyDeque deque = new MyDeque();

		char currentChar;
		String expression;
		File file = new File("../COSC602_P4_ExpressionInput.txt");
		File output = new File("../COSC602_P4_ExpressionOutput.txt");
		try 
		{
			FileWriter fw = new FileWriter(output);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
				expression = scanner.nextLine();
				deque.clear();
				if (expression.trim().length() == 0) 
				{
					continue;
				}
				System.out.println(expression);
				fw.write("\nOriginal infix: " + expression + "\n");

				// Check element and place into queue or stack
				for (int i = 0; i < expression.length(); i++) 
				{
					currentChar = expression.charAt(i);

					// Skips empty space
					if (currentChar == ' ') 
					{
						continue;
					}

					// Parentheses
					if (currentChar == '(') 
					{
						stack.push(currentChar);
					} 
					else if (currentChar == ')') 
					{
						for (int j = 0; j <= stack.size(); j++) {
							if (stack.isEmpty()) {
								isInvalid = true;
							} else if ((char) stack.top() == '(') {
								stack.pop();
								break;
							} else {
								MyBinaryTreeNode temp = new MyBinaryTreeNode(stack.pop());
								if (deque.isEmpty() || deque.head == deque.tail) {
									isInvalid = true;
									break;
								}
								else {
									temp.right = deque.removeBack();
									temp.left = deque.removeBack();
									deque.append(temp);
								}
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
						deque.insertBack(new MyBinaryTreeNode(currentChar));
					}

					// Adds operand to either stack or queue
					else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/'
							|| currentChar == '%') 
					{

						// If empty, add operand to stack
						if (stack.isEmpty()) {
							stack.push(currentChar);
						}

						// If char is priority and top of stack is priority, then pop all priority to
						// Queue and add to queue, if not add to stack
						else if (currentChar == '*' || currentChar == '/' || currentChar == '%') {
							if (stack.top().equals('*') || stack.top().equals('/') || stack.top().equals('%')) 
							{
								while (stack.top().equals('*') || stack.top().equals('/') || stack.top().equals('%')) {
									MyBinaryTreeNode temp = new MyBinaryTreeNode(stack.pop());
									if (deque.isEmpty() || deque.head == deque.tail) {
										isInvalid = true;
										break;
									}
									else {
										temp.right = deque.removeBack();
										temp.left = deque.removeBack();
										deque.append(temp);
									}
								}
								stack.push(currentChar);
							} 
							else if (stack.top().equals('+') || stack.top().equals('-') || stack.top().equals('(')) {
								stack.push(currentChar);
							}
						}

						// If char is not priority, if top of stack is priority, pop to queue until top
						// Is '(' or empty and push
						else if (currentChar == '+' || currentChar == '-') 
						{
							if (stack.top().equals('+') || stack.top().equals('-') || stack.top().equals('*')
									|| stack.top().equals('/') || stack.top().equals('%')) 
							{
								MyBinaryTreeNode temp = new MyBinaryTreeNode(stack.pop());
								if (deque.isEmpty() || deque.head == deque.tail) {
									isInvalid = true;
									break;
								}
								else {
									temp.right = deque.removeBack();
									temp.left = deque.removeBack();
									deque.append(temp);
								}
							}
							stack.push(currentChar);
						}
					}
				}

				
				while (!stack.isEmpty()) 
				{
					if ((char) stack.top() == '(') {
						System.out.println("mismatch");
						isInvalid = true;
						break;
					}
					MyBinaryTreeNode temp = new MyBinaryTreeNode(stack.pop());
					if (deque.isEmpty() || deque.head == deque.tail) {
						isInvalid = true;
						break;
					}
					else {
						temp.right = deque.removeBack();
						temp.left = deque.removeBack();
						deque.append(temp);
					}
				}

				System.out.println(deque);
				
				if (deque.head != deque.tail) {
					isInvalid = true;
				}

				if (isInvalid == true) 
				{
					fw.write("This expression is invalid.\n");
					System.out.println("This expression is invalid. (b)");
					deque.clear();
					stack.clear();
					isInvalid = false;
					continue;
				}
				   
				MyExpressionTree root = new MyExpressionTree(deque.front());
				fw.write("Preorder Traversal: " + root.preorderTraversal());
				System.out.println(root.preorderTraversal());
				fw.write("\nInorder Traversal: " + root.inorderTraversal());
				System.out.println(root.inorderTraversal());
				fw.write("\nPostorder Traversal: " + root.postorderTraversal());
				System.out.println(root.postorderTraversal());
				fw.write("\nEvaluation Result: " + root.evaluate(root.root) + "\n");
				System.out.println(root.evaluate(root.root));
				System.out.println(stack.toString());
				System.out.println(deque.toString());
				
				
				
				if (isInvalid == true) 
				{
					fw.write("This expression is invalid.\n");
					System.out.println("This expression is invalid. (c)");
					continue;
				}
				
				int finalAnswer;
				if (stack.isEmpty()) 
				{
					continue;
				} 
				else finalAnswer = (int) stack.top();
				
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
