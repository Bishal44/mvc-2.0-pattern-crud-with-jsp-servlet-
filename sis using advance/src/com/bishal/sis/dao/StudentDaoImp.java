package com.bishal.sis.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bishal.sis.dto.StudentDto;
import com.bishal.sis.util.DbUtil;


public class StudentDaoImp implements StudentDao {
	PreparedStatement ps = null;

	@Override
	public void saveStudentInfo(StudentDto studentdto) {
		String sql = "insert into student_details(student_name,college_name,email,roll,gender,subject,departments,dob,image_url)values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, studentdto.getStudentName());
			ps.setString(2, studentdto.getCollegeName());
			ps.setString(3, studentdto.getEmail());
			ps.setInt(4, studentdto.getRoll());
			ps.setString(5, studentdto.getGender());
			ps.setString(6, studentdto.getSubject());
			ps.setString(7, studentdto.getDepartment());
			ps.setDate(8, new java.sql.Date(studentdto.getDob().getTime()));
			//ps.setInt(9, studentdto.getId());
			ps.setString(9, studentdto.getImageUrl());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public List<StudentDto> getAllStudentInfo() {
		List<StudentDto> studentlist = new ArrayList<>();
		String sql = "select * from student_details";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentDto studentdto = new StudentDto();
				studentdto.setId(rs.getInt("id"));
				studentdto.setStudentName(rs.getString("student_name"));
				studentdto.setCollegeName(rs.getString("college_name"));
				studentdto.setDepartment(rs.getString("departments"));
				studentdto.setEmail(rs.getString("email"));
				studentdto.setGender(rs.getString("gender"));
				studentdto.setRoll(rs.getInt("roll"));
				studentdto.setSubject(rs.getString("subject"));
				studentdto.setDob(rs.getDate("dob"));

				studentlist.add(studentdto);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return studentlist;

	}

	@Override
	public void updateStudentInfo(StudentDto studentdto) {

		String sql = "update student_details set student_name=?,college_name=?,email=?,roll=?,gender=?,subject=?,departments=?,dob=?,image_url=? where id=?";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setString(1, studentdto.getStudentName());
			ps.setString(2, studentdto.getCollegeName());
			ps.setString(3, studentdto.getEmail());
			ps.setInt(4, studentdto.getRoll());
			ps.setString(5, studentdto.getGender());
			ps.setString(6, studentdto.getSubject());
			ps.setString(7, studentdto.getDepartment());
			ps.setDate(8, new java.sql.Date(studentdto.getDob().getTime()));
		
			ps.setString(9, studentdto.getImageUrl());
			ps.setInt(10, studentdto.getId());
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}

	}

	@Override
	public void deleteStudentInfo(int id) {

		String sql = "delete from student_details where id=?";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public StudentDto getStudentInfobyId(int id) {
		StudentDto studentdto = new StudentDto();
		String sql = "select * from student_details where id=?";
		try {
			ps = DbUtil.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				studentdto.setId(rs.getInt("id"));
				studentdto.setStudentName(rs.getString("student_name"));
				studentdto.setCollegeName(rs.getString("college_name"));
				studentdto.setDepartment(rs.getString("departments"));
				studentdto.setEmail(rs.getString("email"));
				studentdto.setGender(rs.getString("gender"));
				studentdto.setRoll(rs.getInt("roll"));
				studentdto.setSubject(rs.getString("subject"));
				studentdto.setDob(rs.getDate("dob"));

			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return studentdto;
	}

	@Override
	public String getImageUrlById(int id) {
		String sql = "select image_url from student_details where id=?";
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
