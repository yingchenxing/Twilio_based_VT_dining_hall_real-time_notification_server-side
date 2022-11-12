package com.example.vthacks_serverside.service.impl;

import com.example.vthacks_serverside.entity.MessageInfo;
import com.example.vthacks_serverside.mapper.MessageInfoMapper;
import com.example.vthacks_serverside.service.IMessageInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xic
 * @since 2022-11-12
 */
@Service
public class MessageInfoServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements IMessageInfoService {

}
