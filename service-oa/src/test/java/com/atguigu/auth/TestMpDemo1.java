package com.atguigu.auth;


import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private SysRoleMapper mapper;

    //查询所有记录
    @Test
    public void getAll(){
        for (SysRole sysRole : mapper.selectList(null)) {
            System.out.printf(String.valueOf(sysRole));
        }
    }
    //添加操作
    @Test
    public void add(){
        SysRole role = new SysRole();
        role.setRoleName("角色管理员");
        role.setRoleCode("role");
        role.setDescription("角色管理员");

        int rows = mapper.insert(role);
        System.out.println(role.getId());
        System.out.println(rows);



    }
    @Test
    public void testUpdateById(){
        SysRole role = mapper.selectById(10);
        role.setRoleName("角色管理员123121");
        int result = mapper.updateById(role);
        System.out.println(result);

    }@Test
    public void testSelect2() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getRoleCode, "role");
        List<SysRole> users = mapper.selectList(queryWrapper);
        System.out.println(users);
    }

}
