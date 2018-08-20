package com.event.eagle.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.event.eagle.dao.DB;

public class UserService {

	public String getUserName(String id) {
		String result = "";
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from users where id='" + id + "'";

			System.out.println(SQL);

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {
				result = resultSet.getString("firstname") + " " + resultSet.getString("lastname");
			} else {
				return result;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;

	}

	public int verifyUserLogin(String username, String password) {
		int result = -1;
		try {
			Connection con = DB.getConnection();
			Statement statement = con.createStatement();
			String SQL = "select * from users where email='" + username + "' and password = '" + password + "'";

			System.out.println(SQL);

			ResultSet resultSet = statement.executeQuery(SQL);

			if (resultSet.next()) {
				result = resultSet.getInt("id");
			} else {
				result = -1;
			}
			con.close();
		} catch (Exception e) {
			result = -1;
		}
		return result;

	}

}
