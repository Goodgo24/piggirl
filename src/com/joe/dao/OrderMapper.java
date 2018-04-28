package com.joe.dao;

import com.common.BaseMapper;
import com.joe.pojo.Order;
import com.joe.pojo.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends BaseMapper<Order, OrderExample>{
   
}