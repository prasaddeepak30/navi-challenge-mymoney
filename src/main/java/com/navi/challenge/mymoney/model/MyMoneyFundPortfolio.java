package com.navi.challenge.mymoney.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class MyMoneyFundPortfolio implements Cloneable {
    @NonNull
    private final List<Fund> funds;

    @Override
    public MyMoneyFundPortfolio clone() {
        return new MyMoneyFundPortfolio(
                funds.stream()
                        .map(e -> new Fund(e.getAsset(), e.getAmount()))
                        .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return funds.stream()
                .map(entity -> Integer.toString((int) Math.floor(entity.getAmount())))
                .collect(Collectors.joining(" "));
    }

    /**
     * This method sums the investment made across all asset class and returns total amount invested
     *
     * @return total investment across all asset class
     */
    public double getTotalInvestment() {
        return funds.stream().mapToDouble(Fund::getAmount).sum();
    }
}
