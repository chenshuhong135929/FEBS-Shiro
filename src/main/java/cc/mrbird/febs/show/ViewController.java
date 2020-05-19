package cc.mrbird.febs.show;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.memories.entity.Memories;
import cc.mrbird.febs.memories.service.IMemoriesService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MrBird
 */
@Controller("showView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "show")
public class ViewController extends BaseController {

    @GetMapping("index")
    @RequiresPermissions("memories:view")
    public String memoriesForm() {
        return FebsUtil.view("show/index");
    }

}
