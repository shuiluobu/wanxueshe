package com.wxs.admin.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wxs.entity.sys.SysMenu;
import com.wxs.entity.sys.SysUser;
import com.wxs.service.sys.impl.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.wxs.core.anno.Log;
import org.wxs.core.anno.Resource;
import org.wxs.core.bean.Rest;
import org.wxs.core.controller.CrudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 标准的Rest接口,实例控制器
 * Created by Gaojun.Zhou 2017年6月8日
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends CrudController<SysMenu, ISysMenuService>{
	
	@Autowired
    private ISysMenuService sysMenuService;
	
	@Resource("listMenu")
	@ResponseBody
	@RequestMapping("/page")
	public Rest page(
            @RequestParam(required = true,defaultValue="1") Integer page,
            @RequestParam(defaultValue="15")Integer size, String keyword, Model model){
		EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
		ew.orderBy("code", true);
		if(StringUtils.isNotBlank(keyword)){
			ew.like("menuName", keyword);
		}
		Page<SysMenu> pageData = sysMenuService.selectPage(new Page<SysMenu>(page, size),ew);
		Integer pages = sysMenuService.selectCount(ew);
		return Rest.okPageData(pageData.getRecords(),pages);
		
	}
	
	/**
	 * 执行新增菜单
	 * @param menu
	 * @param result
	 * @return
	 */
	@Log("新增菜单")
	@Resource("addMenu")
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(@Valid SysMenu menu,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		menu.setPid("0");
		menu.setDeep(1);
		sysMenuService.insert(menu);
		return Rest.ok("添加成功!");
	}
	/**
	 * 执行编辑用户
	 * @param menu
	 * @param result
	 * @return
	 */
	@Log("编辑菜单")
	@Resource("editMenu")
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(@Valid SysMenu menu,BindingResult result){
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		if(menu == null || StringUtils.isBlank(menu.getId())){
			throw new RuntimeException("参数{id}不能为空");
		}
		sysMenuService.updateById(menu);
		return Rest.ok("编辑成功!");
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "menu";
	}

	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "menu";
	}


	@Override
	public String edit(String id, Model model) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank((String)id)){
			throw new RuntimeException("参数{id}不能为空");
		}
		SysMenu sysMenu= sysMenuService.selectById(id);
		if(sysMenu == null){
			throw new RuntimeException("未查询到要编辑的菜单");
		}
		model.addAttribute("menu", sysMenu);
		System.out.println(sysMenu.getDeep());
		if(sysMenu.getDeep() == 1){
			return "menu/edit";
		}else{
			SysMenu pmenu = new SysMenu();
			if(sysMenu.getPid().trim().equals("0")){
				pmenu.setId("0");
				pmenu.setMenuName("主菜单");
			}else{
				pmenu = sysMenuService.selectById(sysMenu.getPid());
			}
			model.addAttribute("pmenu",pmenu);
			return "menu/edit_item";
		}
	}
	
	/**
	 * 添加子菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@Resource("addMenu")
	@RequestMapping("/addItem")
	public String addItem(String id, Model model) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank((String)id)){
			throw new RuntimeException("参数{id}不能为空");
		}
		SysMenu sysMenu= sysMenuService.selectById(id);
		if(sysMenu == null){
			throw new RuntimeException("未查询到要操作的菜单");
		}
		model.addAttribute("menu", sysMenu);
		return "menu/add_item";
	}
	
	/**
	 * 执行添加子菜单
	 * @param menu
	 * @param result
	 * @return
	 */
	@Log("编辑子菜单")
	@Resource("addMenu")
	@ResponseBody
	@RequestMapping("/doAddItem")
	public Rest doAddItem(@Valid SysMenu menu,BindingResult result){
		
		if(result.hasErrors()){
			String firstError = result.getFieldErrors().get(0).getDefaultMessage();
			return Rest.failure(firstError);
		}
		SysMenu pmenu = sysMenuService.selectById(menu.getPid());
		menu.setDeep(pmenu.getDeep() + 1);
		sysMenuService.insert(menu);
		return Rest.ok("添加成功!");
	}
	
	/**
	 * 获取当前用户的菜单
	 * @return
	 */
	@ResponseBody
	@GetMapping("/leftmenu")
	public Rest leftmenu(HttpServletRequest request){
		String uid = ((SysUser)request.getSession().getAttribute("session_user")).getId();
		List<Map<String, Object>> list = sysMenuService.selectMenuByUid(uid,"0");
		for(Map<String, Object> m : list){
			m.put("children", sysMenuService.selectMenuByUid(uid,m.get("id").toString()));
		}
		return Rest.okData(list);
	}
	
}
