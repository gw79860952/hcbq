CREATE VIEW `hcbq`.`searchview` AS
SELECT
	a.id AS detailId,
	a.bsqf,
	a.bz,
	a.dj,
	a.dy,
	a.dyxf,
	a.endDate,
	a.fx,
	a.htbh,
	a.htmc,
	a.qlms,
	a.sqf,
	a.ssgs,
	a.startDate,
	a.STATUS,
	a.xkz,
	a.dygj,
	a.ksdd,
	a.jsdd,
	a.startDateS,
	a.endDateS,
	a.shStatus as ash,
	b.id AS rightId,
	b.NAME AS qlname,
	b.ejName,
	b.xzms,
	b.zrxzms,
	b.yjName,
	b.haveType,
	b.projectType,
	b.shStatus as bsh,
	c.id AS proId,
	c.NAME AS proName,
	c.oldName,
	c.formType,
	c.glProId,
	c.glProName,
	c.shStatus as csh
FROM
	t_project_right_detail a
	LEFT JOIN t_project_right b ON a.projectRightId = b.id
	LEFT JOIN t_project c ON a.projectId = c.id;


CREATE VIEW `hcbq`.`searchview1` AS
select *
from
searchview where ash='1' and bsh='1' and  detailId in(select max(detailId) from searchview group by proName,oldName,haveType,projectType,yjName,ejName);

CREATE VIEW `hcbq`.`searchview2` AS
select *
from
searchview where detailId in(select max(detailId) from searchview group by proName,oldName,haveType,projectType,yjName,ejName);