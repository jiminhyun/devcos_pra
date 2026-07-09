package homework;

public class I_MemberManager {
    private I_Member[] IMembers;
    private int memberCnt;

    public I_MemberManager(int capacity) {
        IMembers = new I_Member[capacity];
        memberCnt = 0;
    }

    public boolean isFull() {
        return memberCnt == IMembers.length;
    }

    public boolean existsEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if(email.equals(IMembers[i].getEmail())) return true;
        }
        return false;
    }

    public void add(I_Member m) {
        IMembers[memberCnt++] = m;
    }

    public int getCount() {
        return memberCnt;
    }

    public int getCapacity() {
        return IMembers.length;
    }

    public I_Member findByEmail(String email) {
        for (int i = 0; i < memberCnt; i++) {
            if(email.equals(IMembers[i].getEmail())) return IMembers[i];
        }
        return null;
    }

    public I_Member findByName(String name) {
        for (int i = 0; i < memberCnt; i++) {
            if(name.equals(IMembers[i].getName())) return IMembers[i];
        }
        return null;
    }

    public void printAll() {
        for (int i = 0; i < memberCnt; i++) {
            IMembers[i].printInfo();
        }
    }

    public boolean update(String email, String name, String newEmail, String phone) {
        I_Member m = findByEmail(email);
        if(m == null) return false;
        m.update(name, newEmail, phone);
        return true;
    }

    public boolean delete(String email) {
        int idx = -1;
        for (int i = 0; i < memberCnt; i++) {
            if(email.equals(IMembers[i].getEmail())) {
                idx = i;
                break;
            }
        }

        if(idx == -1) return false;
        for (int i = idx; i < memberCnt - 1; i++) {
            IMembers[i] = IMembers[i+1];
        }
        IMembers[--memberCnt] = null;
        return true;
    }
}
