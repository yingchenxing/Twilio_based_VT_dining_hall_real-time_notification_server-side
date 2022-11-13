package com.example.vthacks_serverside.service;

import com.example.vthacks_serverside.entity.MessageInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xic
 * @since 2022-11-12
 */
public interface IMessageInfoService extends IService<MessageInfo> {

    public boolean removeByPhone(MessageInfo messageInfo);
    public List<MessageInfo> getByPhone(MessageInfo messageInfo);
}
