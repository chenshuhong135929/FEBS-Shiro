package cc.mrbird.febs.memories.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrBird
 */
@Controller("memoriesView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "memories")
public class ViewController extends BaseController {

    @GetMapping("list")
    @RequiresPermissions("memories:view")
    public String memoriesForm() {
        return FebsUtil.view("memories/list");
    }

  @GetMapping("add")
  @RequiresPermissions("memories:add")
  public String memoriesAdd() {
    return FebsUtil.view("memories/memoriesAdd");
  }
}
