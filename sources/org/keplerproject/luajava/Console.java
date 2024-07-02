package org.keplerproject.luajava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class Console {
    public static void main(String[] strArr) {
        try {
            LuaState newLuaState = LuaStateFactory.newLuaState();
            newLuaState.openLibs();
            if (strArr.length > 0) {
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    int LloadFile = newLuaState.LloadFile(strArr[i2]);
                    if (LloadFile == 0) {
                        LloadFile = newLuaState.pcall(0, 0, 0);
                    }
                    if (LloadFile != 0) {
                        throw new LuaException("Error on file: " + strArr[i2] + ". " + newLuaState.toString(-1));
                    }
                }
                return;
            }
            System.out.println("API Lua Java - console mode.");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream printStream = System.out;
            while (true) {
                printStream.print("> ");
                String readLine = bufferedReader.readLine();
                if (readLine == null || readLine.equals("exit")) {
                    break;
                }
                int LloadBuffer = newLuaState.LloadBuffer(readLine.getBytes(), "from console");
                if (LloadBuffer == 0) {
                    LloadBuffer = newLuaState.pcall(0, 0, 0);
                }
                if (LloadBuffer != 0) {
                    System.err.println("Error on line: " + readLine);
                    System.err.println(newLuaState.toString(-1));
                }
                printStream = System.out;
            }
            newLuaState.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
