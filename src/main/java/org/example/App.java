package org.example;
 import javax.xml.transform.Result;
 import java.sql.*;
// Java database connectivity
/*
* 1. import ---> java.sql
* 2. load and register the driver ---> com.mysql.jdbc.Driver
* 3. Create connection ---> Connection interface
* 4. create a statement ---> Statement
* 5. execute the query
* 6. process the results
* 7. close
* */
 import java.sql.*;

public class App {
    static String url = "jdbc:mysql://localhost:3306/aliens"; // Also fixed double slash
    static String uname = "root";
    static String pass = "Ribu@2003";
    static String query = "select * from Students";
    static String query1 = "insert into Students values(?,?)";
    static int Id = 8;
    static String username = "Arundhati";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create an instance of App to access non-static variables
        App app = new App();

        Connection con = DriverManager.getConnection(url, uname, pass);
        PreparedStatement st = con.prepareStatement(query1);

        st.setInt(1,Id);
        st.setString(2,username);

        int count = st.executeUpdate();
        System.out.println(count + " " + "rows are affected");

        ResultSet resultSet = st.executeQuery(query);
        String userdata = "";
        while (resultSet.next())
        {
            userdata= resultSet.getInt(1) + " : "+ resultSet.getString(2);
            System.out.println(userdata);
        }
        st.close();
        con.close();
    }
}

