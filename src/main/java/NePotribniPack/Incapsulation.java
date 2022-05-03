package NePotribniPack;

import java.util.Objects;

public class Incapsulation {
    public static void main(String[] args) {
        Object obj = new Object();
        Object obj2 = new Object();
        obj.equals(obj2);
        equals(obj,obj2);
    }
    public static boolean equals(Object a, Object b) {
        return a.equals(b);

    }
    class A{
     Integer s = 1488;

        public boolean equals(A a) {
            return this.s==a.s;
        }
        public static boolean equals(A a1,A a2){
            return a1.s == a2.s;
        }

    }

}
