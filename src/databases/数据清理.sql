
-- 根据顶级菜单Id，查询当前菜单及其子菜单对应的按钮、查询、表格列、验证、菜单权限、按钮权限
-- 菜单
SELECT	* FROM	tb_sys_menu WHERE	id IN(29) OR parent_id IN(29);

-- 按钮
select * from tb_sys_button where menu_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 查询
select * from tb_sys_query_item where join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 表格列
select * from tb_sys_grid_columns where join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 验证
select * from tb_sys_validation where  join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 菜单权限
select * from tb_sys_privilege_list where privilege_access = 'MENU' and privilege_access_value in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 按钮权限
select * from tb_sys_privilege_list where privilege_access = 'BUTTON' and privilege_access_value in (select id from tb_sys_button where menu_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29)));



/*
delete from tb_sys_privilege_list where privilege_access = 'MENU' and privilege_access_value in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 删除按钮权限
delete from tb_sys_privilege_list where privilege_access = 'BUTTON' and privilege_access_value in (select id from tb_sys_button where menu_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29)));

-- 删除查询
delete from tb_sys_query_item where join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 删除表格列
delete from tb_sys_grid_columns where join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 删除验证
delete from tb_sys_validation where  join_type = 'menu' and join_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 删除按钮
delete  from tb_sys_button where menu_id in (SELECT id FROM tb_sys_menu WHERE	id IN(29) OR parent_id IN(29));

-- 删除菜单
delete FROM	tb_sys_menu WHERE	id IN(29) OR parent_id IN(29);
*/
