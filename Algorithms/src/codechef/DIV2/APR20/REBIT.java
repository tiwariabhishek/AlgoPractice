package Algorithms.src.codechef.DIV2.APR20;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;


class Value {

    long[] q;

    public Value(boolean initialise) {
        q = new long[4];
        if(initialise) {
            Arrays.fill(q, 748683265);
        }
    }
}

class StringProcessor {

    private static final long mod = 998244353l;

    //     '0', '1', 'a' or 'A'
    public static int get0Index() {
        return 0;
    }

    public static int get1Index() {
        return 1;
    }

    public static int getaIndex() {
        return 2;
    }

    public static int getAIndex() {
        return 3;
    }

    public static Value process(String expression) {

        char[] tokens = expression.toCharArray();

        Stack<Character> operators = new Stack<>();
        Stack<Value> values = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == '#') values.push(new Value(true));
            else if (tokens[i] == '(') operators.push(tokens[i]);
            else if (tokens[i] == ')') {
                while (operators.peek() != '(') values.push(applyOp(operators.pop(), values.pop(), values.pop()));
                operators.pop();
            } else if (tokens[i] == '^' || tokens[i] == '|' || tokens[i] == '&') operators.push(tokens[i]);
        }

        return values.pop();
    }

    private static Value calculateXOR(Value v1, Value v2) {
        Value result = new Value(false);

        result.q[getaIndex()] = ((v1.q[getaIndex()] * v2.q[get0Index()]) % mod +
                (v1.q[getAIndex()] * v2.q[get1Index()]) % mod +
                (v2.q[getaIndex()] * v1.q[get0Index()]) % mod +
                (v2.q[getAIndex()] * v1.q[get1Index()]) % mod) % mod;

        result.q[getAIndex()] = ((v1.q[getAIndex()] * v2.q[get0Index()]) % mod +
                (v1.q[getaIndex()] * v2.q[get1Index()]) % mod +
                (v2.q[getAIndex()] * v1.q[get0Index()]) % mod +
                (v2.q[getaIndex()] * v1.q[get1Index()]) % mod) % mod;

        result.q[get0Index()] = ((v1.q[getAIndex()] * v2.q[getAIndex()])%mod +
                (v1.q[getaIndex()] * v2.q[getaIndex()])%mod +
                (v1.q[get0Index()] * v2.q[get0Index()])%mod +
                (v1.q[get1Index()] * v2.q[get1Index()])%mod) % mod;

        result.q[get1Index()] = ((v1.q[getAIndex()] * v2.q[getaIndex()])%mod +
                (v1.q[getaIndex()] * v2.q[getAIndex()])%mod +
                (v1.q[get0Index()] * v2.q[get1Index()])%mod +
                (v1.q[get1Index()] * v2.q[get0Index()])%mod) % mod;

        return result;
    }

    private static Value calculateAND(Value v1, Value v2) {
        Value result = new Value(false);

        result.q[getaIndex()] = ((v1.q[getaIndex()] * v2.q[getaIndex()]) % mod +
                (v1.q[getaIndex()] * v2.q[get1Index()]) % mod +
                (v1.q[get1Index()] * v2.q[getaIndex()]) % mod) % mod;

        result.q[getAIndex()] = ((v1.q[getAIndex()] * v2.q[getAIndex()]) % mod +
                (v1.q[getAIndex()] * v2.q[get1Index()]) % mod +
                (v1.q[get1Index()] * v2.q[getAIndex()]) % mod) % mod;

        result.q[get0Index()] = ((v1.q[getAIndex()] * v2.q[getaIndex()])%mod +
                (v1.q[getaIndex()] * v2.q[getAIndex()])%mod +
                (v1.q[get0Index()] * v2.q[get0Index()])%mod +
                (v1.q[get1Index()] * v2.q[get0Index()])%mod +
                (v1.q[get0Index()] * v2.q[get1Index()])%mod +
                (v1.q[getaIndex()] * v2.q[get0Index()])%mod +
                (v1.q[getAIndex()] * v2.q[get0Index()])%mod +
                (v1.q[get0Index()] * v2.q[getaIndex()])%mod +
                (v1.q[get0Index()] * v2.q[getAIndex()])%mod) % mod;

        result.q[get1Index()] = (v1.q[get1Index()] * v2.q[get1Index()])%mod;

        return result;
    }

    private static Value calculateOR(Value v1, Value v2) {
        Value result = new Value(false);

        result.q[getaIndex()] = ((v1.q[getaIndex()] * v2.q[getaIndex()]) % mod +
                (v1.q[getaIndex()] * v2.q[get0Index()]) % mod +
                (v1.q[get0Index()] * v2.q[getaIndex()]) % mod) % mod;

        result.q[getAIndex()] = ((v1.q[getAIndex()] * v2.q[getAIndex()]) % mod +
                (v1.q[getAIndex()] * v2.q[get0Index()]) % mod +
                (v1.q[get0Index()] * v2.q[getAIndex()]) % mod) % mod;

        result.q[get1Index()] = ((v1.q[getAIndex()] * v2.q[getaIndex()])%mod +
                (v1.q[getaIndex()] * v2.q[getAIndex()])%mod +
                (v1.q[get0Index()] * v2.q[get1Index()])%mod +
                (v1.q[get1Index()] * v2.q[get0Index()])%mod +
                (v1.q[get1Index()] * v2.q[get1Index()])%mod +
                (v1.q[getaIndex()] * v2.q[get1Index()])%mod +
                (v1.q[getAIndex()] * v2.q[get1Index()])%mod +
                (v1.q[get1Index()] * v2.q[getaIndex()])%mod +
                (v1.q[get1Index()] * v2.q[getAIndex()])%mod) % mod;

        result.q[get0Index()] = (v1.q[get0Index()] * v2.q[get0Index()])%mod;

        return result;
    }

    private static Value applyOp(char op, Value v1, Value v2) {
        switch (op) {
            case '^':
                return calculateXOR(v1, v2);
            case '&':
                return calculateAND(v1, v2);
            case '|':
                return calculateOR(v1, v2);
        }
        return null;
    }
}


public class REBIT {

    public static void main(String args[]) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out);
            String eol = System.getProperty("line.separator");
            byte[] eolb = eol.getBytes();
            byte[] space = " ".getBytes();
            String str[] = bufferedReader.readLine().split(" ");
            int t = Integer.parseInt(str[0]);
            while (t-- > 0) {
                str = bufferedReader.readLine().split(" ");
                Value value = StringProcessor.process(str[0]);

                for(int i = 0; i < 4; i++) {
                    bufferedOutputStream.write(Long.toString(value.q[i]).getBytes());
                    bufferedOutputStream.write(i == 3 ? eolb :space);
                }
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
