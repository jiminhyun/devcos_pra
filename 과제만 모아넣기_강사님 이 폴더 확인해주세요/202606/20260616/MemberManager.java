package temp;

import homework.I_Member;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    private List<Member> members = new ArrayList<>();
    private int size = 0;

    public void add(Member m) {
        members.add(m);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Member findByEmail(String email) {
        for(Member m : members) {
            if(email.equals(m.getEmail())) return m;
        }
        return null;
    }

    public Member findByName(String name) {
        for(Member m : members) {
            if(name.equals(m.getName())) return m;
        }
        return null;
    }

    public boolean isFull(PricePlan plan) {
        return size == plan.getCapacity();
    }

    public boolean existsEmail(String email) {
        for(Member m : members) {
            if(email.equals(m.getEmail())) return true;
        }
        return false;
    }

    public void printAll() {
        for(Member m : members) {
            m.printInfo();
        }
    }

    public boolean update(String email, String name, String newEmail, String phone) {
        Member m = findByEmail(email);
        if(m == null) return false;
        m.update(name, newEmail, phone);
        return true;
    }

    public boolean delete(String email) {
        int idx = -1;

        for (Member m: members) {
            idx++;
            if(email.equals(m.getEmail())) {
                break;
            }
        }

        if(idx == -1) return false;
        members.remove(members.get(idx));
        size--;
        return true;
    }
}
