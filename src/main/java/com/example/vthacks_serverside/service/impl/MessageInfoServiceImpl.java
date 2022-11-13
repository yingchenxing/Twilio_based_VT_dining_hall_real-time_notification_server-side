package com.example.vthacks_serverside.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vthacks_serverside.entity.MessageInfo;
import com.example.vthacks_serverside.mapper.MessageInfoMapper;
import com.example.vthacks_serverside.service.IMessageInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean removeByPhone(MessageInfo messageInfo){
        String number = messageInfo.getPhoneNumber();
        QueryWrapper<MessageInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number",number);
        return remove(queryWrapper);
    }


    public List<MessageInfo> getByPhone(MessageInfo messageInfo){
        String number = messageInfo.getPhoneNumber();
        QueryWrapper<MessageInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone_number",number);
        return list(queryWrapper);
    }
}
