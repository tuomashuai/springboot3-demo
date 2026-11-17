package com.zood.springboot3demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zood.springboot3demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper。
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
