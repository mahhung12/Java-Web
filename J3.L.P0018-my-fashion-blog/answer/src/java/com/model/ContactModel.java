/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.connect.DBContext;
import com.entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author thuongnnse05095
 */
public class ContactModel {

    private final DBContext db;

    public ContactModel() throws Exception {
        db = new DBContext();
    }

    public Contact getInfoPage() throws Exception {
        String query = "select * from Contact";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Contact output = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String imgLink = db.getResource() + rs.getString(4);
                String content = rs.getString(5);
                output = new Contact(id, name, age, imgLink, content);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.close(conn, ps, rs);
        }
        return output;
    }
}
