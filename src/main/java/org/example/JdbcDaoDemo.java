package org.example;

import java.sql.*;

import org.w3c.dom.ls.LSOutput;

public class JdbcDaoDemo
{
    public static void main (String argss[])
    {
        StudentDAO dao = new StudentDAO();
        Student s1 = dao.getStudent(5);
        System.out.println(s1.sname);
    }
}

class StudentDAO
{
    public Student getStudent(int Id)
    {

        try {
            Student s = new Student();
            s.Id=Id;
            String query = "select name from Students where ID="+Id;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aliens","root","Ribu@2003");
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery(query);
            rs.next();
            String name =rs.getString(1);
            s.sname = name;

            return s;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}

class Student
{
    int Id;
    String sname;
}
