package com.navi.challenge.mymoney.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Fund {
    @NonNull
    private Asset asset;
    @NonNull
    private Double amount;
}
