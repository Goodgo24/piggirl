package com.joe.dao;

import com.common.BaseMapper;
import com.joe.pojo.Goods;
import com.joe.pojo.GoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends BaseMapper<Goods, GoodsExample>{
    
}