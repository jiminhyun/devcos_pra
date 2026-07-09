package temp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    private int size = 0;
    private final int capacity;

    public MemberManager(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Connection connection() {

        String url = "jdbc:mysql://localhost:3306/temp";
        String user = "root";
        String password = "sdfae342436!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conn Success!");

            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Member m) {
        String query = "INSERT INTO member (grade, name, email, phone) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {

            pstmt.setString(1, m.getGrade());
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getEmail());
            pstmt.setString(4, m.getPhone());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member searchResult(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        return rs.getString("grade").equals("VIP") ?
                new VipMember(name, email, phone) : new NormalMember(name, email, phone);
    }

    public int getSize() {
        return size;
    }

    public Member findByEmail(String email) {
        String query = "SELECT grade, name, email, phone from member where email = ?";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return searchResult(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Member findByName(String name) {
        String query = "SELECT grade, name, email, phone from member where name = ?";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return searchResult(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean isFull(PricePlan plan) {
        return size >= plan.getCapacity();
    }

    public boolean existsEmail(String email) {
        String query = "SELECT grade, name, email, phone from member where email = ?";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int size() {
        String query = "select count(*) from member";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                size = rs.getInt(1);
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void printAll() { //없을 때 처리도
        String query = "select grade, name, email, phone from member";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                searchResult(rs).printInfo();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(String email, String name, String newEmail, String phone) {
        String query = "UPDATE member SET name = ?, email = ?, phone = ? WHERE email = ?";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {
            pstmt.setString(1, name);
            pstmt.setString(2, newEmail);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            return pstmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String email) {
        String query = "delete from member where email = ?";
        try (
                Connection conn = connection();
                PreparedStatement pstmt = conn.prepareStatement( query );
        ) {
            pstmt.setString(1, email);
            return pstmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
