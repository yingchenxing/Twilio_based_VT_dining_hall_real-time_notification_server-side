package com.example.vthacks_serverside.controller;


import com.example.vthacks_serverside.common.Result;
import com.example.vthacks_serverside.entity.MessageInfo;
import com.example.vthacks_serverside.service.IMessageInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
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

    //根据id新增或修改
    @PostMapping
    public Result save(@RequestBody MessageInfo messageInfo) {
        return Result.success(messageService.saveOrUpdate(messageInfo));
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(messageService.removeById(id));
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

