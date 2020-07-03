package com.orange.admin.commons.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author Administrator
 *
 */
public class RuntimeConstant {

	// 登录拦截器无需拦截的url
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/home/**",
			"/photo/view_icon",
			"/photo/view_cunstomer"

		);

	// 权限拦截器无需拦截的url
	public static List<String> authorityExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/system/index",
			"/system/noright",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/menu/favicon.ico",
			"/admin/images/**",
			"/error",
			"/upload/upload_photo",
			"/cpacha/generate_cpacha",
			"/system/logout",
			"/system/update_userinfo",
			"/system/update_pwd",
			"/photo/view",
			"/home/**",
			"/upload/upload_photo",
			"/upload/upload_photo_icon",
			"/photo/view_icon",
			"/photo/view_cunstomer"


		);

	public static List<String> honeExcludePathPatterns = Arrays.asList(
			"/system/**",
			"/admin/**",
			"/error",
			"/upload/upload_photo",
			"/photo/view",

			"/upload/upload_photo",
			"/upload/upload_photo_icon",
			"/photo/view_icon",
			"/photo/view_custom",
			"/photo/view_cunstomer"


	);

	public static List<String> homeLoginExcludePathPatterns = Arrays.asList(
			"/system/**",
			"/admin/**",
			"/error",
			"/upload/upload_photo",
			"/photo/view",
			"/upload/upload_photo",
			"/upload/upload_photo_icon",
			"/photo/view_icon",
			"/home/index/**",
			"/home/goods/**",
			"/home/wanted_goods/**",
			"/home/js/**",
			"/home/css/**",
			"/home/imgs/**",
			"/photo/view_custom",
			"/photo/view_icon",
			"/photo/view_cunstomer"


	);



}
