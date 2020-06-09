/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_odbc_project;

/**
 *
 * @author DIPTO
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;
public class Table extends JFrame {
    
    JTable table;
    
    public Table(Object[][] data){
        setLayout(new FlowLayout());
        
        String[] columnNames = {"STD_NAME","STD_ID","STD_DEPARTMENT","STD_PHONE","STD_EMAIL"};

        table = new JTable(data,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Object[][] data= new Object[5][5];
        try{
            //Connection to Oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mydb","root");
        //Query
        String sql = "select * from student_table";
        //Query process and execute
        pst = conn.prepareStatement(sql);
        //returned to rs as some rows.
        rs = pst.executeQuery();
        int i=0;
        while(rs.next()){ 
//            System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                for(int j = 0; j<5; j++){
                    data[i][j] = rs.getString(j+1);
                }
                i++;
        }
//            System.out.println(data[3][3]);
        } catch(Exception e){
            System.out.println(e);
        }

        Table gui = new Table(data);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(600, 200);
        gui.setVisible(true);
        gui.setTitle("Student Table");
        
    }
}
