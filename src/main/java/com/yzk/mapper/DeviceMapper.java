package com.yzk.mapper;

import com.yzk.domain.Device;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("select * from device where id=#{id}")
    Device getById(Integer id);

    @Select("select * from device where deviceId=#{devicei=Id}")
    Device getByDeviceId(String deviceId);

    @Select("select * from device")
    List<Device> getAll();

    @Select("select * from device where ownerId=#{id}")
    List<Device> getByOwnerId(Integer id);

    @Select("select * from device")
    List<Device> getAllByPage();

    @Insert("insert into device values(#{id},#{currentTemperature},#{ownerId},#{room},#{deviceId},#{targetTemperature})")
    @SelectKey(before = false, keyProperty = "id", statement = "SELECT LAST_INSERT_ID() AS id", resultType = Integer.class, statementType = StatementType.STATEMENT)
    Integer addDevice(Device device);

    @Update("update device set currentTemperature=#{currentTemperature} where deviceId=#{deviceId}")
    Integer renewCurrentTemperature(Device device);

    @Delete("delete from device where id=#{id}")
    Integer deleteDevice(Integer id);

    @Update("update device set targetTemperature=#{targetTemperature}, ownerId=#{ownerId}, room=#{room} where id=#{id}")
    Integer modifyById(Device device);

    @Update("update device set ownerId=#{ownerId} where deviceId=#{deviceId}")
    Integer addOwnerIdByDeviceId(@Param("deviceId") String deviceId, @Param("ownerId") Integer ownerId);

    @Update("update device set ownerId=null where deviceId=#{deviceId}")
    Integer removeOwnerIdByDeviceId(String deviceId);
}
