/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.connect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author win
 */
public class ViewModel {

    private final DBContext db;

    public ViewModel() throws Exception {
        db = new DBContext();
    }

    public int getView() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            String query = "select * from [View]";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("viewed");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.close(conn, ps, rs);
        }
        return count;
    }

    public void updatedView() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "Update [View]\n"
                + "set viewed = viewed + 1";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            db.close(conn, ps, rs);
        }
    }
}
