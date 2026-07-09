package homework;

public class MemberRepository {
    //캡슐화
    private Member[] members;
    private int totalCnt=0; // 회원 총 가용 수
    private int memberCnt = 0; // 실 회원 수

    public int getTotalCnt() {
        return this.totalCnt;
    }

    public int getMemberCnt() {
        return this.memberCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt*10;
        this.members = new Member[this.totalCnt];
    }

    public boolean checkEmail(Member member) {
        for (int i = 0; i < this.memberCnt; i++) {
            if (member.getEmail().equals(this.members[i].getEmail())) return true;
        }
        return false;
    }

    public Member checkEmail(String email) {
        for (int i = 0; i < this.memberCnt; i++) {
            if (email.equals(this.members[i].getEmail())) return this.members[i];
        }
        return null;
    }

    public boolean checkTotalCnt() {
        return this.memberCnt != this.totalCnt;
    }

    public void memberAdd(Member member) {
        if(!checkTotalCnt()) {
            System.out.println("회원이 가득찼습니다.");
        }else if (!checkEmail(member)) {
            this.members[memberCnt] = member;
            memberCnt++;
        }else{
            System.out.println("이메일이 중복됩니다.");
        }
    }

    public void selectEmail(String email) {
        for (int i = 0; i < this.memberCnt; i++) {
            if (email.equals(this.members[i].getEmail())) {
                System.out.println("이름: "+this.members[i].getName() +" 이메일: "+this.members[i].getEmail()+" 전화번호: "+this.members[i].getPhoneNumber());
                return;
            }
        }
        System.out.println("회원정보 없습니다.");
    }

    public void selectName(String name) {
        for (int i = 0; i < this.memberCnt; i++) {
            if (name.equals(this.members[i].getName())) {
                System.out.println("이름: "+this.members[i].getName() +" 이메일: "+this.members[i].getEmail()+" 전화번호: "+this.members[i].getPhoneNumber());
                if(i==this.memberCnt-1) return;
            }
        }
        System.out.println("회원정보 없습니다.");
    }

    public void viewAll(){
        for (int i = 0; i < this.memberCnt; i++) {
            System.out.println("이름: "+this.members[i].getName() +" 이메일: "+this.members[i].getEmail()+" 전화번호: "+this.members[i].getPhoneNumber());
        }
    }

    void deleteMember(String email) {
        for (int i = 0; i < this.memberCnt; i++) {
            if (email.equals(this.members[i].getEmail())) {
                for (int j = i; j < this.memberCnt - 1; j++) {
                    this.members[j] = this.members[j + 1];
                }

                this.members[this.memberCnt - 1] = null;

                this.memberCnt--;

                System.out.println("회원이 삭제되었습니다.");
                return;
            }
        }
        System.out.println("회원정보가 없습니다.");
    }

}
