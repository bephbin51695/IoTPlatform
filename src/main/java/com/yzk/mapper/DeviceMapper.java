package com.yzk.mapper;

import com.yzk.domain.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("select * from device where id=#{id}")
    Device getById(Integer id);

    @Select("select * from device")
    List<Device> getAll();

    @Select("select * from device where ownerId=#{id}")
    List<Device> getByOwnerId(Integer id);

    @Select("select * from device")
    List<Device> getAllByPage();

    @Insert("insert into device values(#{id},#{currentTemperature},#{setLowTemperature},#{setHighTemperature},#{ownerId},#{room},#{deviceId})")
    Integer addDevice(Device device);

    @Update("update device set currentTemperature=#{currentTemperature}, setLowTemperature=#{setLowTemperature}, setHighTemperature=#{setHighTemperature}, ownerId=#{ownerId}, room=#{room}  where id=#{id}")
    Integer modifyById(Device device);
}
