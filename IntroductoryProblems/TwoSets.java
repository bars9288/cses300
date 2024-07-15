mport java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Two Sets

public class Main {

    public static void main(String[] args)
            throws IOException
    {
        Reader sc = new Reader();
        long n = sc.nextLong();
        StringBuilder sb = new StringBuilder();
        if (n == 0
                || n == 1
                || n == 2
                || ((n%2 == 1) && ((n - 3) % 4 != 0)) // Check even
                || ((n%2 == 0) && ((n) % 4 != 0)) // Check odd
        ) {System.out.println("NO"); return;}

        sb.append("YES" + "\n");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        if (n%2 != 0) {
            list1.add(1);
            list1.add(2);
            list2.add(3);
            for (int i = 4; i+3 <= n ; i+=4 ) {
                list1.add(i);
                list1.add(i + 3);
                list2.add(i + 1);
                list2.add(i + 2);
            }
        } else {
            for (int i = 1; i <= n ; i+=4 ) {
                list1.add(i);
                list2.add(i + 1);
                list2.add(i + 2);
                list1.add(i + 3);
            }
        }

        //1st array
        sb.append(list1.size() + "\n");
        for (int i = 0; i < list1.size(); i++) {
            if (i == list1.size() - 1) {
                sb.append(list1.get(i) + "\n");
                break;
            }
            sb.append(list1.get(i) + " ");
        }

        //2nd array
        sb.append(list2.size() + "\n");
        for (int i = 0; i < list2.size(); i++) {
            if (i == list2.size() - 1) {
                sb.append(list2.get(i) + "\n");
                break;
            }
            sb.append(list2.get(i) + " ");
        }
        System.out.println(sb.toString());
    }

    // ^^^^^^^^ Down sprcial force Input-Output code ^^^^^^^^

    static class Reader {
        final private int BUFFER_SIZE = 1 << 18;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[256]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
