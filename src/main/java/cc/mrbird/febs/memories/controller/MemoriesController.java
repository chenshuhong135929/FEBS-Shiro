package cc.mrbird.febs.memories.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.UUIDUtil;
import cc.mrbird.febs.memories.entity.Memories;
import cc.mrbird.febs.memories.service.IMemoriesService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  Controller
 *
 * @author MrBird
 * @date 2020-05-15 10:42:48
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
public class MemoriesController extends BaseController {

    @Value("${web.upload-path}")
    private String uploadPath;

    private final IMemoriesService memoriesService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "memories")
    public String memoriesIndex(){
        return FebsUtil.view("memories/memories");
    }

    @GetMapping("memories")
    @ResponseBody
    @RequiresPermissions("memories:view")
    public FebsResponse getAllMemoriess(Memories memories) {
        return new FebsResponse().success().data(memoriesService.findMemoriess(memories));
    }

    @GetMapping("memories/list")
    @ResponseBody
    @RequiresPermissions("memories:view")
    public FebsResponse memoriesList(QueryRequest request, Memories memories) {
        Map<String, Object> dataTable = getDataTable(this.memoriesService.findMemoriess(request, memories));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增首页图片", exceptionMessage = "新增首页图片失败")
    @PostMapping("memories/add")
    @ResponseBody
    @RequiresPermissions("memories:add")
    public FebsResponse addMemories(@Valid Memories memories) {
        memories.setId(UUIDUtil.getUUID());
        memories.setMemoriesDate(new Date());
        this.memoriesService.createMemories(memories);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除首页图片", exceptionMessage = "删除首页图片失败")
    @GetMapping("memories/delete/{ids}")
    @ResponseBody
    @RequiresPermissions("memories:delete")
    public FebsResponse deleteMemories(@NotBlank(message = "{required}") @PathVariable String ids) {
        this.memoriesService.deleteMemories(ids.split(StringPool.COMMA));
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改首页图片", exceptionMessage = "修改首页图片失败")
    @PostMapping("memories/update")
    @ResponseBody
    @RequiresPermissions("memories:update")
    public FebsResponse updateMemories(Memories memories) {
        this.memoriesService.updateMemories(memories);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改首页图片", exceptionMessage = "导出Excel失败")
    @PostMapping("memories/excel")
    @ResponseBody
    @RequiresPermissions("memories:export")
    public void export(QueryRequest queryRequest, Memories memories, HttpServletResponse response) {
        List<Memories> memoriess = this.memoriesService.findMemoriess(queryRequest, memories).getRecords();
        ExcelKit.$Export(Memories.class, response).downXlsx(memoriess, false);
    }

    @PostMapping("memories/uploadSource")
    @ResponseBody
    public Map<String,String> uploadSource(@RequestParam("file") MultipartFile file ) throws IOException {
        String time = String.valueOf(System.currentTimeMillis());
        File fileDir =  new File(uploadPath+"images");
        if(!fileDir.exists())
            fileDir.mkdirs();
        //生成文件在服务器存放的名字
        String fileName = time+"-"+file.getOriginalFilename();
        File files = new File(fileDir+File.separator+fileName);
        //上传
        file.transferTo(files);
        Map<String,String> map =new HashMap<>();
        map.put("imageUrl","/mes/images"+File.separator+fileName);
        return map;
    }
}
