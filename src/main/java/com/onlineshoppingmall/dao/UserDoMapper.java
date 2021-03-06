package com.onlineshoppingmall.dao;

import com.onlineshoppingmall.dataobject.UserDo;

public interface UserDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    int insert(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    int insertSelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    UserDo selectByPrimaryKey(Integer id);
    UserDo selectByTelphone(String phonenum);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    int updateByPrimaryKeySelective(UserDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Wed Aug 14 15:00:11 CST 2019
     */
    int updateByPrimaryKey(UserDo record);
}