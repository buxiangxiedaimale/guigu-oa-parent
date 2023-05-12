package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    //注入Service
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有的角色
//    @GetMapping("/findAll")
//    public List<SysRole> findAll(){
//        //调用service中的方法
//        List<SysRole> list = sysRoleService.list();
//        return list;
//
//    }
    //查询所有的角色
    @ApiOperation("查询所有的角色")
    @GetMapping("/findAll")
    public Result<List<SysRole>> findAll(){
        //调用service中的方法
        try {
            int a = 10/0;
        }catch (Exception e){
            throw new GuiguException(20001,"执行了自定义异常处理");
        }
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);

    }
    //条件分页查询
    //page 当前页  limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        //调用service的方法实现
        //1 创建Page对象，传递分页相关参数
        //page 当前页  limit 每页显示记录数
        Page<SysRole> pageParam = new Page<>(page,limit);

        //2 封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)) {
            //封装 like模糊查询
            wrapper.like(SysRole::getRoleName,roleName);
        }

        //3 调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }
    //添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role){
        //调用service方法
        boolean is_success = sysRoleService.save(role);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "根据id获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }
    //修改角色
    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody SysRole role) {
        boolean is_success = sysRoleService.updateById(role);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if (is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }


}
