package temp;

public enum PricePlan {
    LITE(10),
    BASIC(20),
    PREMIUM(30);

    final int capacity;

    PricePlan(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public static PricePlan selectPlan(int check) {
        switch (check) {
            case 1: return LITE;
            case 2: return BASIC;
            case 3: return PREMIUM;
            default: return null;
        }
    }
}
