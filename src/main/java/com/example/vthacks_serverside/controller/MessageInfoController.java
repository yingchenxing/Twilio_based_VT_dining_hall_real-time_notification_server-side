package com.example.vthacks_serverside.controller;


import com.example.vthacks_serverside.common.Constants;
import com.example.vthacks_serverside.common.Result;
import com.example.vthacks_serverside.entity.MessageInfo;
import com.example.vthacks_serverside.service.IMessageInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author xic
 * @since 2022-11-12
 */
@RestController
@RequestMapping("/messageInfo")
public class MessageInfoController {
    @Resource
    private IMessageInfoService messageService;

    //add or update by id
    @PostMapping
    public Result save(@RequestBody MessageInfo messageInfo) {
        List<MessageInfo> list = messageService.getByPhone(messageInfo);
        if (list.size() == 0)
            return Result.success(messageService.saveOrUpdate(messageInfo));
        else
            return Result.error(Constants.CODE_401, "item not found");
    }

    // delete by id
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        if(messageService.removeById(id))
        return Result.success(true);
        else
            return Result.error(Constants.CODE_300,"fail to delete");
    }


    //delete by phone
    @PostMapping("/del")
    public Result deleteByPhone(@RequestBody MessageInfo messageInfo) {
        return Result.success(messageService.removeByPhone(messageInfo));
    }

    //根据id批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(messageService.removeBatchByIds(ids));
    }

    //返回所有
    @GetMapping
    public List<MessageInfo> findAll() {
        return messageService.list();
    }

    //通过id查询
    @GetMapping("/{id}")
    public MessageInfo findOne(@PathVariable Integer id) {
        return messageService.getById(id);
    }
}

