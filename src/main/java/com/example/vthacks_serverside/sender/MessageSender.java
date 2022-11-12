package com.example.vthacks_serverside.sender;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vthacks_serverside.entity.Hall;
import com.example.vthacks_serverside.entity.MessageInfo;
import com.example.vthacks_serverside.service.IHallService;
import com.example.vthacks_serverside.service.IMessageInfoService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MessageSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<MessageInfo> messageList;
    private List<Hall> hallList;
    public static final String ACCOUNT_SID = "AC374277cad4fcc4aa836eda0aecd6724b";
    public static final String AUTH_TOKEN = "659999308e079c8804cfcb144986dd21";
    public static final String FROM_NUMBER = "+17088347098";

    @Resource
    private IMessageInfoService messageService;

    @Resource
    private IHallService hallService;

    /**
     * send message every 5 second
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void sendMessageJob() {
        QueryWrapper<MessageInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 1);
        messageList = messageService.list(queryWrapper);
        LocalDateTime now = LocalDateTime.now();
        String msgContent = getContent();
        for (MessageInfo msg : messageList) {

            if (msg.getMinute() == now.getMinute() && msg.getHour() == now.getHour()) {
                if (!msg.getHasSent()) {
                    try {
                        sendMessage(FROM_NUMBER, msg.getPhoneNumber(), msgContent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println(msgContent);
                msg.setHasSent(true);
                messageService.saveOrUpdate(msg);
            } else {
                msg.setHasSent(false);
            }

        }
//        System.out.println("test");
    }

    public void sendMessage(String from, String to, String msgContent) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(to),
                        new com.twilio.type.PhoneNumber(from),
                        msgContent)
                .create();
        System.out.println(message.getSid());
    }

    public String getContent() {
        hallList = hallService.list();
        String content = "";
        for (Hall hall : hallList) {
            content += "--------------------\n";
            content += hall.getName() + "\n";
            String state = "State: ";
            if (!hall.getIsOpen())
                state += " not open\n";
            else if (hall.getNumber() < 10)
                state += "normal\n";
            else if (hall.getNumber() < 20)
                state += "crowded\n";
            else
                state += "very crowded\n";
            content += hall.getNumber() + " waiting\n";
            content += state;
        }
        return content;
    }

}

