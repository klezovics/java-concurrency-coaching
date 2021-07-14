package com.klezovich.concurrency.coordination;

import java.math.BigInteger;

public class ComplexCalculation {

    public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;

        PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        result = t1.getResult().add(t2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {

            if (power.intValue() == 0) {
                return;
            }

            if (power.intValue() == 1) {
                result = base;
                return;
            }

            BigInteger tmp = base;
            for (long ii = 1; ii <= power.longValue() - 1; ii++) {
                tmp = tmp.multiply(base);
            }

            result = tmp;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}