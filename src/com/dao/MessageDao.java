package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.po.MessageBean;
import com.po.UserPageQuery;

@Repository("MessageDao")
@Mapper
public interface MessageDao {
	public int sendMessage(MessageBean message);
	public List<MessageBean> getUserMessage(UserPageQuery query);
	public List<MessageBean> getRecordMessage(UserPageQuery query);
	public List<MessageBean> getUserAllMessage(int u_id);
	public int receiveMessage(MessageBean message);
	public int delMessage(MessageBean message);
}
