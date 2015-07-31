package com.elin4it.mybatisDemo.mapper;

import com.elin4it.mybatisDemo.pojo.BankBranch;
import com.elin4it.mybatisDemo.pojo.BankBranchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankBranchMapper {
    int countByExample(BankBranchExample example);

    int deleteByExample(BankBranchExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankBranch record);

    int insertSelective(BankBranch record);

    List<BankBranch> selectByExample(BankBranchExample example);

    BankBranch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankBranch record, @Param("example") BankBranchExample example);

    int updateByExample(@Param("record") BankBranch record, @Param("example") BankBranchExample example);

    int updateByPrimaryKeySelective(BankBranch record);

    int updateByPrimaryKey(BankBranch record);
}