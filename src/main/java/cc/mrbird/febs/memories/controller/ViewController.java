package cc.mrbird.febs.memories.controller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.DateUtil;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.memories.entity.Memories;
import cc.mrbird.febs.memories.service.IMemoriesService;
import cc.mrbird.febs.system.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrBird
 */
@Controller("memoriesView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "memories")
@RequiredArgsConstructor
public class ViewController extends BaseController {


    private final  IMemoriesService memoriesService;

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

  @GetMapping("detail/{id}")
  @RequiresPermissions("memories:view")
  public String systemMemoriesDetail(@PathVariable String id, Model model) {
    resolveMemoriesModel(id, model);
    return FebsUtil.view("memories/memoriesDetail");
  }

  private void resolveMemoriesModel(String id, Model model) {
    Memories memories = memoriesService.findId(id);
    model.addAttribute("memories",memories);
  }
}
