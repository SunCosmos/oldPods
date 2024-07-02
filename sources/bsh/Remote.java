package bsh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

/* loaded from: classes.dex */
public class Remote {
    static String doBsh(String str, String str2) {
        try {
            String substring = str.substring(6);
            int indexOf = substring.indexOf(":");
            String substring2 = substring.substring(0, indexOf);
            String substring3 = substring.substring(indexOf + 1, substring.length());
            try {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Connecting to host : ");
                stringBuffer.append(substring2);
                stringBuffer.append(" at port : ");
                stringBuffer.append(substring3);
                printStream.println(stringBuffer.toString());
                Socket socket = new Socket(substring2, Integer.parseInt(substring3) + 1);
                OutputStream outputStream = socket.getOutputStream();
                InputStream inputStream = socket.getInputStream();
                sendLine(str2, outputStream);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return "1";
                    }
                    System.out.println(readLine);
                }
            } catch (Exception e) {
                PrintStream printStream2 = System.err;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Error communicating with server: ");
                stringBuffer2.append(e);
                printStream2.println(stringBuffer2.toString());
                return "-1";
            }
        } catch (Exception e2) {
            PrintStream printStream3 = System.err;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Bad URL: ");
            stringBuffer3.append(str);
            stringBuffer3.append(": ");
            stringBuffer3.append(e2);
            printStream3.println(stringBuffer3.toString());
            return "-1";
        }
    }

    static String doHttp(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("bsh.client=Remote");
        stringBuffer.append("&bsh.script=");
        stringBuffer.append(URLEncoder.encode(str2));
        String stringBuffer2 = stringBuffer.toString();
        String str3 = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter((Writer) new OutputStreamWriter(httpURLConnection.getOutputStream(), "8859_1"), true);
            printWriter.print(stringBuffer2);
            printWriter.flush();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Error, HTTP response: ");
                stringBuffer3.append(responseCode);
                printStream.println(stringBuffer3.toString());
            }
            str3 = httpURLConnection.getHeaderField("Bsh-Return");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                System.out.println(readLine);
            }
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Return Value: ");
            stringBuffer4.append(str3);
            printStream2.println(stringBuffer4.toString());
        } catch (MalformedURLException | IOException e) {
            System.out.println(e);
        }
        return str3;
    }

    public static int eval(String str, String str2) {
        String doBsh;
        if (str.startsWith("http:")) {
            doBsh = doHttp(str, str2);
        } else {
            if (!str.startsWith("bsh:")) {
                throw new IOException("Unrecognized URL type.Scheme must be http:// or bsh://");
            }
            doBsh = doBsh(str, str2);
        }
        try {
            return Integer.parseInt(doBsh);
        } catch (Exception unused) {
            return 0;
        }
    }

    static String getFile(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuffer.toString();
            }
            stringBuffer.append(readLine);
            stringBuffer.append("\n");
        }
    }

    public static void main(String[] strArr) {
        if (strArr.length < 2) {
            System.out.println("usage: Remote URL(http|bsh) file [ file ] ... ");
            System.exit(1);
        }
        System.exit(eval(strArr[0], getFile(strArr[1])));
    }

    private static void sendLine(String str, OutputStream outputStream) {
        outputStream.write(str.getBytes());
        outputStream.flush();
    }
}
