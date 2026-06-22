package temp;

import java.lang.classfile.Interfaces;

public class Ocp {
    class DiscountCalculator {
        int calc(String grade, int price) {
            if (grade.equals("GOLD"))      return price * 90 / 100;
            else if (grade.equals("VIP"))  return price * 80 / 100;
            else                           return price;
        }
    }

    interface DiscountPolicy {
        int discount(int price);
    }

    class BasicDiscount implements DiscountPolicy {
        public int discount(int price){
            return price;
        };
    }

    class GoldDiscount implements DiscountPolicy {
        public int discount(int price){
            return price * 90 /100;
        };
    }

    class VipDiscount implements DiscountPolicy {
        public int discount(int price){
            return price * 80 /100;
        };
    }

    class SilverDiscount implements DiscountPolicy {
        public int discount(int price){
            return price;
        };
    }
}
