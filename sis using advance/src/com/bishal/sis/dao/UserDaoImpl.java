package com.bishal.sis.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.bishal.sis.dto.UserDto;
import com.bishal.sis.util.DbUtil;

public class UserDaoImpl implements UserDao {
	PreparedStatement ps=null;
	@Override
	public void saveUserInfo(UserDto userDto) {
		String sql="insert into users(username,password,first_name,last_name,email,gender,dob,image_url) values(?,?,?,?,?,?,?,?) ";
		//System.out.println("userDto.getDob() "+userDto.getDob());
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, userDto.getUserName());
			ps.setString(2, userDto.getPassword());
			ps.setString(3, userDto.getFirstName());
			ps.setString(4, userDto.getLastName());
			ps.setString(5, userDto.getEmail());
			ps.setString(6, userDto.getGender());
			ps.setDate(7,new Date(userDto.getDob().getTime()));
			ps.setString(8, userDto.getImageUrl());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void updateUserInfo(UserDto userDto) {
		String sql="update users set username=?,password=?,first_name=?,last_name=?,email=?,gender=?,dob=? ,image_url=? where user_id=? ";
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, userDto.getUserName());
			ps.setString(2, userDto.getPassword());
			ps.setString(3, userDto.getFirstName());
			ps.setString(4, userDto.getLastName());
			ps.setString(5, userDto.getEmail());
			ps.setString(6, userDto.getGender());
			ps.setDate(7, new Date(userDto.getDob().getTime()));
			
			ps.setString(8, userDto.getImageUrl());
			ps.setInt(9, userDto.getId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteUserInfo(int id) {
		String sql="delete from users where user_id=?";
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public List<UserDto> getAllUserInfo() {
		List <UserDto> userList=new ArrayList<>();
		String sql="select * from users";
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserDto userDto=new UserDto();
				userDto.setId(rs.getInt("user_id"));
				userDto.setFirstName(rs.getString("first_name"));
				userDto.setLastName(rs.getString("last_name"));
				userDto.setUserName(rs.getString("username"));
				userDto.setPassword(rs.getString("password"));
				userDto.setEmail(rs.getString("email"));
				userDto.setGender(rs.getString("gender"));
				userDto.setDob(rs.getDate("dob"));
				userList.add(userDto);
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return userList;
	}
	@Override
	public UserDto getUserByunameAndpass(String uname, String pass) {
		String sql="select user_id,username,password from users where username=? and password=?";
		UserDto userDto=new UserDto();
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1,uname);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				userDto.setId(rs.getInt("user_id"));
				
				userDto.setUserName(rs.getString("username"));
				userDto.setPassword(rs.getString("password"));
				
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return userDto;
	}
		
		
	
	@Override
	public UserDto grtinfoById(int id) {
		String sql="select * from users where user_id=?";
		UserDto userDto=new UserDto();
		try {
			ps=DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			//System.out.println(rs);
			while(rs.next()) {
				
				userDto.setId(rs.getInt("user_id"));
				userDto.setFirstName(rs.getString("first_name"));
				userDto.setLastName(rs.getString("last_name"));
				userDto.setUserName(rs.getString("username"));
				userDto.setPassword(rs.getString("password"));
				userDto.setEmail(rs.getString("email"));
				userDto.setGender(rs.getString("gender"));
				userDto.setDob(rs.getDate("dob"));
				
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return userDto;
	}
	@Override
	public String getImageUrlById(int id) {
		String sql = "select image_url from users where user_id=?";
		String imageUrl = "";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				imageUrl = rs.getString("image_url");
				//System.out.println("imageUrl "+imageUrl);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return imageUrl;
	}
	
}
