package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TArvTreatMapper;
import com.fc.v2.model.auto.TArvTreat;
import com.fc.v2.service.ITArvTreatService;

/**
 * 虫霉防治处置单 Service业务层处理（state-machine 形状：单据流转）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TArvTreatServiceImpl implements ITArvTreatService {

    private static final int MAX_STAGE = 3;
    private static final int STATUS_ACTIVE = 1;
    private static final int STATUS_TERMINAL = 2;

    @javax.annotation.Resource
    private TArvTreatMapper arvTreatMapper;

    @Override
    public TArvTreat selectTArvTreatById(Long id) {
        return this.arvTreatMapper.selectById(id);
    }

    @Override
    public List<TArvTreat> selectTArvTreatList(QueryWrapper<TArvTreat> queryWrapper) {
        return this.arvTreatMapper.selectList(queryWrapper);
    }

    @Override
    public TArvTreat advance(Long id, String remark) {
        TArvTreat r = this.arvTreatMapper.selectById(id);
        if (r == null) {
            return null;
        }
        int st = r.getStage() == null ? 0 : r.getStage();
        r.setStage(Math.min(st + 2, MAX_STAGE));
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.arvTreatMapper.updateById(r);
        return r;
    }

    @Override
    public TArvTreat rollback(Long id, String remark) {
        TArvTreat r = this.arvTreatMapper.selectById(id);
        if (r == null) {
            return null;
        }
        r.setStage(0);
        r.setStatus(STATUS_ACTIVE);
        r.setLastAction(remark);
        this.arvTreatMapper.updateById(r);
        return r;
    }

    @Override
    public boolean updateContent(Long id, String remark) {
        TArvTreat r = this.arvTreatMapper.selectById(id);
        if (r == null) {
            return false;
        }
        r.setContent(remark);
        return this.arvTreatMapper.updateById(r) > 0;
    }

    @Override
    public boolean remove(Long id) {
        TArvTreat r = this.arvTreatMapper.selectById(id);
        if (r == null) {
            return false;
        }
        return this.arvTreatMapper.deleteById(id) > 0;
    }

}
