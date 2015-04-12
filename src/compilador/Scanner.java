/* The following code was generated by JFlex 1.4.1 on 12/04/15 13:12 */

package compilador;

import java_cup.runtime.*;



/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 12/04/15 13:12 from the specification file
 * <tt>D:/TRABAJOS CON GIT/MODIFICACION DE COMPILADOR TINY/Tiny/especificacion_lexica/lexico.flex</tt>
 */
class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0, 13, 11,  0,  0, 12,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    13, 31,  0,  0,  0,  0,  0,  0, 38, 39, 36, 34, 43, 35,  0, 37, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 29, 42, 32, 30, 33,  0, 
     0, 24, 10, 10, 26, 10, 10, 10, 10, 10, 10, 10, 10, 10, 25, 27, 
    10, 10, 28, 10, 10, 10, 10, 10, 10, 10, 10, 40,  0, 41,  0,  0, 
     0,  7, 19, 10, 17,  5,  6, 21, 20, 16, 10, 10,  8, 10, 18, 15, 
    22, 10,  3,  9,  2,  4, 14, 23, 10, 10, 10, 44,  0, 45,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\6\3\1\4\1\1\1\5\6\3"+
    "\1\1\1\6\1\1\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\1"+
    "\10\3\1\4\1\3\1\23\5\3\1\24\1\25\1\26"+
    "\1\27\1\30\1\0\7\3\1\31\1\3\1\32\1\3"+
    "\1\33\3\3\1\34\1\35\1\36\1\37\1\3\1\40"+
    "\2\3\1\41\1\42\5\3\1\43\1\44\1\3\1\45"+
    "\1\46\1\47\1\3\1\50";

  private static int [] zzUnpackAction() {
    int [] result = new int[94];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\56\0\134\0\212\0\270\0\346\0\u0114\0\u0142"+
    "\0\u0170\0\u019e\0\u01cc\0\u01fa\0\u0228\0\u0256\0\u0284\0\u02b2"+
    "\0\u02e0\0\u030e\0\u033c\0\u036a\0\u0398\0\u03c6\0\56\0\56"+
    "\0\56\0\56\0\56\0\56\0\56\0\56\0\56\0\56"+
    "\0\56\0\u03f4\0\u0422\0\u0450\0\u047e\0\u04ac\0\u04da\0\u0508"+
    "\0\u0536\0\u0564\0\56\0\u0592\0\u0170\0\u05c0\0\u05ee\0\u061c"+
    "\0\u064a\0\u0678\0\u0170\0\56\0\56\0\56\0\56\0\u06a6"+
    "\0\u06d4\0\u0702\0\u0730\0\u075e\0\u078c\0\u07ba\0\u07e8\0\u0170"+
    "\0\u0816\0\u0170\0\u0844\0\u0170\0\u0872\0\u08a0\0\u08ce\0\u0170"+
    "\0\56\0\u0170\0\u0170\0\u08fc\0\u0170\0\u092a\0\u0958\0\u0170"+
    "\0\u0170\0\u0986\0\u09b4\0\u09e2\0\u0a10\0\u0a3e\0\u0170\0\u0170"+
    "\0\u0a6c\0\u0170\0\u0170\0\u0170\0\u0a9a\0\u0170";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[94];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\4\11"+
    "\1\12\1\13\1\14\1\15\1\11\1\16\2\11\1\17"+
    "\3\11\1\20\1\21\2\11\1\22\1\11\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\2\57\0"+
    "\1\3\56\0\1\11\1\43\7\11\3\0\6\11\1\44"+
    "\10\11\23\0\3\11\1\45\5\11\3\0\17\11\23\0"+
    "\11\11\3\0\4\11\1\46\12\11\23\0\6\11\1\47"+
    "\2\11\3\0\4\11\1\50\12\11\23\0\5\11\1\51"+
    "\3\11\3\0\1\11\1\52\15\11\23\0\11\11\3\0"+
    "\17\11\35\0\1\53\54\0\1\53\57\0\1\14\42\0"+
    "\11\11\3\0\1\11\1\54\15\11\23\0\4\11\1\55"+
    "\4\11\3\0\4\11\1\56\12\11\23\0\3\11\1\57"+
    "\5\11\3\0\1\11\1\60\15\11\23\0\1\11\1\61"+
    "\7\11\3\0\17\11\23\0\11\11\3\0\13\11\1\62"+
    "\3\11\23\0\11\11\3\0\16\11\1\63\57\0\1\64"+
    "\60\0\1\65\52\0\1\66\55\0\1\67\17\0\55\70"+
    "\3\0\2\11\1\71\6\11\3\0\17\11\23\0\3\11"+
    "\1\72\5\11\3\0\17\11\23\0\1\73\4\11\1\74"+
    "\3\11\3\0\10\11\1\75\6\11\23\0\1\76\10\11"+
    "\3\0\17\11\23\0\7\11\1\77\1\11\3\0\17\11"+
    "\23\0\11\11\3\0\3\11\1\100\13\11\23\0\6\11"+
    "\1\101\2\11\3\0\17\11\23\0\1\11\1\102\7\11"+
    "\3\0\17\11\23\0\11\11\3\0\2\11\1\103\14\11"+
    "\23\0\1\104\10\11\3\0\17\11\23\0\11\11\3\0"+
    "\7\11\1\105\7\11\23\0\11\11\3\0\1\11\1\106"+
    "\15\11\23\0\11\11\3\0\2\11\1\107\14\11\23\0"+
    "\11\11\3\0\14\11\1\110\2\11\21\0\55\70\1\111"+
    "\2\0\3\11\1\112\5\11\3\0\17\11\23\0\11\11"+
    "\3\0\4\11\1\113\12\11\23\0\2\11\1\114\6\11"+
    "\3\0\17\11\23\0\11\11\3\0\3\11\1\115\13\11"+
    "\23\0\3\11\1\116\5\11\3\0\17\11\23\0\11\11"+
    "\3\0\2\11\1\117\14\11\23\0\3\11\1\120\5\11"+
    "\3\0\17\11\23\0\7\11\1\71\1\11\3\0\17\11"+
    "\23\0\11\11\3\0\3\11\1\121\13\11\23\0\11\11"+
    "\3\0\2\11\1\122\14\11\23\0\6\11\1\123\2\11"+
    "\3\0\17\11\23\0\1\124\10\11\3\0\17\11\23\0"+
    "\1\11\1\125\7\11\3\0\17\11\23\0\5\11\1\126"+
    "\3\11\3\0\17\11\23\0\6\11\1\127\2\11\3\0"+
    "\17\11\23\0\11\11\3\0\4\11\1\130\12\11\23\0"+
    "\3\11\1\131\5\11\3\0\17\11\23\0\3\11\1\132"+
    "\5\11\3\0\17\11\23\0\11\11\3\0\4\11\1\133"+
    "\12\11\23\0\1\134\10\11\3\0\17\11\23\0\5\11"+
    "\1\135\3\11\3\0\17\11\23\0\11\11\3\0\4\11"+
    "\1\136\12\11\21\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2760];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\24\1\13\11\11\1\1\11\10\1\4\11"+
    "\1\0\20\1\1\11\25\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[94];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(r);
		this.sf=sf;
		lineanum=0;
		debug=true;
	}
	private SymbolFactory sf;
	private int lineanum;
	private boolean debug;



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 37: 
          { if(debug) System.out.println("token WRITE");
	return sf.newSymbol("WRITE",sym.WRITE);
          }
        case 41: break;
        case 9: 
          { if(debug) System.out.println("token PLUS");
	return sf.newSymbol("PLUS",sym.PLUS);
          }
        case 42: break;
        case 35: 
          { if(debug) System.out.println("token UNTIL");
	return sf.newSymbol("UNTIL",sym.UNTIL);
          }
        case 43: break;
        case 17: 
          { if(debug) System.out.println("token SEMI");
	return sf.newSymbol("SEMI",sym.SEMI);
          }
        case 44: break;
        case 13: 
          { if(debug) System.out.println("token LPAREN");
	return sf.newSymbol("LPAREN",sym.LPAREN);
          }
        case 45: break;
        case 30: 
          { if (debug) System.out.println("token BOL");
	return sf-newSymbol("BOL",sym.BOL,new boolean(yytext()));
          }
        case 46: break;
        case 20: 
          { if(debug) System.out.println("token OR");
	return sf.newSymbol("OR",sym.OR;
          }
        case 47: break;
        case 32: 
          { if(debug) System.out.println("token READ");
	return sf.newSymbol("READ",sym.READ);
          }
        case 48: break;
        case 5: 
          { /* saltos espacios en blanco*/
          }
        case 49: break;
        case 36: 
          { if(debug) System.out.println("token BEGIN");
	return sf.newSymbol("BEGIN",sym.BEGIN);
          }
        case 50: break;
        case 21: 
          { if(debug) System.out.println("token ASSIGN");
	return sf.newSymbol("ASSIGN",sym.ASSIGN);
          }
        case 51: break;
        case 19: 
          { if(debug) System.out.println("token IF");
	return sf.newSymbol("IF",sym.IF);
          }
        case 52: break;
        case 12: 
          { if(debug) System.out.println("token OVER");
	return sf.newSymbol("OVER",sym.OVER);
          }
        case 53: break;
        case 15: 
          { if(debug) System.out.println("token LCORCHE");
	return sf.newSymbol("LCORCHE",sym.LCORCHE);
          }
        case 54: break;
        case 3: 
          { if(debug) System.out.println("token ID");
	return sf.newSymbol("ID",sym.ID,new String(yytext()));
          }
        case 55: break;
        case 1: 
          { System.err.println("Caracter Ilegal encontrado en analisis lexico: " + yytext() + "\n");
          }
        case 56: break;
        case 40: 
          { if (debug) System.out.println("token BOOLEAN");
	return sf.newSymbol("BOOLEAN",sym.BOOLEAN);
          }
        case 57: break;
        case 23: 
          { if(debug) System.out.println("token NE");
	return sf.newSymbol("NE",sym.NE);
          }
        case 58: break;
        case 8: 
          { if(debug) System.out.println("token GT");
	return sf.newSymbol("LT",sym.LT);
          }
        case 59: break;
        case 7: 
          { if(debug) System.out.println("token LT");
	return sf.newSymbol("LT",sym.LT);
          }
        case 60: break;
        case 16: 
          { if(debug) System.out.println("token RCORCHE");
	return sf.newSymbol("RCORCHE",sym.RCORCHE);
          }
        case 61: break;
        case 31: 
          { if(debug) System.out.println("token THEN");
	return sf.newSymbol("THEN",sym.THEN);
          }
        case 62: break;
        case 26: 
          { if (debug) System.out.println("token FOR") ;
	return sf.newSymbol("FOR",sym.FOR);
          }
        case 63: break;
        case 27: 
          { if (debug) System.out.println("token INT");
	return sf.newSymbol("INT",sym.INT);
          }
        case 64: break;
        case 22: 
          { if(debug) System.out.println("token GE");
	return sf.newSymbol("LE",sym.LE);
          }
        case 65: break;
        case 10: 
          { if(debug) System.out.println("token MINUS");
	return sf.newSymbol("MINUS",sym.MINUS)
          }
        case 66: break;
        case 11: 
          { if(debug) System.out.println("token TIMES");
	return sf.newSymbol("TIMES",sym.TIMES);
          }
        case 67: break;
        case 18: 
          { if(debug) System.out.println("token COMA");
	return sf.newSymbol("COMA",sym.COMA);
          }
        case 68: break;
        case 24: 
          { if(debug) System.out.println("token LE");
	return sf.newSymbol("LE",sym.LE);
          }
        case 69: break;
        case 38: 
          { if (debug) System.out.println("token RETURN");
	return sf.newSymbol("RETURN",sym.RETURN);
          }
        case 70: break;
        case 34: 
          { if (debug) System.out.println("token VOID");
	return sf.newSymbol("VOID",sym.VOID);
          }
        case 71: break;
        case 29: 
          { /* salto comentarios */ 
	if(debug) System.out.println("token COMENTARIO");
          }
        case 72: break;
        case 33: 
          { if(debug) System.out.println("token ELSE");
	return sf.newSymbol("ELSE",sym.ELSE);
          }
        case 73: break;
        case 6: 
          { if(debug) System.out.println("token EQ");
	return sf.newSymbol("EQ",sym.EQ);
          }
        case 74: break;
        case 25: 
          { if(debug) System.out.println("token END");
	return sf.newSymbol("END",sym.END);
          }
        case 75: break;
        case 4: 
          { lineanum++;
          }
        case 76: break;
        case 28: 
          { if(debug) System.out.println("token AND");
	return sf.newSymbol("AND",sym.AND);
          }
        case 77: break;
        case 2: 
          { if(debug) System.out.println("token NUM");
	return sf.newSymbol("NUM",sym.NUM,new Integer(yytext()));
          }
        case 78: break;
        case 14: 
          { if(debug) System.out.println("token RPAREN");
	return sf.newSymbol("RPAREN",sym.RPAREN);
          }
        case 79: break;
        case 39: 
          { if(debug) System.out.println("token REPEAT");
	return sf.newSymbol("REPEAT",sym.REPEAT);
          }
        case 80: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return sf.newSymbol("EOF",sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
