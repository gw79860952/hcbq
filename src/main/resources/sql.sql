CREATE VIEW `hcbq`.`searchview` AS
select a.id as detailId,a.bsqf,a.bz,a.dj,a.dy,a.dyxf,a.endDate,a.fx,a.htbh,a.htmc,a.qlms,a.sqf,a.ssgs,a.startDate,a.status,a.xkz,a.dygj,a.ksdd,a.jsdd,a.startDateS,a.endDateS,
b.id as rightId,b.name as qlname,b.ejName,b.xzms,b.zrxzms,b.yjName,b.haveType,b.projectType,
c.id as proId,c.name as proName,c.oldName,c.formType,c.glProId,c.glProName
from
t_project_right_detail a left join t_project_right b on a.projectRightId = b.id
left join t_project c on a.projectId = c.id;


CREATE VIEW `hcbq`.`searchview1` AS
select *
from
searchview where detailId in(select max(detailId) from searchview group by proName,oldName,haveType,projectType,yjName,ejName);