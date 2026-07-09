package homework;

public interface I_Member {

    public String getName();
    public String getEmail();
    public String getGrade();
    public String getPhone();
    public String getBenefit();
    public void update(String name, String email, String phone);

    default void printInfo() {
        System.out.println("["+getGrade()+"] "+getName()+" / "+getEmail()+" / "+getPhone()+" (혜택: "+getBenefit()+")");
    }


}
