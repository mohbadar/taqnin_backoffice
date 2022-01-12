-- Necessary Data
INSERT INTO public.user_tbl(id, active, address, email, name, password, username, avatar) VALUES
 (1, true, 'Kabul', 'sys_admin@nsia.gov.af', 'Sys Admin', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW','sys_admin', '2019-02-24_21_22_53_thumbnail.jpg'),
 (2, true, 'Kabul', 'admin@nsia.gov.af', 'Admin User', '$2a$10$UytVE0KGnM6nu3vh9bXxcu/jgEaE6qfflRfut2SKYHtiBrnk3/akW', 'admin', '2019-02-24_21_22_53_thumbnail.jpg');

INSERT INTO public.group_tbl(id, name, description, active) VALUES
 (1, 'SYS_ADMIN_GROUP', 'SYS ADMIN GROUP', true),
 (2, 'ADMIN_GROUP', 'ADMIN GROUP', true);


INSERT INTO public.role(id, name, description, active) VALUES
 (1, 'SYS_ADMIN_ROLE', 'It can manage the application and envrionments', true),
 (2, 'ADMIN_ROLE', 'It can manage the application', true);

INSERT INTO public.permission(id, name, description, active) VALUES
(1, 'SYS_ADMIN', 'Can have access to everything', true),
(2, 'ADMIN', 'Can access to admin pages of an environment', true);

-- LIST OF MODULES PERMISSIONS
INSERT into public.permission (id, name, description, active) values
(11001,'TAQNIN_ANNOUNCEMENT_MODULE','Can access announcement module', true),
(12001,'TAQNIN_DASHBOARD_MODULE','Can access dashboard module', true),
(13001,'TAQNIN_DOCUMENT_MODULE','Can access document module', true),
(14001,'TAQNIN_SETTINGS_MODULE','Can access settings module', true),
(15001,'TAQNIN_CONFIGURATION_MODULE','Can access configuration module', true),
(16001,'TAQNIN_WELCOME_MODULE','Can access welcome module', true);

-- LIST OF ANNOUNCEMENT PERMISSIONS
INSERT into public.permission (id, name, description, active) values 
(11002, 'ANNOUNCEMENT_ADMIN_LIST', 'Can view table/list of annoucements', true),
(11003, 'ANNOUNCEMENT_ADMIN_CREATE', 'Can create announcement', true),
(11004, 'ANNOUNCEMENT_ADMIN_EDIT', 'Can edit announcement', true),
(11005, 'ANNOUNCEMENT_ADMIN_VIEW', 'Can view announcement', true),
(11006, 'ANNOUNCEMENT_ADMIN_DELETE', 'Can delete announcement', true),

(11007, 'ANNOUNCEMENT_LIST', 'Can view table/list of announcements', true),
(11008, 'ANNOUNCEMENT_VIEW', 'Can view organization announcement', true),
(11009, 'MAIN_ANNOUNCEMENT_VIEW', 'Can create main announcement', true),
(11010, 'MAIN_ANNOUNCEMENT_CREATE', 'Can create main announcement', true),
(11011, 'MAIN_ANNOUNCEMENT_EDIT', 'Can edit main announcement', true);

-- LIST OF DASHBOARD PERMISSIONS
INSERT into public.permission (id, name, description, active) values 
(12002, 'DASHBOARD_CARDS', 'Can view cards with counted values', true),
(12003, 'DASHBOARD_TABS', 'Can view dashboard tabs', true);

-- LIST OF DOCUMENT PERMISSIONS
INSERT into public.permission (id, name, description, active) values 
(13002, 'DOCUMENT_SEARCH', 'Can view cards with counted values', true),
(13003, 'DOCUMENT_LIST', 'Can view cards with counted values', true),
(13004, 'DOCUMENT_CREATE', 'Can view dashboard tabs', true),
(13005, 'DOCUMENT_EDIT', 'Can view cards with counted values', true),
(13006, 'DOCUMENT_VIEW', 'Can view cards with counted values', true),
(13007, 'DOCUMENT_DELETE', 'Can view cards with counted values', true),
(13008, 'DOCUMENT_ATTACHMENT_VIEW', 'Can view cards with counted values', true),
(13009, 'DOCUMENT_APPROVE', 'Can view cards with counted values', true),
(13010, 'DOCUMENT_ASSIGN', 'Can view cards with counted values', true),
(13011, 'DOCUMENT_ASSIGN_TO_DEPARTMENT', 'Can view cards with counted values', true),
(13012, 'DOCUMENT_VIEW_HISTORY', 'Can view cards with counted values', true),
(13013, 'DOCUMENT_COMMENT_LIST', 'Can view cards with counted values', true),
(13014, 'DOCUMENT_COMMENT_CREATE', 'Can view cards with counted values', true),
(13015, 'DOCUMENT_COMMENT_EDIT', 'Can view cards with counted values', true),
(13016, 'DOCUMENT_COMMENT_VIEW', 'Can view cards with counted values', true),
(13017, 'DOCUMENT_COMMENT_DELETE', 'Can view cards with counted values', true),
(13018, 'MARK_DOCUMENT_AS_COMPLETED', 'Can view cards with counted values', true);

-- LIST OF SETTINGS PERMISSIONS
INSERT into public.permission (id, name, description, active) values 
(14002, 'USER_LIST', 'Can view users route', true),
(14003, 'USER_CREATE', 'Can create user', true),
(14004, 'USER_EDIT', 'Can edit user', true),
(14005, 'USER_VIEW', 'Can view user', true),
(14006, 'GROUP_LIST', 'Can view groups route', true),
(14007, 'GROUP_CREATE', 'Can create group', true),
(14008, 'GROUP_EDIT', 'Can edit group', true),
(14009, 'GROUP_VIEW', 'Can view group', true),
(14010, 'ROLE_LIST', 'Can view roles route', true),
(14011, 'ROLE_CREATE', 'Can create role', true),
(14012, 'ROLE_EDIT', 'Can edit role', true),
(14013, 'ROLE_VIEW', 'Can view role', true),
(14014, 'PERMISSION_VIEW_DETAILS', 'Can view permissions route', true),
(14015, 'PERMISSION_CREATE', 'Can create permission', true),
(14016, 'PERMISSION_EDIT', 'Can edit permission', true),
(14017, 'PERMISSION_VIEW', 'Can view permission', true);

-- LIST OF CONFIGURATION PERMISSIONS
INSERT into public.permission (id, name, description, active) values 
(15002, 'DEPARTMENT_LIST', 'Can view table/list of departments', true),
(15003, 'DEPARTMENT_CREATE', 'Can create department', true),
(15004, 'DEPARTMENT_EDIT', 'Can edit department', true),
(15005, 'DEPARTMENT_VIEW', 'Can view department', true),
(15006, 'DEPARTMENT_DELETE', 'Can delete department', true),

(15007, 'ORGANIZATION_LIST', 'Can view table/list of organizations', true),
(15008, 'ORGANIZATION_CREATE', 'Can create organization', true),
(15009, 'ORGANIZATION_EDIT', 'Can edit organization', true),
(15010, 'ORGANIZATION_VIEW', 'Can view organization details', true),
(15011, 'ORGANIZATION_DELETE', 'Can delete organization', true),

(15012, 'STATUS_LIST', 'Can view table/list of status', true),
(15013, 'STATUS_CREATE', 'Can create status', true),
(15014, 'STATUS_EDIT', 'Can edit status', true),
(15015, 'STATUS_VIEW', 'Can view status details', true),
(15016, 'STATUS_DELETE', 'Can delete status', true),

(15017, 'WORKFLOW_LIST', 'Can view table/list of workflows', true),
(15018, 'WORKFLOW_CREATE', 'Can create workflow', true),
(15019, 'WORKFLOW_EDIT', 'Can edit workflow', true),
(15020, 'WORKFLOW_VIEW', 'Can view workflow details', true),
(15021, 'WORKFLOW_DELETE', 'Can delete workflow', true),

(15022, 'DOCTYPE_LIST', 'Can view table/list of doctype', true),
(15023, 'DOCTYPE_CREATE', 'Can create doctype', true),
(15024, 'DOCTYPE_EDIT', 'Can edit doctype', true),
(15025, 'DOCTYPE_VIEW', 'Can view doctype details', true),
(15026, 'DOCTYPE_DELETE', 'Can delete doctype', true);


INSERT INTO public.role_permission(role_id, permission_id)
SELECT 1, id
FROM public.permission;

INSERT INTO public.role_permission(role_id, permission_id)
SELECT 2, id
FROM public.permission;

INSERT INTO public.role_permission(role_id, permission_id) values 
(1, 11001),
(1, 12001),
(1, 13001),
(1, 14001),
(1, 15001),
(1, 16001),
(1, 14002),
(1, 14003),
(1, 14004),
(1, 14005),
(1, 14006),
(1, 14007),
(1, 14008),
(1, 14009),
(1, 14010),
(1, 14011),
(1, 14012),
(1, 14013),
(1, 14014),
(1, 14015),
(1, 14016),
(1, 14017);

INSERT INTO public.role_permission(role_id, permission_id) values 
(2, 11001),
(2, 12001),
(2, 13001),
(2, 14001),
(2, 15001),
(2, 16001),
(2, 14002),
(2, 14003),
(2, 14004),
(2, 14005),
(2, 14006),
(2, 14007),
(2, 14008),
(2, 14009),
(2, 14010),
(2, 14011),
(2, 14012),
(2, 14013),
(2, 14014),
(2, 14015),
(2, 14016),
(2, 14017);


INSERT INTO public.user_group(user_id, group_id) VALUES
(1, 1),
(2, 2);

INSERT INTO public.group_role(group_id, role_id) VALUES
(1, 1),
(2, 2);
