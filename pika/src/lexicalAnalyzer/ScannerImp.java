package lexicalAnalyzer;

import inputHandler.PushbackCharStream;
import tokens.*;

public abstract class ScannerImp implements Scanner {
	private Token nextToken;
	protected final PushbackCharStream input;
	
	protected abstract Token findNextToken();

	public ScannerImp(PushbackCharStream input) {
		super();
		this.input = input;
		nextToken = findNextToken();
	}

	// Iterator<Token> implementation
	@Override
	public boolean hasNext() {
		return !(nextToken instanceof NullToken);
	}

	@Override
	public Token next() {
		Token result = nextToken;
		nextToken = findNextToken();
		return result;
	}
	
	public boolean isLiteral() {
		Token result = nextToken;
		if ((result instanceof IdentifierToken) || (result instanceof IntToken) || (result instanceof FloatToken) || (result instanceof StringToken) || (result instanceof CharacterToken)) {
			return true;
		}
		return false;
	}
	
	public boolean wasBracket() {
		Token result = nextToken;
		if (result.isLextant(Punctuator.CLOSE_BRACKET)) {
			return true;
		}
		return false;
	}
	
	public boolean wasIndex() {
		Token rb = nextToken;
		if (rb.isLextant(Punctuator.CLOSE_SQUARE_BRACKET)) {
			return true;
		}
		return false;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}