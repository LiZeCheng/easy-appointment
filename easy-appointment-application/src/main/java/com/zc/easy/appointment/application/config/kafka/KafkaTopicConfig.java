package com.zc.easy.appointment.application.config.kafka;

import com.zc.easy.appointment.application.constants.KafkaConst;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZecL
 * @Create 2021-06-10 21:34
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * 定义一个KafkaAdmin的bean，可以自动检测集群中是否存在topic，不存在则创建
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConst.bootstrapServers);
        return new KafkaAdmin(configs);
    }

    /**
     * 创建 Topic
     */
    @Bean
    public NewTopic topicinfo() {
        // 创建topic，需要指定创建的topic的"名称"、"分区数"、"副本数量(副本数数目设置要小于Broker数量)"
        return new NewTopic("test", 1, (short) 1);
    }
}
