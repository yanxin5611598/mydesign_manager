package com.yx.mydesign.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yx.mydesign.bean.Device;
import com.yx.mydesign.bean.MyTrain;
import com.yx.mydesign.bean.User;

public class OtherDaoImpl implements OtherDao {

	Statement pstmt = null;
	ResultSet rs = null;
	Connection conn =  DBConnectionUtils.getConnection();;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	@Override
	public String selectTudeAboutOnlineDevice(String device) {
		String sql = "select longitude,latitude from device where deviceid = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, device);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1)+"-"+rs.getString(2);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Integer getDeviceNum() {
		String sql = "select count(1) from device";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer result = 0;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Integer getUserNum() {
		String sql = "select count(1) from user";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer result = 0;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public Integer getRecordNum() {
		String sql = "select count(1) from user_check_result";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Integer result = 0;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	@Override
	public int insertTrain(String values){
		String sql = "insert into mytrain(tem,hum,choh,pm25,pm10,you,liang,zhong,cha,yanzhong,rank_max,rank_center) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		String[] valuesSplit = values.split("\\s+");
		int i = 0;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,valuesSplit[1]);
			ps.setString(2,valuesSplit[2]);
			ps.setString(3,valuesSplit[3]);
			ps.setString(4,valuesSplit[4]);
			ps.setString(5,valuesSplit[5]);
			ps.setString(6,valuesSplit[6]);
			ps.setString(7,valuesSplit[7]);
			ps.setString(8,valuesSplit[8]);
			ps.setString(9,valuesSplit[9]);
			ps.setString(10,valuesSplit[10]);
			ps.setString(11,valuesSplit[11]);
			ps.setString(12,valuesSplit[12]);
			i = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public int insertTrainTest(String values){
		String sql = "insert into mytrain_final(tem,hum,choh,pm25,pm10,you,liang,zhong,cha,yanzhong,rank_max,rank_center) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		String[] valuesSplit = values.split("\\s+");
		int i = 0;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,valuesSplit[1]);
			ps.setString(2,valuesSplit[2]);
			ps.setString(3,valuesSplit[3]);
			ps.setString(4,valuesSplit[4]);
			ps.setString(5,valuesSplit[5]);
			ps.setString(6,valuesSplit[6]);
			ps.setString(7,valuesSplit[7]);
			ps.setString(8,valuesSplit[8]);
			ps.setString(9,valuesSplit[9]);
			ps.setString(10,valuesSplit[10]);
			ps.setString(11,valuesSplit[11]);
			ps.setString(12,valuesSplit[12]);
			i = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public void closeConnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//使用新的重心法插入评判的结果数据
	@Override
	public int updateToTrainForNewAlg(Integer id,String value) {
		String sql = "update mytrain_pm25 set rank_center_new = ? where id = ?";
		PreparedStatement ps = null;
		int i = 0;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,value);
			ps.setInt(2,id);
			i = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public List<MyTrain> getAllTrain() {
		String sql = "select * from mytrain_pm25";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MyTrain> list = new ArrayList<MyTrain>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				MyTrain train = new MyTrain();
				train.setId(rs.getInt(1));
				train.setTem(rs.getDouble(2));
				train.setHum(rs.getDouble(3));
				train.setChoh(rs.getDouble(4));
				train.setPm25(rs.getDouble(5));
				train.setPm10(rs.getDouble(6));
				train.setYou(rs.getDouble(7));
				train.setLiang(rs.getDouble(8));
				train.setZhong(rs.getDouble(9));
				train.setCha(rs.getDouble(10));
				train.setYanzhong(rs.getDouble(11));
				train.setRankMax(rs.getString(12));
				train.setRankCenter(rs.getString(13));
				train.setRankCenterNew(rs.getString(14));
				System.out.println(train.toString());
				list.add(train);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		String sql = "select * from user where user.username = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			rs = psmt.executeQuery();
			while (rs.next()) {
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setGender(rs.getString(4));
				user.setAge(rs.getString(5));
				user.setPhone(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setIsvip(rs.getInt(8));
				user.setRewardpoint(rs.getInt(9));
				user.setRequestviewnum(rs.getInt(10));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	@Override
	public Device getDeviceByDeviceID(String deviceID) {
		Device device = new Device();
		String sql = "select * from device where device.deviceID = ?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, deviceID);
			rs = psmt.executeQuery();
			while (rs.next()) {
				device.setId(rs.getInt(1));
				device.setDeviceid(rs.getString(2));
				device.setPlacefrom(rs.getString(3));
				device.setTimefrom(rs.getDate(4));
				device.setState(rs.getString(5));
				device.setPlacecurrent(rs.getString(6));
				device.setLongitude(rs.getString(7));
				device.setLatitude(rs.getString(8));
				device.setTimecurrent(rs.getDate(9));
				device.setUsername(rs.getString(10));
				device.setPhone(rs.getString(11));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return device;
	}
	@Override
	public int updateDeviceInfo(Device device,String deviceID) {
		PreparedStatement psmt = null;
		String sql = "update device set device.placecurrent = ? , device.longitude = ? , device.latitude = ? , device.timecurrent = ? , device.username = ? , device.phone = ? where device.deviceID = ?";
		int result = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, device.getPlacecurrent());
			psmt.setString(2,device.getLongitude());
			psmt.setString(3, device.getLatitude());
			psmt.setDate(4, new Date(device.getTimecurrent().getTime()));
			psmt.setString(5, device.getUsername());
			psmt.setString(6, device.getPhone());
			psmt.setString(7,deviceID);
			result = psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}