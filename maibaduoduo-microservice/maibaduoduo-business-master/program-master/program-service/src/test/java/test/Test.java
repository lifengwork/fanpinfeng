package test;

import java.util.Random;

public class Test {
    @org.junit.Test
    public void tst(){
        new Random(5).nextInt();
        int ii = Runtime.getRuntime().availableProcessors() << 1;
    }
}
