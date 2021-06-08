package com.zc.easy.appointment.application.ws.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lizecheng
 * @date 2021/6/8 14:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String userId;
    private String message;
}
