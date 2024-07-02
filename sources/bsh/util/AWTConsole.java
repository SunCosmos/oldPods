package bsh.util;

import bsh.ConsoleInterface;
import bsh.Interpreter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;

/* loaded from: classes.dex */
public class AWTConsole extends TextArea implements ConsoleInterface, Runnable, KeyListener {
    private int histLine;
    private Vector history;
    private InputStream in;
    private InputStream inPipe;
    private StringBuffer line;
    private PrintStream out;
    private OutputStream outPipe;
    private String startedLine;
    private int textLength;

    public AWTConsole() {
        this(12, 80, null, null);
    }

    public AWTConsole(int i2, int i3, InputStream inputStream, OutputStream outputStream) {
        super(i2, i3);
        this.line = new StringBuffer();
        this.textLength = 0;
        this.history = new Vector();
        this.histLine = 0;
        setFont(new Font("Monospaced", 0, 14));
        setEditable(false);
        addKeyListener(this);
        this.outPipe = outputStream;
        if (outputStream == null) {
            this.outPipe = new PipedOutputStream();
            try {
                this.in = new PipedInputStream((PipedOutputStream) this.outPipe);
            } catch (IOException unused) {
                print("Console internal error...");
            }
        }
        this.inPipe = inputStream;
        new Thread(this).start();
        requestFocus();
    }

    public AWTConsole(InputStream inputStream, OutputStream outputStream) {
        this(12, 80, inputStream, outputStream);
    }

    private void acceptLine(String str) {
        OutputStream outputStream = this.outPipe;
        if (outputStream == null) {
            print("Console internal error...");
            return;
        }
        try {
            outputStream.write(str.getBytes());
            this.outPipe.flush();
        } catch (IOException unused) {
            this.outPipe = null;
            throw new RuntimeException("Console pipe broken...");
        }
    }

    private int countNLs() {
        String text = getText();
        int i2 = 0;
        for (int i3 = 0; i3 < text.length(); i3++) {
            if (text.charAt(i3) == '\n') {
                i2++;
            }
        }
        return i2;
    }

    private void doChar(char c2) {
        if (c2 < ' ' || c2 > '~') {
            return;
        }
        this.line.append(c2);
        append(String.valueOf(c2));
        this.textLength++;
    }

    private void enter() {
        String stringBuffer;
        if (this.line.length() == 0) {
            stringBuffer = ";\n";
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append((Object) this.line);
            stringBuffer2.append("\n");
            stringBuffer = stringBuffer2.toString();
            this.history.addElement(this.line.toString());
        }
        this.line.setLength(0);
        this.histLine = 0;
        append("\n");
        this.textLength = getText().length();
        acceptLine(stringBuffer);
        setCaretPosition(this.textLength);
    }

    private void historyDown() {
        int i2 = this.histLine;
        if (i2 == 0) {
            return;
        }
        this.histLine = i2 - 1;
        showHistoryLine();
    }

    private void historyUp() {
        if (this.history.size() == 0) {
            return;
        }
        if (this.histLine == 0) {
            this.startedLine = this.line.toString();
        }
        if (this.histLine < this.history.size()) {
            this.histLine++;
            showHistoryLine();
        }
    }

    private void inPipeWatcher() {
        if (this.inPipe == null) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            this.out = new PrintStream(pipedOutputStream);
            this.inPipe = new PipedInputStream(pipedOutputStream);
        }
        byte[] bArr = new byte[256];
        while (true) {
            int read = this.inPipe.read(bArr);
            if (read == -1) {
                println("Console: Input closed...");
                return;
            }
            print(new String(bArr, 0, read));
        }
    }

    public static void main(String[] strArr) {
        AWTConsole aWTConsole = new AWTConsole();
        final Frame frame = new Frame("Bsh Console");
        frame.add(aWTConsole, "Center");
        frame.pack();
        frame.show();
        frame.addWindowListener(new WindowAdapter() { // from class: bsh.util.AWTConsole.1
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispose();
            }
        });
        new Interpreter(aWTConsole).run();
    }

    private void showHistoryLine() {
        String str;
        if (this.histLine == 0) {
            str = this.startedLine;
        } else {
            Vector vector = this.history;
            str = (String) vector.elementAt(vector.size() - this.histLine);
        }
        replaceRange(str, this.textLength - this.line.length(), this.textLength);
        this.line = new StringBuffer(str);
        this.textLength = getText().length();
    }

    @Override // bsh.ConsoleInterface
    public void error(Object obj) {
        print(obj, Color.red);
    }

    @Override // bsh.ConsoleInterface
    public PrintStream getErr() {
        return this.out;
    }

    @Override // bsh.ConsoleInterface
    public Reader getIn() {
        return new InputStreamReader(this.in);
    }

    @Override // bsh.ConsoleInterface
    public PrintStream getOut() {
        return this.out;
    }

    public void keyPressed(KeyEvent keyEvent) {
        type(keyEvent.getKeyCode(), keyEvent.getKeyChar(), keyEvent.getModifiers());
        keyEvent.consume();
    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override // bsh.ConsoleInterface
    public synchronized void print(Object obj) {
        append(String.valueOf(obj));
        this.textLength = getText().length();
    }

    public void print(Object obj, Color color) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("*** ");
        stringBuffer.append(String.valueOf(obj));
        print(stringBuffer.toString());
    }

    @Override // bsh.ConsoleInterface
    public void println(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(obj));
        stringBuffer.append("\n");
        print(stringBuffer.toString());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            inPipeWatcher();
        } catch (IOException unused) {
            println("Console: I/O Error...");
        }
    }

    public void setCaretPosition(int i2) {
        getPeer().setCaretPosition(i2 + countNLs());
    }

    public String toString() {
        return "BeanShell AWTConsole";
    }

    public void type(int i2, char c2, int i3) {
        int i4;
        if (i2 == 38) {
            historyUp();
            return;
        }
        if (i2 == 40) {
            historyDown();
            return;
        }
        if (i2 == 67) {
            if ((i3 & 2) > 0) {
                this.line.append("^C");
                append("^C");
                i4 = this.textLength + 2;
                this.textLength = i4;
            }
            doChar(c2);
            return;
        }
        if (i2 == 85) {
            if ((i3 & 2) > 0) {
                int length = this.line.length();
                int i5 = this.textLength;
                replaceRange("", i5 - length, i5);
                this.line.setLength(0);
                this.histLine = 0;
                i4 = getText().length();
                this.textLength = i4;
            }
            doChar(c2);
            return;
        }
        switch (i2) {
            case 8:
                if (this.line.length() > 0) {
                    this.line.setLength(r3.length() - 1);
                    int i6 = this.textLength;
                    replaceRange("", i6 - 1, i6);
                    i4 = this.textLength - 1;
                    break;
                } else {
                    return;
                }
            case 9:
                this.line.append("    ");
                append("    ");
                i4 = this.textLength + 4;
                break;
            case 10:
                enter();
                return;
            default:
                doChar(c2);
                return;
        }
        this.textLength = i4;
    }
}
