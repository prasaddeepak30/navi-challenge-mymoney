package com.navi.challenge.mymoney.dao;

import com.navi.challenge.mymoney.model.Asset;
import com.navi.challenge.mymoney.model.MyMoneyFundPortfolio;
import lombok.Getter;
import org.springframework.context.annotation.Scope;

import java.time.Month;
import java.util.*;

@Getter
@Scope("singleton")
public class DataStub {
    public TreeMap<Month, MyMoneyFundPortfolio> monthlyBalance = new TreeMap<>();
    public TreeMap<Month, Map<Asset, Double>> monthlyMarketChangeRate = new TreeMap<>();
    public MyMoneyFundPortfolio initialAllocation;
    public MyMoneyFundPortfolio initialSip;
    public Map<Asset, Double> desiredWeights = new HashMap<>();
    public Set<Asset> defaultAssetOrderForIO = new LinkedHashSet<>();
}
