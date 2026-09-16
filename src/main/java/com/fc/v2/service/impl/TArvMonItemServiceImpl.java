package com.fc.v2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.mapper.auto.TArvMonItemMapper;
import com.fc.v2.model.auto.TArvMonItem;
import com.fc.v2.service.ITArvMonItemService;

/**
 * 环境监测批次明细 Service业务层处理（batch-process 形状：整批提交）
 *
 * @author fuce
 * @date 2026-09-14
 */
@Service
public class TArvMonItemServiceImpl implements ITArvMonItemService {

    private static final int MAX_ROWS = 500;
    private static final int STATUS_OK = 1;
    private static final int STATUS_FAIL = 2;

    @javax.annotation.Resource
    private TArvMonItemMapper arvMonItemMapper;

    @Override
    public TArvMonItem selectTArvMonItemById(Long id) {
        return this.arvMonItemMapper.selectById(id);
    }

    @Override
    public int submitBatch(String batchNo, List<TArvMonItem> rows) {
        String no = rows.get(0).getBatchNo();
        java.util.List<TArvMonItem> errors = new java.util.ArrayList<TArvMonItem>();
        int seq = 0;
        for (TArvMonItem r : rows) {
            if (r.getItemCode() == null || r.getItemCode().trim().isEmpty()
                    || r.getQty() == null
                    || r.getQty().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                seq++;
                r.setRowNo(Integer.valueOf(seq));
                r.setBatchNo(no);
                r.setStatus(STATUS_FAIL);
                this.arvMonItemMapper.insert(r);
                errors.add(r);
            }
        }
        if (!errors.isEmpty()) {
            return 0;
        }
        int ok = 0;
        for (TArvMonItem r : rows) {
            r.setBatchNo(no);
            r.setStatus(STATUS_OK);
            this.arvMonItemMapper.insert(r);
            ok++;
        }
        return ok;
    }

    @Override
    public List<TArvMonItem> listErrors(String batchNo) {
        return this.arvMonItemMapper.selectList(new QueryWrapper<TArvMonItem>()
                .eq("batch_no", batchNo).eq("status", STATUS_FAIL));
    }
}
