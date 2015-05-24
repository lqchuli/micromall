package com.micromall.datacenter.service.good;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallGoodsService {
    MallGoodBean save(MallGoodBean bean);

    void delete(int goodId);

    MallGoodBean findByGoodId(int goodId);

    List<MallGoodBean> findAll(int customerId, String goodName);

    double getPriceByAgent(int agentLevel, String priceInfo);
}
