package com.elin4it.mybatisDemo.mapper;

import com.elin4it.mybatisDemo.pojo.Bank;
import com.elin4it.mybatisDemo.pojo.BankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankMapper {
    int countByExample(BankExample example);

    int deleteByExample(BankExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bank record);

    int insertSelective(Bank record);

    List<Bank> selectByExample(BankExample example);

    Bank selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bank record, @Param("example") BankExample example);

    int updateByExample(@Param("record") Bank record, @Param("example") BankExample example);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}