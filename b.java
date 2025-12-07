import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        long value;   // 当前可取金额
        long d;       // 增长速度

        Node(long value, long d) {
            this.value = value;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int T = fs.nextInt();

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = fs.nextInt();
            int t = fs.nextInt();

            long[] a = new long[n];
            long[] d = new long[n];

            for (int i = 0; i < n; i++) a[i] = fs.nextLong();
            for (int i = 0; i < n; i++) d[i] = fs.nextLong();

            // Java 大顶堆：value 从大到小排序
            PriorityQueue<Node> pq = new PriorityQueue<>(
                (x, y) -> Long.compare(y.value, x.value)
            );

            // 初始化所有奖池当前金额
            for (int i = 0; i < n; i++) {
                pq.offer(new Node(a[i], d[i]));
            }

            long total = 0;

            // 每秒选择最大的奖池
            for (int time = 1; time <= t; time++) {
                Node cur = pq.poll();
                total += cur.value;

                // 下次可取金额
                pq.offer(new Node(cur.d * time, cur.d));
            }

            sb.append(total).append("\n");
        }

        System.out.print(sb.toString());
    }

    // 高速输入
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = c - '0';
            while ((c = read()) > ' ') {
                val = val * 10 + (c - '0');
            }
            return val * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}