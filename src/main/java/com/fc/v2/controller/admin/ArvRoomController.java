package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.common.log.Log;
import com.fc.v2.model.auto.TArvRoom;
import com.fc.v2.service.ITArvRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 库房档案 Controller
 *
 * @author fuce
 * @date 2026-09-12
 */
@Api(value = "库房档案")
@Controller
@RequestMapping("/ArvRoomController")
public class ArvRoomController extends BaseController {

    private final String prefix = "admin/arvRoom";

    @Autowired
    private ITArvRoomService arvRoomService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    @RequiresPermissions("archv:arvRoom:view")
    public String view(ModelMap model) {
        return prefix + "/list";
    }

    @Log(title = "库房档案集合查询", action = "list")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/list")
    @RequiresPermissions("archv:arvRoom:list")
    @ResponseBody
    public ResultTable list(TArvRoom record) {
        QueryWrapper<TArvRoom> queryWrapper = new QueryWrapper<TArvRoom>();
        startPage();
        com.github.pagehelper.PageInfo<TArvRoom> page =
                new com.github.pagehelper.PageInfo<TArvRoom>(arvRoomService.selectTArvRoomList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @Log(title = "库房档案新增", action = "add")
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/add")
    @RequiresPermissions("archv:arvRoom:add")
    @ResponseBody
    public AjaxResult add(TArvRoom record) {
        return toAjax(arvRoomService.insertTArvRoom(record));
    }

    @Log(title = "库房档案修改", action = "edit")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @RequiresPermissions("archv:arvRoom:edit")
    @ResponseBody
    public AjaxResult editSave(TArvRoom record) {
        return toAjax(arvRoomService.updateTArvRoom(record));
    }

    @Log(title = "库房档案删除", action = "remove")
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/remove")
    @RequiresPermissions("archv:arvRoom:remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(arvRoomService.deleteTArvRoomByIds(ids));
    }
}
