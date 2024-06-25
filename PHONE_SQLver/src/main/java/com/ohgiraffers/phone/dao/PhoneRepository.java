package com.ohgiraffers.phone.dao;

import com.ohgiraffers.phone.dto.PhoneDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class PhoneRepository {

    private Properties pros = new Properties();
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt2 = null;
    private ResultSet rset = null;

    public PhoneRepository() {
        try {
           this.pros.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/phone/mapper/phone-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public PhoneDTO phoneFindByName(String name) {
        String query = pros.getProperty("FindByName");
        con= getConnection();
        PhoneDTO emp = null;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                emp = new PhoneDTO();
                emp.setUserNO(rset.getInt("USER_NO"));
                emp.setUserName(rset.getString("USER_NAME"));
                emp.setUserAddress(rset.getString("USER_ADDRESS"));
                emp.setCallName(rset.getString("CALL_NAME"));
                emp.setCallNumber(rset.getString("CALL_NUMBER"));
                emp.setMainCall(rset.getString("MAIN_CALL"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
//        System.out.print(emp);
        return emp;
    }



    public ArrayList phoneViewAll() {
        ArrayList arrayList = new ArrayList();
        String query = pros.getProperty("ViewAll");
        con = getConnection();
        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            while(rset.next()) {
                PhoneDTO emp = new PhoneDTO();
                emp.setUserNO(rset.getInt("USER_NO"));
                emp.setUserName(rset.getString("USER_NAME"));
                emp.setUserAddress(rset.getString("USER_ADDRESS"));
                emp.setCallName(rset.getString("CALL_NAME"));
                emp.setCallNumber(rset.getString("CALL_NUMBER"));
                emp.setMainCall(rset.getString("MAIN_CALL"));
                arrayList.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        return arrayList;

    }

    public int phoneInsert(PhoneDTO emp) {

        String query1 = pros.getProperty("phoneInsert1");
        String query2 = pros.getProperty("phoneInsert2");
        con = getConnection();
        int result = 0;
        int result1 = 0;

        try {
            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, emp.getUserName());
            pstmt.setString(2, emp.getUserAddress());
            result = pstmt.executeUpdate();

            pstmt2 = con.prepareStatement(query2);
            pstmt2.setString(1, emp.getCallName());
            pstmt2.setString(2, emp.getCallNumber());
            pstmt2.setString(3, emp.getMainCall());
            result1 = pstmt2.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(pstmt);
            close(pstmt2);
        }
        return result + result1;
    }

    public int phoneDelete(String name){
        String query = pros.getProperty("phoneDelete");
        con = getConnection();
        int result = 0;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            result = pstmt.executeUpdate();     // 정수형으로 결과 반환?     Integer pars
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(pstmt);
        }
        return result;
    }

    public int phoneModify(PhoneDTO phone) {

        String query1 = pros.getProperty("phoneModify1");
        con = getConnection();
        int result1 = 0;
        try {
            pstmt = con.prepareStatement(query1);
            pstmt.setString(1, phone.getUserName());
            pstmt.setString(2, phone.getUserAddress());
            pstmt.setInt(3, phone.getUserNO());

            result1 = pstmt.executeUpdate();



            String query2 = pros.getProperty("phoneModify2");
            con = getConnection();
            int result2 = 0;

            pstmt = con.prepareStatement(query2);
            pstmt.setString(1, phone.getCallNumber());
            pstmt.setInt(2, phone.getUserNO());

            result2 = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        return 0;


    }
}
