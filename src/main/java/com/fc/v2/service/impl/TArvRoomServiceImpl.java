package com.fc.v2.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TArvRoomMapper;
import com.fc.v2.mapper.auto.TArvDepotMapper;
import com.fc.v2.model.auto.TArvRoom;
import com.fc.v2.model.auto.TArvDepot;
import com.fc.v2.service.ITArvRoomService;
import com.fc.v2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库房档案Service业务层处理
 *
 * @author fuce
 * @date 2026-09-12
 */
@Service
public class TArvRoomServiceImpl extends ServiceImpl<TArvRoomMapper, TArvRoom> implements ITArvRoomService {

    @Autowired
    private TArvDepotMapper arvDepotMapper;

    @Override
    public TArvRoom selectTArvRoomById(Long id) {
        return this.baseMapper.selectOne(new QueryWrapper<TArvRoom>()
                .eq("id", id)
                .eq("del_flag", 0));
    }

    @Override
    public List<TArvRoom> selectTArvRoomList(Wrapper<TArvRoom> queryWrapper) {
        QueryWrapper<TArvRoom> wrapper = new QueryWrapper<TArvRoom>();
        com.github.pagehelper.PageHelper.startPage(1, 10);
        wrapper.eq("room_status", 0);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int insertTArvRoom(TArvRoom record) {
        if (record == null) {
            return 0;
        }

        record.setCreateBy(record.getKeeper());
        TArvDepot refArch = arvDepotMapper.selectOne(new QueryWrapper<TArvDepot>()
                .eq("id", record.getDepotId()).eq("del_flag", 0));
        if (refArch == null) {
            return 0;
        }
        if (refArch.getStatus() != null && refArch.getStatus() == 1) {
            return 0;
        }
        record.setDepotCode(refArch.getDepotCode());
        if (StringUtils.isNotEmpty(record.getRoomNo())) {
            Integer dupCnt = this.baseMapper.selectCount(new QueryWrapper<TArvRoom>()
                    .eq("room_no", record.getRoomNo()).eq("del_flag", 0));
            if (dupCnt != null && dupCnt > 0) {
                return 0;
            }
        }
        Date dayBase = record.getReviewDue();
        long dayDiff = 0L;
        if (dayBase != null) {
            dayDiff = (dayBase.getTime() - todayStart().getTime()) / 86400000L + 1;
        }
        record.setRemainDays((int) dayDiff);

        record.setDelFlag(0);
        return this.baseMapper.insert(record);
    }

    @Override
    public int updateTArvRoom(TArvRoom record) {
        if (record == null || record.getId() == null) {
            return 0;
        }

        if (record.getId() != null && StringUtils.isNotEmpty(record.getRoomNo())) {
            Integer dupCnt = this.baseMapper.selectCount(new QueryWrapper<TArvRoom>()
                    .eq("room_no", record.getRoomNo()).ne("id", record.getId()).eq("del_flag", 0));
            if (dupCnt != null && dupCnt > 0) {
                return 0;
            }
        }

        record.setUpdateTime(new Date());
        return this.baseMapper.update(record, new UpdateWrapper<TArvRoom>()
                .eq("id", record.getId())
                .eq("del_flag", 0));
    }

    @Override
    public int deleteTArvRoomByIds(String ids) {
        Long[] idArr = ConvertUtil.toLongArray(ids);
        return this.baseMapper.deleteBatchIds(Arrays.asList(idArr));
    }

    @Override
    public int deleteTArvRoomById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    private Date todayStart() {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.HOUR_OF_DAY, 0);
        c.set(java.util.Calendar.MINUTE, 0);
        c.set(java.util.Calendar.SECOND, 0);
        c.set(java.util.Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
