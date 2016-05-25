package com.funeral.kris.pay.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.funeral.kris.constants.COLLECTION;
import com.funeral.kris.constants.WishConstants;
import com.funeral.kris.controller.ContactInfoController;
import com.funeral.kris.model.ExpressInfo;
import com.funeral.kris.model.Order;
import com.funeral.kris.model.TFeeCollection;
import com.funeral.kris.model.Wishlist;
import com.funeral.kris.model.WishlistDetail;
import com.funeral.kris.util.MySQL;

public class PayDAOImpl implements PayDAO {

	@Override
	public Order getOrderByOrderNo(String orderNo) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "select * from t_order where order_no=? ";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, orderNo);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Order order = new Order();

				order.setPayableAmount(rs.getBigDecimal("payable_amount"));
				order.setCurrencyId(1);
				order.setCurrencyRate(BigDecimal.ONE);
				order.setOrderId(rs.getInt("order_id"));
				order.setStatusId(rs.getInt("status_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setSubject(rs.getString("subject"));
				order.setDescription(rs.getString("description"));
				order.setOrderNo(orderNo);
				return order;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void createFeeCollection(TFeeCollection feeCollection) {
		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "insert into t_fee_collection (order_id,order_no,subject,amount,out_trade_no,status_id) values(?,?,?,?,?,?)";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, feeCollection.getOrderId());

			st.setString(2, feeCollection.getOrderNo());

			st.setString(3, feeCollection.getSubject());

			st.setBigDecimal(4, feeCollection.getAmount());

			st.setString(5, feeCollection.getOutTradeNo());

			st.setInt(6, feeCollection.getStatusId());

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public TFeeCollection loadPayableFeeCollectionByOrderNo(String orderNo) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "select * from t_fee_collection where order_no=?  and status_id not in (?,?)";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, orderNo);
			st.setInt(2, COLLECTION.collection_pay_success);
			st.setInt(3, COLLECTION.collection_pay_expired);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				TFeeCollection collection = new TFeeCollection();

				collection.setCollectionId(rs.getInt("collection_id"));

				return collection;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public void saveFeeCollection(TFeeCollection feeCollection) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "update t_fee_collection set order_no=? ,  status_id =? , collection_type=?,order_id=?,description=?,subject=?,trade_status=?,collection_type=? where collection_id=?";

		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, feeCollection.getOrderNo());
			st.setInt(2, feeCollection.getStatusId());
			st.setInt(3, 1);
			st.setInt(4, -1);
			st.setString(5, feeCollection.getSubject());
			st.setString(6, feeCollection.getSubject());
			st.setString(7, feeCollection.getTradeStatus());
			st.setInt(8, feeCollection.getCollectionType());
			st.setInt(9, feeCollection.getCollectionId());

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Wishlist getwishListByUserId(int userId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "select * from wishlists where user_id=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, userId);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Wishlist wishlist = new Wishlist();

				wishlist.setWishlistId(rs.getInt("wishlist_id"));
				wishlist.setStatus(rs.getString("status"));
				wishlist.setPrice(rs.getBigDecimal("price"));
				wishlist.setUserId(userId);
				wishlist.setOriginalPirce(rs.getBigDecimal("original_price"));
				return wishlist;

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void updateWishlist(Wishlist wishlist) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "update  wishlists   set     status=?,  price=?, original_price=?   where wishlist_id=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, wishlist.getStatus());
			st.setBigDecimal(2, wishlist.getPrice());

			st.setBigDecimal(3, wishlist.getOriginalPrice());

			st.setInt(4, wishlist.getWishlistId());

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<WishlistDetail> getSelectedWishlistDetailByWishListId(int wishlistId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = "select * from wishlist_details where  wishlist_id=? and selected=1 and source_id=?";

		List<WishlistDetail> wishListDetailList = new ArrayList();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, wishlistId);
			st.setInt(2, WishConstants.wish_source_direct);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				WishlistDetail detail = new WishlistDetail();

				detail.setCount(rs.getInt("count"));

				detail.setWishId(wishlistId);

				detail.setOriginalPrice(rs.getBigDecimal("original_price"));

				detail.setPrice(rs.getBigDecimal("price"));

				detail.setSourceId(WishConstants.wish_source_direct);

				detail.setWishlistDetailId(rs.getInt("Wishlist_detail_id"));

				detail.setWishType(rs.getString("wish_type"));

				wishListDetailList.add(detail);

			}

			return wishListDetailList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteWishlistDetail(int wishlistDetailId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = " delete from wishlist_details where  wishlist_detail_id=?  and source_id=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, wishlistDetailId);

			st.setInt(2, WishConstants.wish_source_direct);

			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ExpressInfo> getUncompledExpressInfoByUserId(int userId, int statusId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = " select *  from t_express_info where  user_id=?  and status_id=?";

		List<ExpressInfo> expressInfoList = new ArrayList();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, userId);

			st.setInt(2, statusId);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				ExpressInfo expressInfo = new ExpressInfo();

				expressInfo.setExpressId(rs.getLong("express_id"));

				expressInfo.setStatusId(statusId);

				expressInfoList.add(expressInfo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return expressInfoList;

	}

	@Override
	public void updateExpressInfo(ExpressInfo expressInfo) {

	}

	@Override
	public void releaseUsingContacter(int userId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = " update t_contact_info set  status_id=?    where  user_id=?  and status_id="
				+ ContactInfoController.IN_USE;

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, ContactInfoController.IN_RELEASED);
			st.setInt(1, userId);
			st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ExpressInfo getUncompledExpressInfoByWishOrderId(int wishOrderId, int statusId) {

		MySQL MySQL = new MySQL();

		Connection conn = MySQL.getConn();

		String sql = " select *  from t_express_info where  wish_order_id=? and status_id=" + statusId;

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, wishOrderId);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				ExpressInfo expressInfo = new ExpressInfo();

				expressInfo.setExpressId(rs.getLong("express_id"));

				expressInfo.setStatusId(statusId);

				return expressInfo;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
