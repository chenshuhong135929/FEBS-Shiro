package cc.mrbird.febs.thecompany.contoller;

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
@Controller("thecompanyView")
@RequestMapping(FebsConstant.VIEW_PREFIX + "thecompany")
public class ViewController extends BaseController {

    @GetMapping("list")
    @RequiresPermissions("thecompany:view")
    public String thecompanysForm() {
        return FebsUtil.view("thecompany/list");
    }

}
