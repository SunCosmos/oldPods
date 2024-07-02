package bsh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Interpreter implements Runnable, ConsoleInterface, Serializable {
    public static boolean DEBUG = false;
    public static boolean LOCALSCOPING = false;
    public static boolean TRACE = false;
    public static final String VERSION = "2.0b4";
    static /* synthetic */ Class array$Ljava$lang$String = null;
    static transient PrintStream debug = null;
    static This sharedObject = null;
    static String systemLineSeparator = "\n";
    ConsoleInterface console;
    transient PrintStream err;
    protected boolean evalOnly;
    private boolean exitOnEOF;
    NameSpace globalNameSpace;
    transient Reader in;
    protected boolean interactive;
    transient PrintStream out;
    Interpreter parent;
    transient Parser parser;
    private boolean showResults;
    String sourceFileInfo;
    private boolean strictJava;

    /* renamed from: bsh.Interpreter$1 */
    /* loaded from: classes.dex */
    class AnonymousClass1 extends FilterInputStream {
        AnonymousClass1(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() {
            return 0;
        }
    }

    static {
        staticInit();
    }

    public Interpreter() {
        this(new StringReader(""), System.out, System.err, false, null);
        this.evalOnly = true;
        setu("bsh.evalOnly", new Primitive(true));
    }

    public Interpreter(ConsoleInterface consoleInterface) {
        this(consoleInterface, null);
    }

    public Interpreter(ConsoleInterface consoleInterface, NameSpace nameSpace) {
        this(consoleInterface.getIn(), consoleInterface.getOut(), consoleInterface.getErr(), true, nameSpace);
        setConsole(consoleInterface);
    }

    public Interpreter(Reader reader, PrintStream printStream, PrintStream printStream2, boolean z) {
        this(reader, printStream, printStream2, z, null);
    }

    public Interpreter(Reader reader, PrintStream printStream, PrintStream printStream2, boolean z, NameSpace nameSpace) {
        this(reader, printStream, printStream2, z, nameSpace, null, null);
    }

    public Interpreter(Reader reader, PrintStream printStream, PrintStream printStream2, boolean z, NameSpace nameSpace, Interpreter interpreter, String str) {
        this.strictJava = false;
        this.exitOnEOF = true;
        this.parser = new Parser(reader);
        long currentTimeMillis = System.currentTimeMillis();
        this.in = reader;
        this.out = printStream;
        this.err = printStream2;
        this.interactive = z;
        debug = printStream2;
        this.parent = interpreter;
        if (interpreter != null) {
            setStrictJava(interpreter.getStrictJava());
        }
        this.sourceFileInfo = str;
        BshClassManager createClassManager = BshClassManager.createClassManager(this);
        if (nameSpace == null) {
            this.globalNameSpace = new NameSpace(createClassManager, "global");
        } else {
            this.globalNameSpace = nameSpace;
        }
        if (!(getu("bsh") instanceof This)) {
            initRootSystemObject();
        }
        if (z) {
            loadRCFiles();
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        if (DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Time to initialize interpreter: ");
            stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
            debug(stringBuffer.toString());
        }
    }

    private boolean Line() {
        return this.parser.Line();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static final void debug(String str) {
        if (DEBUG) {
            PrintStream printStream = debug;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("// Debug: ");
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
    }

    private String getBshPrompt() {
        try {
            return (String) eval("getBshPrompt()");
        } catch (Exception unused) {
            return "bsh % ";
        }
    }

    private JavaCharStream get_jj_input_stream() {
        return this.parser.jj_input_stream;
    }

    private JJTParserState get_jjtree() {
        return this.parser.jjtree;
    }

    private void initRootSystemObject() {
        BshClassManager classManager = getClassManager();
        setu("bsh", new NameSpace(classManager, "Bsh Object").getThis(this));
        if (sharedObject == null) {
            sharedObject = new NameSpace(classManager, "Bsh Shared System Object").getThis(this);
        }
        setu("bsh.system", sharedObject);
        setu("bsh.shared", sharedObject);
        setu("bsh.help", new NameSpace(classManager, "Bsh Command Help Text").getThis(this));
        try {
            setu("bsh.cwd", System.getProperty("user.dir"));
        } catch (SecurityException unused) {
            setu("bsh.cwd", ".");
        }
        setu("bsh.interactive", new Primitive(this.interactive));
        setu("bsh.evalOnly", new Primitive(this.evalOnly));
    }

    public static void invokeMain(Class cls, String[] strArr) {
        Class[] clsArr = new Class[1];
        Class cls2 = array$Ljava$lang$String;
        if (cls2 == null) {
            cls2 = class$("[Ljava.lang.String;");
            array$Ljava$lang$String = cls2;
        }
        clsArr[0] = cls2;
        Method resolveJavaMethod = Reflect.resolveJavaMethod(null, cls, "main", clsArr, true);
        if (resolveJavaMethod != null) {
            resolveJavaMethod.invoke(null, strArr);
        }
    }

    public static void main(String[] strArr) {
        String[] strArr2;
        PrintStream printStream;
        StringBuffer stringBuffer;
        String str;
        if (strArr.length <= 0) {
            new Interpreter(new CommandLineReader(new InputStreamReader((System.getProperty("os.name").startsWith("Windows") && System.getProperty("java.version").startsWith("1.1.")) ? new FilterInputStream(System.in) { // from class: bsh.Interpreter.1
                AnonymousClass1(InputStream inputStream) {
                    super(inputStream);
                }

                @Override // java.io.FilterInputStream, java.io.InputStream
                public int available() {
                    return 0;
                }
            } : System.in)), System.out, System.err, true).run();
            return;
        }
        String str2 = strArr[0];
        if (strArr.length > 1) {
            strArr2 = new String[strArr.length - 1];
            System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
        } else {
            strArr2 = new String[0];
        }
        Interpreter interpreter = new Interpreter();
        interpreter.setu("bsh.args", strArr2);
        try {
            Object source = interpreter.source(str2, interpreter.globalNameSpace);
            if (source instanceof Class) {
                try {
                    invokeMain((Class) source, strArr2);
                } catch (Exception e) {
                    e = e;
                    if (e instanceof InvocationTargetException) {
                        e = ((InvocationTargetException) e).getTargetException();
                    }
                    PrintStream printStream2 = System.err;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Class: ");
                    stringBuffer2.append(source);
                    stringBuffer2.append(" main method threw exception:");
                    stringBuffer2.append(e);
                    printStream2.println(stringBuffer2.toString());
                }
            }
        } catch (TargetError e2) {
            PrintStream printStream3 = System.out;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Script threw exception: ");
            stringBuffer3.append(e2);
            printStream3.println(stringBuffer3.toString());
            if (e2.inNativeCode()) {
                e2.printStackTrace(DEBUG, System.err);
            }
        } catch (EvalError e3) {
            e = e3;
            printStream = System.out;
            stringBuffer = new StringBuffer();
            str = "Evaluation Error: ";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        } catch (FileNotFoundException e4) {
            e = e4;
            printStream = System.out;
            stringBuffer = new StringBuffer();
            str = "File not found: ";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        } catch (IOException e5) {
            e = e5;
            printStream = System.out;
            stringBuffer = new StringBuffer();
            str = "I/O Error: ";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        PrintStream printStream;
        objectInputStream.defaultReadObject();
        ConsoleInterface consoleInterface = this.console;
        if (consoleInterface != null) {
            setOut(consoleInterface.getOut());
            printStream = this.console.getErr();
        } else {
            setOut(System.out);
            printStream = System.err;
        }
        setErr(printStream);
    }

    public static void redirectOutputToFile(String str) {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(str));
            System.setOut(printStream);
            System.setErr(printStream);
        } catch (IOException unused) {
            PrintStream printStream2 = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't redirect output to file: ");
            stringBuffer.append(str);
            printStream2.println(stringBuffer.toString());
        }
    }

    private String showEvalString(String str) {
        String replace = str.replace('\n', ' ').replace('\r', ' ');
        if (replace.length() <= 80) {
            return replace;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(replace.substring(0, 80));
        stringBuffer.append(" . . . ");
        return stringBuffer.toString();
    }

    static void staticInit() {
        PrintStream printStream;
        StringBuffer stringBuffer;
        String str;
        try {
            systemLineSeparator = System.getProperty("line.separator");
            debug = System.err;
            DEBUG = Boolean.getBoolean("debug");
            TRACE = Boolean.getBoolean("trace");
            LOCALSCOPING = Boolean.getBoolean("localscoping");
            String property = System.getProperty("outfile");
            if (property != null) {
                redirectOutputToFile(property);
            }
        } catch (SecurityException e) {
            e = e;
            printStream = System.err;
            stringBuffer = new StringBuffer();
            str = "Could not init static:";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        } catch (Exception e2) {
            e = e2;
            printStream = System.err;
            stringBuffer = new StringBuffer();
            str = "Could not init static(2):";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        } catch (Throwable th) {
            e = th;
            printStream = System.err;
            stringBuffer = new StringBuffer();
            str = "Could not init static(3):";
            stringBuffer.append(str);
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        }
    }

    @Override // bsh.ConsoleInterface
    public final void error(Object obj) {
        ConsoleInterface consoleInterface = this.console;
        if (consoleInterface != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("// Error: ");
            stringBuffer.append(obj);
            stringBuffer.append("\n");
            consoleInterface.error(stringBuffer.toString());
            return;
        }
        PrintStream printStream = this.err;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("// Error: ");
        stringBuffer2.append(obj);
        printStream.println(stringBuffer2.toString());
        this.err.flush();
    }

    public Object eval(Reader reader) {
        return eval(reader, this.globalNameSpace, "eval stream");
    }

    public Object eval(Reader reader, NameSpace nameSpace, String str) {
        SimpleNode simpleNode;
        if (DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("eval: nameSpace = ");
            stringBuffer.append(nameSpace);
            debug(stringBuffer.toString());
        }
        Interpreter interpreter = new Interpreter(reader, this.out, this.err, false, nameSpace, this, str);
        CallStack callStack = new CallStack(nameSpace);
        boolean z = false;
        SimpleNode simpleNode2 = null;
        Object obj = null;
        while (true) {
            if (z) {
                break;
            }
            try {
                try {
                    try {
                        z = interpreter.Line();
                        if (interpreter.get_jjtree().nodeArity() > 0) {
                            simpleNode = (SimpleNode) interpreter.get_jjtree().rootNode();
                            try {
                                simpleNode.setSourceFile(str);
                                if (TRACE) {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("// ");
                                    stringBuffer2.append(simpleNode.getText());
                                    println(stringBuffer2.toString());
                                }
                                obj = simpleNode.eval(callStack, interpreter);
                                if (callStack.depth() > 1) {
                                    StringBuffer stringBuffer3 = new StringBuffer();
                                    stringBuffer3.append("Callstack growing: ");
                                    stringBuffer3.append(callStack);
                                    throw new InterpreterError(stringBuffer3.toString());
                                }
                                if (obj instanceof ReturnControl) {
                                    obj = ((ReturnControl) obj).value;
                                } else if (interpreter.showResults && obj != Primitive.VOID) {
                                    StringBuffer stringBuffer4 = new StringBuffer();
                                    stringBuffer4.append("<");
                                    stringBuffer4.append(obj);
                                    stringBuffer4.append(">");
                                    println(stringBuffer4.toString());
                                }
                            } catch (EvalError e) {
                                e = e;
                                if (DEBUG) {
                                    e.printStackTrace();
                                }
                                if (e.getNode() == null) {
                                    e.setNode(simpleNode);
                                }
                                StringBuffer stringBuffer5 = new StringBuffer();
                                stringBuffer5.append("Sourced file: ");
                                stringBuffer5.append(str);
                                e.reThrow(stringBuffer5.toString());
                                interpreter.get_jjtree().reset();
                                if (callStack.depth() > 1) {
                                    callStack.clear();
                                    callStack.push(nameSpace);
                                }
                            } catch (InterpreterError e2) {
                                e = e2;
                                simpleNode2 = simpleNode;
                                e.printStackTrace();
                                StringBuffer stringBuffer6 = new StringBuffer();
                                stringBuffer6.append("Sourced file: ");
                                stringBuffer6.append(str);
                                stringBuffer6.append(" internal Error: ");
                                stringBuffer6.append(e.getMessage());
                                throw new EvalError(stringBuffer6.toString(), simpleNode2, callStack);
                            } catch (TargetError e3) {
                                e = e3;
                                if (e.getNode() == null) {
                                    e.setNode(simpleNode);
                                }
                                StringBuffer stringBuffer7 = new StringBuffer();
                                stringBuffer7.append("Sourced file: ");
                                stringBuffer7.append(str);
                                e.reThrow(stringBuffer7.toString());
                                interpreter.get_jjtree().reset();
                                if (callStack.depth() > 1) {
                                    callStack.clear();
                                    callStack.push(nameSpace);
                                }
                            } catch (TokenMgrError e4) {
                                e = e4;
                                simpleNode2 = simpleNode;
                                StringBuffer stringBuffer8 = new StringBuffer();
                                stringBuffer8.append("Sourced file: ");
                                stringBuffer8.append(str);
                                stringBuffer8.append(" Token Parsing Error: ");
                                stringBuffer8.append(e.getMessage());
                                throw new EvalError(stringBuffer8.toString(), simpleNode2, callStack);
                            } catch (Exception e5) {
                                e = e5;
                                simpleNode2 = simpleNode;
                                if (DEBUG) {
                                    e.printStackTrace();
                                }
                                StringBuffer stringBuffer9 = new StringBuffer();
                                stringBuffer9.append("Sourced file: ");
                                stringBuffer9.append(str);
                                stringBuffer9.append(" unknown error: ");
                                stringBuffer9.append(e.getMessage());
                                throw new EvalError(stringBuffer9.toString(), simpleNode2, callStack);
                            }
                        }
                        interpreter.get_jjtree().reset();
                    } catch (InterpreterError e6) {
                        e = e6;
                    } catch (TargetError e7) {
                        e = e7;
                        simpleNode = null;
                    } catch (EvalError e8) {
                        e = e8;
                        simpleNode = null;
                    } catch (TokenMgrError e9) {
                        e = e9;
                    } catch (Exception e10) {
                        e = e10;
                    }
                    if (callStack.depth() > 1) {
                        callStack.clear();
                        callStack.push(nameSpace);
                    }
                } catch (ParseException e11) {
                    boolean z2 = DEBUG;
                    if (z2) {
                        error(e11.getMessage(z2));
                    }
                    e11.setErrorSourceFile(str);
                    throw e11;
                }
            } finally {
                interpreter.get_jjtree().reset();
                if (callStack.depth() > 1) {
                    callStack.clear();
                    callStack.push(nameSpace);
                }
            }
        }
        return Primitive.unwrap(obj);
    }

    public Object eval(String str) {
        if (DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("eval(String): ");
            stringBuffer.append(str);
            debug(stringBuffer.toString());
        }
        return eval(str, this.globalNameSpace);
    }

    public Object eval(String str, NameSpace nameSpace) {
        if (!str.endsWith(";")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(";");
            str = stringBuffer.toString();
        }
        StringReader stringReader = new StringReader(str);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("inline evaluation of: ``");
        stringBuffer2.append(showEvalString(str));
        stringBuffer2.append("''");
        return eval(stringReader, nameSpace, stringBuffer2.toString());
    }

    public Object get(String str) {
        try {
            return Primitive.unwrap(this.globalNameSpace.get(str, this));
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, new CallStack());
        }
    }

    public BshClassManager getClassManager() {
        return getNameSpace().getClassManager();
    }

    @Override // bsh.ConsoleInterface
    public PrintStream getErr() {
        return this.err;
    }

    @Override // bsh.ConsoleInterface
    public Reader getIn() {
        return this.in;
    }

    public Object getInterface(Class cls) {
        try {
            return this.globalNameSpace.getThis(this).getInterface(cls);
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, new CallStack());
        }
    }

    public NameSpace getNameSpace() {
        return this.globalNameSpace;
    }

    @Override // bsh.ConsoleInterface
    public PrintStream getOut() {
        return this.out;
    }

    public Interpreter getParent() {
        return this.parent;
    }

    public boolean getShowResults() {
        return this.showResults;
    }

    public String getSourceFileInfo() {
        String str = this.sourceFileInfo;
        return str != null ? str : "<unknown source>";
    }

    public boolean getStrictJava() {
        return this.strictJava;
    }

    Object getu(String str) {
        try {
            return get(str);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("set: ");
            stringBuffer.append(e);
            throw new InterpreterError(stringBuffer.toString());
        }
    }

    void loadRCFiles() {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(System.getProperty("user.home"));
            stringBuffer.append(File.separator);
            stringBuffer.append(".bshrc");
            source(stringBuffer.toString(), this.globalNameSpace);
        } catch (Exception e) {
            if (DEBUG) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not find rc file: ");
                stringBuffer2.append(e);
                debug(stringBuffer2.toString());
            }
        }
    }

    public File pathToFile(String str) {
        File file = new File(str);
        if (!file.isAbsolute()) {
            String str2 = (String) getu("bsh.cwd");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2);
            stringBuffer.append(File.separator);
            stringBuffer.append(str);
            file = new File(stringBuffer.toString());
        }
        return new File(file.getCanonicalPath());
    }

    @Override // bsh.ConsoleInterface
    public final void print(Object obj) {
        ConsoleInterface consoleInterface = this.console;
        if (consoleInterface != null) {
            consoleInterface.print(obj);
        } else {
            this.out.print(obj);
            this.out.flush();
        }
    }

    @Override // bsh.ConsoleInterface
    public final void println(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(obj));
        stringBuffer.append(systemLineSeparator);
        print(stringBuffer.toString());
    }

    @Override // java.lang.Runnable
    public void run() {
        String stringBuffer;
        if (this.evalOnly) {
            throw new RuntimeException("bsh Interpreter: No stream");
        }
        if (this.interactive) {
            try {
                eval("printBanner();");
            } catch (EvalError unused) {
                println("BeanShell 2.0b4 - by Pat Niemeyer (pat@pat.net)");
            }
        }
        CallStack callStack = new CallStack(this.globalNameSpace);
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        try {
                            System.out.flush();
                            System.err.flush();
                            Thread.yield();
                            if (this.interactive) {
                                print(getBshPrompt());
                            }
                            z = Line();
                            if (get_jjtree().nodeArity() > 0) {
                                SimpleNode simpleNode = (SimpleNode) get_jjtree().rootNode();
                                if (DEBUG) {
                                    simpleNode.dump(">");
                                }
                                Object eval = simpleNode.eval(callStack, this);
                                if (callStack.depth() > 1) {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("Callstack growing: ");
                                    stringBuffer2.append(callStack);
                                    throw new InterpreterError(stringBuffer2.toString());
                                    break;
                                }
                                if (eval instanceof ReturnControl) {
                                    eval = ((ReturnControl) eval).value;
                                }
                                if (eval != Primitive.VOID) {
                                    setu("$_", eval);
                                    if (this.showResults) {
                                        StringBuffer stringBuffer3 = new StringBuffer();
                                        stringBuffer3.append("<");
                                        stringBuffer3.append(eval);
                                        stringBuffer3.append(">");
                                        println(stringBuffer3.toString());
                                    }
                                }
                            }
                            get_jjtree().reset();
                        } catch (EvalError e) {
                            if (this.interactive) {
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("EvalError: ");
                                stringBuffer4.append(e.toString());
                                stringBuffer = stringBuffer4.toString();
                            } else {
                                StringBuffer stringBuffer5 = new StringBuffer();
                                stringBuffer5.append("EvalError: ");
                                stringBuffer5.append(e.getMessage());
                                stringBuffer = stringBuffer5.toString();
                            }
                            error(stringBuffer);
                            if (DEBUG) {
                                e.printStackTrace();
                            }
                            if (!this.interactive) {
                                z = true;
                            }
                            get_jjtree().reset();
                            if (callStack.depth() > 1) {
                            }
                        } catch (TokenMgrError e2) {
                            StringBuffer stringBuffer6 = new StringBuffer();
                            stringBuffer6.append("Error parsing input: ");
                            stringBuffer6.append(e2);
                            error(stringBuffer6.toString());
                            this.parser.reInitTokenInput(this.in);
                            if (!this.interactive) {
                                z = true;
                            }
                            get_jjtree().reset();
                            if (callStack.depth() > 1) {
                            }
                        }
                    } catch (TargetError e3) {
                        StringBuffer stringBuffer7 = new StringBuffer();
                        stringBuffer7.append("// Uncaught Exception: ");
                        stringBuffer7.append(e3);
                        error(stringBuffer7.toString());
                        if (e3.inNativeCode()) {
                            e3.printStackTrace(DEBUG, this.err);
                        }
                        if (!this.interactive) {
                            z = true;
                        }
                        setu("$_e", e3.getTarget());
                        get_jjtree().reset();
                        if (callStack.depth() > 1) {
                        }
                    } catch (Exception e4) {
                        StringBuffer stringBuffer8 = new StringBuffer();
                        stringBuffer8.append("Unknown error: ");
                        stringBuffer8.append(e4);
                        error(stringBuffer8.toString());
                        if (DEBUG) {
                            e4.printStackTrace();
                        }
                        if (!this.interactive) {
                            z = true;
                        }
                        get_jjtree().reset();
                        if (callStack.depth() > 1) {
                        }
                    }
                } catch (InterpreterError e5) {
                    StringBuffer stringBuffer9 = new StringBuffer();
                    stringBuffer9.append("Internal Error: ");
                    stringBuffer9.append(e5.getMessage());
                    error(stringBuffer9.toString());
                    e5.printStackTrace();
                    if (!this.interactive) {
                        z = true;
                    }
                    get_jjtree().reset();
                    if (callStack.depth() > 1) {
                    }
                } catch (ParseException e6) {
                    StringBuffer stringBuffer10 = new StringBuffer();
                    stringBuffer10.append("Parser Error: ");
                    stringBuffer10.append(e6.getMessage(DEBUG));
                    error(stringBuffer10.toString());
                    if (DEBUG) {
                        e6.printStackTrace();
                    }
                    if (!this.interactive) {
                        z = true;
                    }
                    this.parser.reInitInput(this.in);
                    get_jjtree().reset();
                    if (callStack.depth() > 1) {
                    }
                }
                if (callStack.depth() > 1) {
                    callStack.clear();
                    callStack.push(this.globalNameSpace);
                }
            } catch (Throwable th) {
                get_jjtree().reset();
                if (callStack.depth() > 1) {
                    callStack.clear();
                    callStack.push(this.globalNameSpace);
                }
                throw th;
            }
        }
        if (this.interactive && this.exitOnEOF) {
            System.exit(0);
        }
    }

    public void set(String str, double d2) {
        set(str, new Primitive(d2));
    }

    public void set(String str, float f) {
        set(str, new Primitive(f));
    }

    public void set(String str, int i2) {
        set(str, new Primitive(i2));
    }

    public void set(String str, long j) {
        set(str, new Primitive(j));
    }

    public void set(String str, Object obj) {
        if (obj == null) {
            obj = Primitive.NULL;
        }
        CallStack callStack = new CallStack();
        try {
            if (Name.isCompound(str)) {
                this.globalNameSpace.getNameResolver(str).toLHS(callStack, this).assign(obj, false);
            } else {
                this.globalNameSpace.setVariable(str, obj, false);
            }
        } catch (UtilEvalError e) {
            throw e.toEvalError(SimpleNode.JAVACODE, callStack);
        }
    }

    public void set(String str, boolean z) {
        set(str, new Primitive(z));
    }

    public void setClassLoader(ClassLoader classLoader) {
        getClassManager().setClassLoader(classLoader);
    }

    public void setConsole(ConsoleInterface consoleInterface) {
        this.console = consoleInterface;
        setu("bsh.console", consoleInterface);
        setOut(consoleInterface.getOut());
        setErr(consoleInterface.getErr());
    }

    public void setErr(PrintStream printStream) {
        this.err = printStream;
    }

    public void setExitOnEOF(boolean z) {
        this.exitOnEOF = z;
    }

    public void setNameSpace(NameSpace nameSpace) {
        this.globalNameSpace = nameSpace;
    }

    public void setOut(PrintStream printStream) {
        this.out = printStream;
    }

    public void setShowResults(boolean z) {
        this.showResults = z;
    }

    public void setStrictJava(boolean z) {
        this.strictJava = z;
    }

    void setu(String str, Object obj) {
        try {
            set(str, obj);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("set: ");
            stringBuffer.append(e);
            throw new InterpreterError(stringBuffer.toString());
        }
    }

    public Object source(String str) {
        return source(str, this.globalNameSpace);
    }

    public Object source(String str, NameSpace nameSpace) {
        File pathToFile = pathToFile(str);
        if (DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Sourcing file: ");
            stringBuffer.append(pathToFile);
            debug(stringBuffer.toString());
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile));
        try {
            return eval(bufferedReader, nameSpace, str);
        } finally {
            bufferedReader.close();
        }
    }

    public void unset(String str) {
        try {
            LHS lhs = this.globalNameSpace.getNameResolver(str).toLHS(new CallStack(), this);
            if (lhs.type == 0) {
                lhs.nameSpace.unsetVariable(str);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't unset, not a variable: ");
            stringBuffer.append(str);
            throw new EvalError(stringBuffer.toString(), SimpleNode.JAVACODE, new CallStack());
        } catch (UtilEvalError e) {
            throw new EvalError(e.getMessage(), SimpleNode.JAVACODE, new CallStack());
        }
    }
}
