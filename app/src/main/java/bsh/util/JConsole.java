package bsh.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/* loaded from: classes.dex */
public class JConsole extends JScrollPane implements GUIConsoleInterface, Runnable, KeyListener, MouseListener, ActionListener, PropertyChangeListener {
    private static final String COPY = "Copy";
    private static final String CUT = "Cut";
    private static final String PASTE = "Paste";
    final int SHOW_AMBIG_MAX;
    String ZEROS;
    private int cmdStart;
    private DefaultStyledDocument doc;
    private boolean gotUp;
    private int histLine;
    private Vector history;
    private InputStream in;
    private InputStream inPipe;
    private JPopupMenu menu;
    NameCompletion nameCompletion;
    private PrintStream out;
    private OutputStream outPipe;
    private String startedLine;
    private JTextPane text;

    /* loaded from: classes.dex */
    public static class BlockingPipedInputStream extends PipedInputStream {
        boolean closed;

        public BlockingPipedInputStream(PipedOutputStream pipedOutputStream) {
            super(pipedOutputStream);
        }

        @Override // java.io.PipedInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.closed = true;
            super.close();
        }

        @Override // java.io.PipedInputStream, java.io.InputStream
        public synchronized int read() {
            int i2;
            int i3;
            if (this.closed) {
                throw new IOException("stream closed");
            }
            while (true) {
                i2 = ((PipedInputStream) this).in;
                if (i2 >= 0) {
                    break;
                }
                notifyAll();
                try {
                    wait(750L);
                } catch (InterruptedException unused) {
                    throw new InterruptedIOException();
                }
            }
            byte[] bArr = ((PipedInputStream) this).buffer;
            int i4 = ((PipedInputStream) this).out;
            int i5 = i4 + 1;
            ((PipedInputStream) this).out = i5;
            i3 = bArr[i4] & 255;
            if (i5 >= bArr.length) {
                ((PipedInputStream) this).out = 0;
            }
            if (i2 == ((PipedInputStream) this).out) {
                ((PipedInputStream) this).in = -1;
            }
            return i3;
        }
    }

    public JConsole() {
        this(null, null);
    }

    public JConsole(InputStream inputStream, OutputStream outputStream) {
        this.cmdStart = 0;
        this.history = new Vector();
        this.histLine = 0;
        this.SHOW_AMBIG_MAX = 10;
        this.gotUp = true;
        this.ZEROS = "000";
        DefaultStyledDocument defaultStyledDocument = new DefaultStyledDocument();
        this.doc = defaultStyledDocument;
        this.text = new JTextPane(this, defaultStyledDocument) { // from class: bsh.util.JConsole.1
            private final /* synthetic */ JConsole this$0;

            {
                this.this$0 = this;
            }

            public void cut() {
                if (this.this$0.text.getCaretPosition() < this.this$0.cmdStart) {
                    super.copy();
                } else {
                    super.cut();
                }
            }

            public void paste() {
                this.this$0.forceCaretMoveToEnd();
                super.paste();
            }
        };
        Font font = new Font("Monospaced", 0, 14);
        this.text.setText("");
        this.text.setFont(font);
        this.text.setMargin(new Insets(7, 5, 7, 5));
        this.text.addKeyListener(this);
        setViewportView(this.text);
        JPopupMenu jPopupMenu = new JPopupMenu("JConsole\tMenu");
        this.menu = jPopupMenu;
        jPopupMenu.add(new JMenuItem(CUT)).addActionListener(this);
        this.menu.add(new JMenuItem(COPY)).addActionListener(this);
        this.menu.add(new JMenuItem(PASTE)).addActionListener(this);
        this.text.addMouseListener(this);
        UIManager.addPropertyChangeListener(this);
        this.outPipe = outputStream;
        if (outputStream == null) {
            this.outPipe = new PipedOutputStream();
            try {
                this.in = new PipedInputStream((PipedOutputStream) this.outPipe);
            } catch (IOException unused) {
                print("Console internal\terror (1)...", Color.red);
            }
        }
        this.inPipe = inputStream;
        if (inputStream == null) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            this.out = new PrintStream(pipedOutputStream);
            try {
                this.inPipe = new BlockingPipedInputStream(pipedOutputStream);
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Console internal error: ");
                stringBuffer.append(e);
                print(stringBuffer.toString());
            }
        }
        new Thread(this).start();
        requestFocus();
    }

    private void acceptLine(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            String num = Integer.toString(str.charAt(i2), 16);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.ZEROS.substring(0, 4 - num.length()));
            stringBuffer2.append(num);
            String stringBuffer3 = stringBuffer2.toString();
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("\\u");
            stringBuffer4.append(stringBuffer3);
            stringBuffer.append(stringBuffer4.toString());
        }
        String stringBuffer5 = stringBuffer.toString();
        OutputStream outputStream = this.outPipe;
        if (outputStream == null) {
            print("Console internal\terror: cannot output ...", Color.red);
            return;
        }
        try {
            outputStream.write(stringBuffer5.getBytes());
            this.outPipe.flush();
        } catch (IOException unused) {
            this.outPipe = null;
            throw new RuntimeException("Console pipe broken...");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void append(String str) {
        int textLength = textLength();
        this.text.select(textLength, textLength);
        this.text.replaceSelection(str);
    }

    private void doCommandCompletion(String str) {
        if (this.nameCompletion == null) {
            return;
        }
        int length = str.length() - 1;
        while (length >= 0 && (Character.isJavaIdentifierPart(str.charAt(length)) || str.charAt(length) == '.')) {
            length--;
        }
        String substring = str.substring(length + 1);
        if (substring.length() < 2) {
            return;
        }
        String[] completeName = this.nameCompletion.completeName(substring);
        if (completeName.length == 0) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        int i2 = 0;
        if (completeName.length == 1 && !completeName.equals(substring)) {
            append(completeName[0].substring(substring.length()));
            return;
        }
        String text = this.text.getText();
        String substring2 = text.substring(this.cmdStart);
        int i3 = this.cmdStart;
        while (text.charAt(i3) != '\n' && i3 > 0) {
            i3--;
        }
        Object substring3 = text.substring(i3 + 1, this.cmdStart);
        StringBuffer stringBuffer = new StringBuffer("\n");
        while (i2 < completeName.length && i2 < 10) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(completeName[i2]);
            stringBuffer2.append("\n");
            stringBuffer.append(stringBuffer2.toString());
            i2++;
        }
        if (i2 == 10) {
            stringBuffer.append("...\n");
        }
        print(stringBuffer, Color.gray);
        print(substring3);
        append(substring2);
    }

    private void enter() {
        String stringBuffer;
        String cmd = getCmd();
        if (cmd.length() == 0) {
            stringBuffer = ";\n";
        } else {
            this.history.addElement(cmd);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(cmd);
            stringBuffer2.append("\n");
            stringBuffer = stringBuffer2.toString();
        }
        append("\n");
        this.histLine = 0;
        acceptLine(stringBuffer);
        this.text.repaint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceCaretMoveToEnd() {
        if (this.text.getCaretPosition() < this.cmdStart) {
            this.text.setCaretPosition(textLength());
        }
        this.text.repaint();
    }

    private void forceCaretMoveToStart() {
        this.text.getCaretPosition();
        this.text.repaint();
    }

    private String getCmd() {
        try {
            return this.text.getText(this.cmdStart, textLength() - this.cmdStart);
        } catch (BadLocationException e) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Internal JConsole Error: ");
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AttributeSet getStyle() {
        return this.text.getCharacterAttributes();
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
            this.startedLine = getCmd();
        }
        if (this.histLine < this.history.size()) {
            this.histLine++;
            showHistoryLine();
        }
    }

    private void inPipeWatcher() {
        byte[] bArr = new byte[256];
        while (true) {
            int read = this.inPipe.read(bArr);
            if (read == -1) {
                println("Console: Input\tclosed...");
                return;
            }
            print(new String(bArr, 0, read));
        }
    }

    private void invokeAndWait(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
            return;
        }
        try {
            SwingUtilities.invokeAndWait(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String replaceRange(Object obj, int i2, int i3) {
        String obj2 = obj.toString();
        this.text.select(i2, i3);
        this.text.replaceSelection(obj2);
        return obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCommandStart() {
        this.cmdStart = textLength();
    }

    private AttributeSet setStyle(Color color) {
        return setStyle((Font) null, color);
    }

    private AttributeSet setStyle(Font font) {
        return setStyle(font, (Color) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AttributeSet setStyle(Font font, Color color) {
        return font != null ? setStyle(font.getFamily(), font.getSize(), color, font.isBold(), font.isItalic(), StyleConstants.isUnderline(getStyle())) : setStyle(null, -1, color);
    }

    private AttributeSet setStyle(String str, int i2, Color color) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        if (color != null) {
            StyleConstants.setForeground(simpleAttributeSet, color);
        }
        if (str != null) {
            StyleConstants.setFontFamily(simpleAttributeSet, str);
        }
        if (i2 != -1) {
            StyleConstants.setFontSize(simpleAttributeSet, i2);
        }
        setStyle((AttributeSet) simpleAttributeSet);
        return getStyle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AttributeSet setStyle(String str, int i2, Color color, boolean z, boolean z2, boolean z3) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        if (color != null) {
            StyleConstants.setForeground(simpleAttributeSet, color);
        }
        if (str != null) {
            StyleConstants.setFontFamily(simpleAttributeSet, str);
        }
        if (i2 != -1) {
            StyleConstants.setFontSize(simpleAttributeSet, i2);
        }
        StyleConstants.setBold(simpleAttributeSet, z);
        StyleConstants.setItalic(simpleAttributeSet, z2);
        StyleConstants.setUnderline(simpleAttributeSet, z3);
        setStyle((AttributeSet) simpleAttributeSet);
        return getStyle();
    }

    private void setStyle(AttributeSet attributeSet) {
        setStyle(attributeSet, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStyle(AttributeSet attributeSet, boolean z) {
        this.text.setCharacterAttributes(attributeSet, z);
    }

    private void showHistoryLine() {
        String str;
        if (this.histLine == 0) {
            str = this.startedLine;
        } else {
            Vector vector = this.history;
            str = (String) vector.elementAt(vector.size() - this.histLine);
        }
        replaceRange(str, this.cmdStart, textLength());
        this.text.setCaretPosition(textLength());
        this.text.repaint();
    }

    private int textLength() {
        return this.text.getDocument().getLength();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0053, code lost:
    
        if (r3.text.getCaretPosition() <= r3.cmdStart) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void type(java.awt.event.KeyEvent r4) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.util.JConsole.type(java.awt.event.KeyEvent):void");
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(CUT)) {
            this.text.cut();
        } else if (actionCommand.equals(COPY)) {
            this.text.copy();
        } else if (actionCommand.equals(PASTE)) {
            this.text.paste();
        }
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

    public InputStream getInputStream() {
        return this.in;
    }

    @Override // bsh.ConsoleInterface
    public PrintStream getOut() {
        return this.out;
    }

    public void keyPressed(KeyEvent keyEvent) {
        type(keyEvent);
        this.gotUp = false;
    }

    public void keyReleased(KeyEvent keyEvent) {
        this.gotUp = true;
        type(keyEvent);
    }

    public void keyTyped(KeyEvent keyEvent) {
        type(keyEvent);
    }

    public void mouseClicked(MouseEvent mouseEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.menu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            this.menu.show((Component) mouseEvent.getSource(), mouseEvent.getX(), mouseEvent.getY());
        }
        this.text.repaint();
    }

    @Override // bsh.ConsoleInterface
    public void print(final Object obj) {
        invokeAndWait(new Runnable() { // from class: bsh.util.JConsole.2
            @Override // java.lang.Runnable
            public void run() {
                JConsole.this.append(String.valueOf(obj));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
            }
        });
    }

    @Override // bsh.util.GUIConsoleInterface
    public void print(Object obj, Color color) {
        print(obj, null, color);
    }

    public void print(Object obj, Font font) {
        print(obj, font, null);
    }

    public void print(final Object obj, final Font font, final Color color) {
        invokeAndWait(new Runnable() { // from class: bsh.util.JConsole.4
            @Override // java.lang.Runnable
            public void run() {
                AttributeSet style = JConsole.this.getStyle();
                JConsole.this.setStyle(font, color);
                JConsole.this.append(String.valueOf(obj));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
                JConsole.this.setStyle(style, true);
            }
        });
    }

    public void print(Object obj, String str, int i2, Color color) {
        print(obj, str, i2, color, false, false, false);
    }

    public void print(final Object obj, final String str, final int i2, final Color color, final boolean z, final boolean z2, final boolean z3) {
        invokeAndWait(new Runnable() { // from class: bsh.util.JConsole.5
            @Override // java.lang.Runnable
            public void run() {
                AttributeSet style = JConsole.this.getStyle();
                JConsole.this.setStyle(str, i2, color, z, z2, z3);
                JConsole.this.append(String.valueOf(obj));
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
                JConsole.this.setStyle(style, true);
            }
        });
    }

    public void print(final Icon icon) {
        if (icon == null) {
            return;
        }
        invokeAndWait(new Runnable() { // from class: bsh.util.JConsole.3
            @Override // java.lang.Runnable
            public void run() {
                JConsole.this.text.insertIcon(icon);
                JConsole.this.resetCommandStart();
                JConsole.this.text.setCaretPosition(JConsole.this.cmdStart);
            }
        });
    }

    public void println() {
        print("\n");
        this.text.repaint();
    }

    @Override // bsh.ConsoleInterface
    public void println(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(obj));
        stringBuffer.append("\n");
        print(stringBuffer.toString());
        this.text.repaint();
    }

    public void println(Icon icon) {
        print(icon);
        println();
        this.text.repaint();
    }

    @Override // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().equals("lookAndFeel")) {
            SwingUtilities.updateComponentTreeUI(this.menu);
        }
    }

    public void requestFocus() {
        super.requestFocus();
        this.text.requestFocus();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            inPipeWatcher();
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Console: I/O Error: ");
            stringBuffer.append(e);
            stringBuffer.append("\n");
            print(stringBuffer.toString(), Color.red);
        }
    }

    public void setFont(Font font) {
        super.setFont(font);
        JTextPane jTextPane = this.text;
        if (jTextPane != null) {
            jTextPane.setFont(font);
        }
    }

    @Override // bsh.util.GUIConsoleInterface
    public void setNameCompletion(NameCompletion nameCompletion) {
        this.nameCompletion = nameCompletion;
    }

    @Override // bsh.util.GUIConsoleInterface
    public void setWaitFeedback(boolean z) {
        setCursor(Cursor.getPredefinedCursor(z ? 3 : 0));
    }

    public String toString() {
        return "BeanShell console";
    }
}
