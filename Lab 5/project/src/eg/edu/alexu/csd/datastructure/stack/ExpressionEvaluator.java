package eg.edu.alexu.csd.datastructure.stack;

public class ExpressionEvaluator implements IExpressionEvaluator {
	static int compare(char onTop, char current) {
		if("*/".contains(onTop+"") && "+-".contains(current + ""))
			return 1;
		else if ( ("*/".contains(onTop+"") && "*/".contains(current+"")) 
				    || ("+-".contains(onTop+"") && "+-".contains(current+"")) )
			return 0;
		else
			return -1;
	}
	/**
	 * Checks if the string is valid infix
	 * @param s
	 * The infix string
	 * @return
	 * if valid return the string without spaces. if not return null
	 */
	static String check(String s) {
		// remove spaces
		String newS = "";
		int len = s.length();
		for(int i=0; i<len; i++) {
			if(s.charAt(i) != ' ')
				newS += s.charAt(i);
		}
		s = newS;
		len = s.length();
		String validInputs = "0123456789()*/+-ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		Stack stack = new Stack();
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			if( ! validInputs.contains( c + "" ) ) return null;
			// checking parentheses
			if(c == '(') stack.push('(');
			else if (c == ')') try {stack.pop();} catch (RuntimeException ex) {return null;}
		}
		if("*/+-".contains(s.charAt(len-1)+"") || stack.size() != 0) return null;
		// check if two operators are next to each other or letters
		for(int i=0; i<len-1; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i+1);
			// two operators
			if("+-*/(".contains(c1+"") && "+*/)".contains(c2+"") )
				return null;
			if("()".contains(c1+"") && "()".contains(c2+"") && c1!=c2)
				return null;
			// two letters
			if(letters.contains(c1+"") && letters.contains(c2+""))
				return null;
			// letter and a number
			if((letters.contains(c1+"") && numbers.contains(c2+"")) || (letters.contains(c2+"") && numbers.contains(c1+"")) )
				return null;
		}
		return s;
	}
	/**
	* Takes a symbolic/numeric infix expression as input and converts it to
	* postfix notation. There is no assumption on spaces between terms or the
	* length of the term (e.g., two digits symbolic or numeric term)
	*
	* @param expression
	* infix expression
	* @return postfix expression
	* @throws
	* RuntimeException if the infix is invalid
	*/
	public String infixToPostfix(String expression) {
		expression = check(expression);
		if(expression == null) throw new RuntimeException("Invalid expression.");
		int len = expression.length();
		String postfix = "";
		Stack stack = new Stack();
		for(int i=0; i<len; i++) {
			char c = expression.charAt(i);
			if( ! "()*/+-".contains(c+"") ) {
				if(Character.isLetter(c)) {
					postfix += c + " ";
					continue;
				}
				while(!"()*/+-".contains(c+"")) { // will break if has operator of at end of expression
					postfix += c;
					if(i<len-1) {
						i++;
						c = expression.charAt(i);
					}else 
						break;
				}
				postfix += " ";
				if( i == len-1) {
					if(expression.charAt(i) == ')') {
						c = (char) stack.pop();
						while(c != '(') {
							postfix += c + " ";
							c = (char) stack.pop();
						}
					}
					break;
				}
			}
			if(c == '(') 
				stack.push('(');
			else if(c == ')') {
				c = (char) stack.pop();
				while(c != '(') {
					postfix += c + " ";
					c = (char) stack.pop();
				}
			}else if(c=='-' && "(*/+-".contains(expression.charAt(i-1)+"")) {
				postfix += "0 ";
				stack.push('-');
			}else if (i<len) {
				if ( !stack.isEmpty() && (char)stack.peek() != '(') {
					int compareValue = compare((char)stack.peek(),c);
					if (compareValue == -1) {
						stack.push(c);
					}
					while(compareValue != -1) {
						if(compareValue == 1) {
							postfix += (char)stack.pop() + " ";
							if(!stack.isEmpty()) {compareValue = compare((char)stack.peek(),c); continue;}
							else stack.push(c);
						} else if (compareValue == 0) {
							postfix += (char)stack.pop() + " ";
							stack.push(c);
						}
						break;
					}
				}else
					stack.push(c);
			}
		}
		if( ! stack.isEmpty()) postfix += (char)stack.pop();
		while(!stack.isEmpty())
			postfix += " " + (char)stack.pop();
		return postfix;
	}
	/**
	* Evaluate a postfix numeric expression, with a single space separator
	*
	* @param expression
	* postfix expression
	* @return 
	* the expression evaluated value
	* @throws
	* RuntimeException if the postfix is invalid
	*/
	public int evaluate(String expression) {
		Stack stack = new Stack();
		int len = expression.length();
		for(int i=0; i<len; i++) {
			char c = expression.charAt(i);
			if(c == ' ') continue;
			if( ! "*/+-".contains(c+"") ) {
				String num = "";
				while(!"*/+- ".contains(c+"")) {
					num += c;
					if(i<len-1) {
						i++;
						c = expression.charAt(i);
					}else
						break;
				}
				stack.push(Float.parseFloat(num));
			} else {
				float n2 = (float) stack.pop();
				float n1 = (float) stack.pop();
				switch(c) {
				case '+':
					stack.push(n1+n2);
					break;
				case '-':
					stack.push(n1-n2);
					break;
				case '*':
					stack.push(n1*n2);
					break;
				case '/':
					stack.push(n1/n2);
				}
			}
		}
		float temp = (float)stack.pop();
		return (int)( temp<0? temp-0.5 : temp+0.5 );
	}
}
