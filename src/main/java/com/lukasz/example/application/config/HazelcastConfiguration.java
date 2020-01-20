package com.lukasz.example.application.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration {

    public static final String ADDRESSES_CACHE_NAME = "addresses";
    private static final String BUCKETS_CACHE_NAME = "buckets";
    private static final EvictionPolicy LEAST_RECENTLY_USED = EvictionPolicy.LRU;
    private static final int ADDRESSES_EXPIRATION_TIME_IN_SEC = 1800;
    private static final int DDOS_PROTECT_EXPIRATION_TIME_IN_SEC = 30;

    @Bean
    public Config hazelCastConfig() {
        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig()
                        .setName(BUCKETS_CACHE_NAME)
                        .setTimeToLiveSeconds(DDOS_PROTECT_EXPIRATION_TIME_IN_SEC))
                .addMapConfig(
                        new MapConfig()
                                .setName(ADDRESSES_CACHE_NAME)
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(LEAST_RECENTLY_USED)
                                .setTimeToLiveSeconds(ADDRESSES_EXPIRATION_TIME_IN_SEC));
    }
}
