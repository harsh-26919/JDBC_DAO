package org.example;

import java.sql.*;

import org.w3c.dom.ls.LSOutput;

public class JdbcDaoDemo
{
    public static void main (String argss[])
    {
        StudentDAO dao = new StudentDAO();
        Student s2 = new Student();
        s2.Id=9;
        s2.sname="Archana";
        dao.connect();
        dao.addStudent(s2);///Adding a new student in the database//
        dao.connect();
        Student s1 = dao.getStudent(9); //Fetching data
        System.out.println(s1.sname);



    }
}

class StudentDAO
{
    Connection con =null;
    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aliens","root","Ribu@2003");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public Student getStudent(int Id)
    {

        try {
            Student s = new Student();
            s.Id=Id;
            String query = "select name from Students where ID="+Id;
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String name =rs.getString(1);
            s.sname = name;
            return s;
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void addStudent(Student s)
    {
        PreparedStatement pst;
        String query="insert into Students values (?,?)";
        try {
            pst= con.prepareStatement(query);
            pst.setInt(1,s.Id);
            pst.setString(2,s.sname);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}

class Student
{
    int Id;
    String sname;
}
