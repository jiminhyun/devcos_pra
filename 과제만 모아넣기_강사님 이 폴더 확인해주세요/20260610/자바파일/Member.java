package homework;

public class Member {
    //캡슐화
    private String name;
    private String email;
    private String phoneNumber;

    public Member(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateInfo(Member member) {
        this.name = member.name;
        this.email = member.email;
        this.phoneNumber = member.phoneNumber;
    }

    // Getter 메서드들 (필요한 경우에만 제공)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
}
