package bsh;

import java.io.PrintStream;
import java.io.Reader;

/* loaded from: classes.dex */
public interface ConsoleInterface {
    void error(Object obj);

    PrintStream getErr();

    Reader getIn();

    PrintStream getOut();

    void print(Object obj);

    void println(Object obj);
}
