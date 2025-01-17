package com.model;
//Lib import
import com.db.Admins;
import com.db.Provider;

import java.sql.*;
// Packages import
//import oes.db.Admins;
//import oes.db.Provider;

public class AdminsDao {
    public static boolean doValidate(Admins ad) {
         
        boolean status = false;
        try
        {
            Connection con = Provider.getOracleConnection();
            String sql = "select * from admintable where username=? and password=?"; // SQL Query
	    PreparedStatement pst = con.prepareStatement(sql);
	    pst.setString(1, ad.getUsername());
	    pst.setString(2, ad.getPassword());
	    ResultSet rs = pst.executeQuery();
	    if(rs.next())
            {
                status = true;
	    }
	    else
            {
                status = false;
            }
        }catch(Exception e){System.out.println(e);}
	return status;
    }
}
