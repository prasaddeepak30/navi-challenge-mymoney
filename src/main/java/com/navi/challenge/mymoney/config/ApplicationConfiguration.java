package com.navi.challenge.mymoney.config;

import com.navi.challenge.mymoney.dao.DataStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.navi.challenge.mymoney.model.Asset.*;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public DataStub dataStub() {
        DataStub stub = new DataStub();
        stub.defaultAssetOrderForIO.add(ASSET_EQUITY);
        stub.defaultAssetOrderForIO.add(ASSET_DEBT);
        stub.defaultAssetOrderForIO.add(ASSET_GOLD);
        return stub;
    }
}
