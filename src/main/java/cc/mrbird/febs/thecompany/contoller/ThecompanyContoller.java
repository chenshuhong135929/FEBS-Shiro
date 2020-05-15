package cc.mrbird.febs.thecompany.contoller;

import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("thecompany")

public class ThecompanyContoller extends BaseController {
  @GetMapping("list")
  @RequiresPermissions("thecompany:view")
  public FebsResponse userList() {
    //Map<String, Object> dataTable = getDataTable(this.userService.findUserDetailList(user, request));
    return new FebsResponse().success();
  }

}
