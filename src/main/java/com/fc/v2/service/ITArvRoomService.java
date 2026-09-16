package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.fc.v2.model.auto.TArvRoom;

import java.util.List;

/**
 * 库房档案 Service接口
 *
 * @author fuce
 * @date 2026-09-12
 */
public interface ITArvRoomService {

    /** 按主键查询 */
    TArvRoom selectTArvRoomById(Long id);

    /** 按条件查询列表（分页由调用方统一处理） */
    List<TArvRoom> selectTArvRoomList(Wrapper<TArvRoom> queryWrapper);

    /** 新增 */
    int insertTArvRoom(TArvRoom record);

    /** 修改 */
    int updateTArvRoom(TArvRoom record);

    /** 批量删除 */
    int deleteTArvRoomByIds(String ids);

    /** 按主键删除 */
    int deleteTArvRoomById(Long id);
}
