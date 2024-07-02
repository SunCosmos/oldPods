package android.support.v4.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

/* loaded from: lib/Mus.dex */
class DatagramSocketWrapper extends Socket {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DatagramSocketWrapper(DatagramSocket datagramSocket, FileDescriptor fileDescriptor) throws SocketException {
        super(new DatagramSocketImplWrapper(datagramSocket, fileDescriptor));
    }

    /* loaded from: lib/Mus.dex */
    private static class DatagramSocketImplWrapper extends SocketImpl {
        DatagramSocketImplWrapper(DatagramSocket datagramSocket, FileDescriptor fileDescriptor) {
            this.localport = datagramSocket.getLocalPort();
            this.fd = fileDescriptor;
        }

        @Override // java.net.SocketImpl
        protected void accept(SocketImpl socketImpl) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected int available() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void bind(InetAddress inetAddress, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void close() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(String str, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(InetAddress inetAddress, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void create(boolean z) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected InputStream getInputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void listen(int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void connect(SocketAddress socketAddress, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketImpl
        protected void sendUrgentData(int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public Object getOption(int i2) throws SocketException {
            throw new UnsupportedOperationException();
        }

        @Override // java.net.SocketOptions
        public void setOption(int i2, Object obj) throws SocketException {
            throw new UnsupportedOperationException();
        }
    }
}
