/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : wanxueshe

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-17 10:07:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户',
  `logTitle` varchar(300) DEFAULT NULL COMMENT '日志标题',
  `logContent` text COMMENT '日志内容',
  `clientIp` varchar(50) DEFAULT NULL COMMENT '客户端IP',
  `requestUrl` varchar(300) DEFAULT NULL COMMENT '请求URL',
  `requestMethod` varchar(20) DEFAULT NULL COMMENT '请求方式',
  `requestParams` text COMMENT '参数',
  `logTime` datetime DEFAULT NULL COMMENT '日志时间',
  `other` varchar(300) DEFAULT NULL COMMENT '备用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1941e32d49f3442bb30d88dbac01705d', 'admin', '', '编辑用户', '0:0:0:0:0:0:0:1', '/user/doEdit', 'POST', '{\"password\":[\"\"],\"userImg\":[\"/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg\"],\"file\":[\"\"],\"userState\":[\"1\"],\"password2\":[\"\"],\"userDesc\":[\"超级管理员\"],\"id\":[\"8ec475bfc69041a4a3984c5510f7982b\"],\"userName\":[\"admin\"],\"roleIds[]\":[\"737933bffef640329a4f864c4e2746ba\"]}', '2017-07-06 15:39:36', null);
INSERT INTO `sys_log` VALUES ('3ccae7cd77544b929d379d238364a78b', 'admin', '', '编辑用户', '0:0:0:0:0:0:0:1', null, 'POST', '{\"password\":[\"\"],\"userImg\":[\"/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg\"],\"file\":[\"\"],\"userState\":[\"1\"],\"password2\":[\"\"],\"userDesc\":[\"超级管理员\"],\"id\":[\"8ec475bfc69041a4a3984c5510f7982b\"],\"userName\":[\"admin\"],\"roleIds[]\":[\"737933bffef640329a4f864c4e2746ba\"]}', '2017-07-06 15:38:10', null);
INSERT INTO `sys_log` VALUES ('5d6dd88bed3045e385870ad5db52b3a2', 'admin', '', '更新角色状态', '0:0:0:0:0:0:0:1', '/role/roleState', 'GET', '{\"roleState\":[\"true\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175984\"]}', '2017-07-06 15:46:20', null);
INSERT INTO `sys_log` VALUES ('8e97fe59385d430d862f73e09087934b', 'admin', '', '更新角色状态', '0:0:0:0:0:0:0:1', '/role/roleState', 'GET', '{\"roleState\":[\"true\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175982\"]}', '2017-07-06 15:46:19', null);
INSERT INTO `sys_log` VALUES ('be6e9a25b12c47fe92de521003f50382', 'admin', '', '编辑角色', '0:0:0:0:0:0:0:1', '/role/doEdit', 'POST', '{\"roleName\":[\"超级管理员\"],\"roleDesc\":[\"超级管理员\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"]}', '2017-07-06 15:46:18', null);
INSERT INTO `sys_log` VALUES ('c0f7a4af5dc240a38ed73c702e129baa', 'admin', '', '更新角色状态', '0:0:0:0:0:0:0:1', '/role/roleState', 'GET', '{\"roleState\":[\"false\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175981\"]}', '2017-07-06 15:46:19', null);
INSERT INTO `sys_log` VALUES ('e28ca7d678fa41eb8bc712c6ad59f1af', 'admin', '', '更新角色状态', '0:0:0:0:0:0:0:1', '/role/roleState', 'GET', '{\"roleState\":[\"false\"],\"id\":[\"737933bffef640329a4f864c4e2746ba\"],\"_\":[\"1499327175983\"]}', '2017-07-06 15:46:19', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `deep` int(11) DEFAULT NULL COMMENT '深度',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) DEFAULT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0c9b5fc8b44b41d1871a8fc8300247ec', '删除菜单', '4', '', '', '4', '3', '010303', 'deleteMenu');
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', '', 'icon-settings', '0', '1', '01', '');
INSERT INTO `sys_menu` VALUES ('1db9105008404a3485b6fac30dba3c0e', '查看角色列表', '3', '', '', '0', '3', '010200', 'listRole');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '1', '/user/list', 'icon-yonghu', '1', '2', '0101', 'user');
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '1', '/role/list', 'icon-jiaose', '2', '2', '0102', 'role');
INSERT INTO `sys_menu` VALUES ('3987d383a7a74b45902e14e027d9b56e', '更新角色状态', '3', '', '', '6', '3', '010206', 'updateStateRole');
INSERT INTO `sys_menu` VALUES ('3b18f3d776c74266b63c2542825aa3c3', 'SPU管理', 'be659f4c66fb4db989f654eb408d86e1', '', 'icon-SPUguanli', '0', '2', '0201', 'spuList');
INSERT INTO `sys_menu` VALUES ('3f26102ef0e04c3c9328cb97067cc5fa', '创建菜单', '4', '', '', '1', '3', '010301', 'addMenu');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '1', '/menu/list', 'icon-menu', '3', '2', '0103', 'menu');
INSERT INTO `sys_menu` VALUES ('4253190001c1480fb0d631d64d150535', '编辑用户', '2', '', '', '2', '3', '010102', 'editUser');
INSERT INTO `sys_menu` VALUES ('42dd5ae31e3a43b3a197440e8ec19a94', '监控列表', 'f5a20c82110b4a3ea9e30ca01a038680', '', '', '1', '3', '010701', 'monitorList');
INSERT INTO `sys_menu` VALUES ('488ef1eff57b4827acade7c0744278ce', '查看菜单列表', '4', '', '', '0', '3', '010300', 'listMenu');
INSERT INTO `sys_menu` VALUES ('60dda993d87647f5989c15f14f866df9', '新增角色', '3', '', '', '1', '3', '010201', 'addRole');
INSERT INTO `sys_menu` VALUES ('649b484b58414d91aefa5a26143e6557', '删除用户', '2', '', '', '3', '3', '010103', 'deleteUser');
INSERT INTO `sys_menu` VALUES ('686630c7cb624cc786dcdc9958971a6b', '编辑角色', '3', '', '', '2', '3', '010202', 'editRole');
INSERT INTO `sys_menu` VALUES ('809db56d93e848e8b43396e125803884', '日志管理', '1', '/log/list', 'icon-rizhi', '4', '2', '0104', '');
INSERT INTO `sys_menu` VALUES ('87d3ef8ecfae40be95f9ac5ac2c1adc8', '教师管理', 'be659f4c66fb4db989f654eb408d86e1', '/teacher/list', '', '0', '2', '0201', 'user');
INSERT INTO `sys_menu` VALUES ('9c51e94cef99435780fb72bdb923a2ab', '更新用户状态', '2', '', '', '4', '3', '010104', 'updateStateUser');
INSERT INTO `sys_menu` VALUES ('a5ebf29168234406939856bc6890c86b', '角色授权', '3', '', '', '4', '3', '010204', 'authRole');
INSERT INTO `sys_menu` VALUES ('a73802e513cc4465883530ec8074abbb', '新增用户', '2', '', '', '1', '3', '010101', 'addUser');
INSERT INTO `sys_menu` VALUES ('b4e7232189b14cf3ba160cf7b0d3bf37', '删除角色', '3', '', '', '3', '3', '010203', 'deleteRole');
INSERT INTO `sys_menu` VALUES ('be659f4c66fb4db989f654eb408d86e1', '机构内管理', '0', '', 'icon-shangpin', '0', '1', '02', '');
INSERT INTO `sys_menu` VALUES ('c0c304be5c294114b5bc0d0c3acef992', '日志列表', '809db56d93e848e8b43396e125803884', '', '', '1', '3', '010401', 'listLog');
INSERT INTO `sys_menu` VALUES ('d2bc30feb5474a1bb7e02d48d39a3f8a', '查看用户列表', '2', '', '', '0', '3', '010100', 'listUser');
INSERT INTO `sys_menu` VALUES ('dc5f478d98ed4297a8ae638fe90df050', '编辑菜单', '4', '', '', '3', '3', '010302', 'editMenu');
INSERT INTO `sys_menu` VALUES ('f5a20c82110b4a3ea9e30ca01a038680', '系统监控', '1', '/druid/wall.html', 'icon-jiankong', '7', '2', '0107', '');
INSERT INTO `sys_menu` VALUES ('f899f3d3baec4571b1c786717f9906fd', '批量删除角色', '3', '', '', '5', '3', '010205', 'deleteBatchRole');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  `roleDesc` varchar(300) DEFAULT NULL COMMENT '角色描述',
  `roleState` int(2) DEFAULT '1' COMMENT '状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2a9b728a431246b08f853c2529e6ba84', '测试角色', '测试', '1', '2017-02-28 15:15:41');
INSERT INTO `sys_role` VALUES ('3bd9f9e5fa8a4e0587a78cf697e4a9ce', '只读角色', '只读角色', '1', '2017-07-06 14:35:37');
INSERT INTO `sys_role` VALUES ('737933bffef640329a4f864c4e2746ba', '超级管理员', '超级管理员', '1', '2016-12-14 10:22:34');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  `menuId` varchar(50) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0007db394e2a4c48a4dfbe2401ac0a39', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '3b18f3d776c74266b63c2542825aa3c3');
INSERT INTO `sys_role_menu` VALUES ('0022b20f5a8243d68b729da860fa50d4', '2a9b728a431246b08f853c2529e6ba84', '3f26102ef0e04c3c9328cb97067cc5fa');
INSERT INTO `sys_role_menu` VALUES ('044e721126794b32b8b94d227b321018', '737933bffef640329a4f864c4e2746ba', 'be659f4c66fb4db989f654eb408d86e1');
INSERT INTO `sys_role_menu` VALUES ('0685b1490a3a4e62a2b69199f964de2a', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('0ecbbac0284b483488d57a456f6cb339', '2a9b728a431246b08f853c2529e6ba84', '0c9b5fc8b44b41d1871a8fc8300247ec');
INSERT INTO `sys_role_menu` VALUES ('11656adbd1164fe1b6171fea1d56374e', '737933bffef640329a4f864c4e2746ba', 'dc5f478d98ed4297a8ae638fe90df050');
INSERT INTO `sys_role_menu` VALUES ('121b1db2c51d4d3ba7b8c0ebc3e1e32e', 'a21876314a764438b6af6bfa422ec09a', '2');
INSERT INTO `sys_role_menu` VALUES ('15b2a5beabe44f178e4ef76ff7d191ee', '164f77a778a341d8925251350be7f916', '649b484b58414d91aefa5a26143e6557');
INSERT INTO `sys_role_menu` VALUES ('18684be2cb5246e992729924e5651c05', '737933bffef640329a4f864c4e2746ba', '1');
INSERT INTO `sys_role_menu` VALUES ('1a75a9b255464dc3b250425dce81a676', '164f77a778a341d8925251350be7f916', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('1c6642cb915442359d9fc2543b5a8c79', 'a21876314a764438b6af6bfa422ec09a', '488ef1eff57b4827acade7c0744278ce');
INSERT INTO `sys_role_menu` VALUES ('1e0e01a71b8842e7ac203036f7384685', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('1f51087024e4483b876576101c5a0865', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '1');
INSERT INTO `sys_role_menu` VALUES ('21629df3640f4ccdb74c597142840ddd', 'a21876314a764438b6af6bfa422ec09a', '3f26102ef0e04c3c9328cb97067cc5fa');
INSERT INTO `sys_role_menu` VALUES ('2206b8a75e484ccabfce6a086e14bb43', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', '2');
INSERT INTO `sys_role_menu` VALUES ('231ae010cf1e4b329a9bcafdd8132988', '737933bffef640329a4f864c4e2746ba', 'f899f3d3baec4571b1c786717f9906fd');
INSERT INTO `sys_role_menu` VALUES ('23e9069b749e4c25b964bbcbb897b64a', '2a9b728a431246b08f853c2529e6ba84', '3b18f3d776c74266b63c2542825aa3c3');
INSERT INTO `sys_role_menu` VALUES ('26533797de9340c5b96add1f00d2d34e', '737933bffef640329a4f864c4e2746ba', 'f5a20c82110b4a3ea9e30ca01a038680');
INSERT INTO `sys_role_menu` VALUES ('2aafe086a2024a2e8e8fc2e3edf15407', '2a9b728a431246b08f853c2529e6ba84', 'f899f3d3baec4571b1c786717f9906fd');
INSERT INTO `sys_role_menu` VALUES ('2bf6a07b541a4a8fa0bc30968b86d227', '737933bffef640329a4f864c4e2746ba', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('2efb72c9f9d349a7b423c64beee4e095', '737933bffef640329a4f864c4e2746ba', '3');
INSERT INTO `sys_role_menu` VALUES ('2f4917cb5c3842fe89a2f6fa7f0183b2', '2a9b728a431246b08f853c2529e6ba84', '4');
INSERT INTO `sys_role_menu` VALUES ('35c27b37c89c4e6ca7160ef320329961', '1d0d43b9fbbe4c078beb4a732309fe64', '1');
INSERT INTO `sys_role_menu` VALUES ('3aceb6111772490e9887904fb54949e3', 'eb2e1fa3caa448658da909cf141788f8', '9');
INSERT INTO `sys_role_menu` VALUES ('3c3356a0de424c7793956f3120ec0155', '737933bffef640329a4f864c4e2746ba', '686630c7cb624cc786dcdc9958971a6b');
INSERT INTO `sys_role_menu` VALUES ('3d989d32ccee49b4a50567a428d67822', '737933bffef640329a4f864c4e2746ba', 'b4e7232189b14cf3ba160cf7b0d3bf37');
INSERT INTO `sys_role_menu` VALUES ('3feeac67800b4b2d9a7a32b990d2047e', '2a9b728a431246b08f853c2529e6ba84', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('48dd8350fb7a4c3cb4406cbfa78aa524', 'a21876314a764438b6af6bfa422ec09a', '1');
INSERT INTO `sys_role_menu` VALUES ('4941228c03564673b30677cdfcfce014', '737933bffef640329a4f864c4e2746ba', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('4b02c6885182461b8afc8fa8df861eb8', '737933bffef640329a4f864c4e2746ba', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('537af500a6b9442eb71e0d77a1ea4841', '1d0d43b9fbbe4c078beb4a732309fe64', '9');
INSERT INTO `sys_role_menu` VALUES ('540bbdd5f82243a9bd22d018ed963b5e', '737933bffef640329a4f864c4e2746ba', '0c9b5fc8b44b41d1871a8fc8300247ec');
INSERT INTO `sys_role_menu` VALUES ('5627ff1dd2304e51b16ccd3f4d785fcd', '164f77a778a341d8925251350be7f916', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('5f0977d73e2843d39a10d966887522fa', '2a9b728a431246b08f853c2529e6ba84', '60dda993d87647f5989c15f14f866df9');
INSERT INTO `sys_role_menu` VALUES ('5ffd9a6e37b54d26823c11ae6e7da521', '737933bffef640329a4f864c4e2746ba', '3f26102ef0e04c3c9328cb97067cc5fa');
INSERT INTO `sys_role_menu` VALUES ('61eab4643d1d4e31b51947b9b2ac6265', '737933bffef640329a4f864c4e2746ba', '60dda993d87647f5989c15f14f866df9');
INSERT INTO `sys_role_menu` VALUES ('69dfd4db18b14a9caab5c5492591e26b', '737933bffef640329a4f864c4e2746ba', 'c0c304be5c294114b5bc0d0c3acef992');
INSERT INTO `sys_role_menu` VALUES ('6f41c85dd5174f78ad2db183db359b55', 'a21876314a764438b6af6bfa422ec09a', '4');
INSERT INTO `sys_role_menu` VALUES ('712118e6fe374f92b3beaffc1019952a', 'f08487637b0d4bfc9accc14cbca6f1cd', '3');
INSERT INTO `sys_role_menu` VALUES ('71c15e90717d4edf92b7223ff72d6fd2', '737933bffef640329a4f864c4e2746ba', '649b484b58414d91aefa5a26143e6557');
INSERT INTO `sys_role_menu` VALUES ('72af0b08728742c6a7ee6bb5ba1d37d8', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '1db9105008404a3485b6fac30dba3c0e');
INSERT INTO `sys_role_menu` VALUES ('750868dfc79a4a32841da56d1601a8d1', 'f08487637b0d4bfc9accc14cbca6f1cd', '1');
INSERT INTO `sys_role_menu` VALUES ('766608c5b0d6464f97b99ffe5395d253', '737933bffef640329a4f864c4e2746ba', '9c51e94cef99435780fb72bdb923a2ab');
INSERT INTO `sys_role_menu` VALUES ('77f2e21563614d3e96b42fd167408d6e', '737933bffef640329a4f864c4e2746ba', '4');
INSERT INTO `sys_role_menu` VALUES ('77fd54d3ab0d4eaa8605346d93095eb9', 'eb2e1fa3caa448658da909cf141788f8', '8');
INSERT INTO `sys_role_menu` VALUES ('7b464f14ffbe4a32a550a7b16f360517', '164f77a778a341d8925251350be7f916', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('7d32bd0b812240f7b7fca184e6b17625', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('7f2d88d5d4f3473b8f61fd1390e9fd1e', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '4');
INSERT INTO `sys_role_menu` VALUES ('82728dbd138243c48e18b3af4f98147b', '164f77a778a341d8925251350be7f916', '1');
INSERT INTO `sys_role_menu` VALUES ('829dc9e65dba4a3a9e3f76a906151205', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', '1');
INSERT INTO `sys_role_menu` VALUES ('82b6803711884de5b40e79b8d278d8f8', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', 'f5a20c82110b4a3ea9e30ca01a038680');
INSERT INTO `sys_role_menu` VALUES ('8696d65710da4fd39b32716bff349745', '2a9b728a431246b08f853c2529e6ba84', 'b4e7232189b14cf3ba160cf7b0d3bf37');
INSERT INTO `sys_role_menu` VALUES ('8a4de646d12c411d8d28cec9642e776e', '2a9b728a431246b08f853c2529e6ba84', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('8a6381f1ddf943a2a3bc42629c339f15', '1d0d43b9fbbe4c078beb4a732309fe64', '8');
INSERT INTO `sys_role_menu` VALUES ('8be3672bb953491e8ec6289c909c9ae3', '2a9b728a431246b08f853c2529e6ba84', 'a5ebf29168234406939856bc6890c86b');
INSERT INTO `sys_role_menu` VALUES ('90a83e581e16416bbb69aa4a5d6b59f4', '737933bffef640329a4f864c4e2746ba', 'a5ebf29168234406939856bc6890c86b');
INSERT INTO `sys_role_menu` VALUES ('92e58707aa6d423ebe62bd356f204d3d', '737933bffef640329a4f864c4e2746ba', '2');
INSERT INTO `sys_role_menu` VALUES ('96043e15cb0a49a4ab871a5bfa4aaa50', '2a9b728a431246b08f853c2529e6ba84', '3');
INSERT INTO `sys_role_menu` VALUES ('976499d229b349dba84d804986b5a598', 'a21876314a764438b6af6bfa422ec09a', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('9ab36f6479234e7a8fcb642ef3b4fbc0', '737933bffef640329a4f864c4e2746ba', '1db9105008404a3485b6fac30dba3c0e');
INSERT INTO `sys_role_menu` VALUES ('9b51121484c64285b4726941a80e998c', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '2');
INSERT INTO `sys_role_menu` VALUES ('9c988de0c4334b93af5cb4c827cfbba2', '2a9b728a431246b08f853c2529e6ba84', 'dc5f478d98ed4297a8ae638fe90df050');
INSERT INTO `sys_role_menu` VALUES ('9e02696f59654038af6ba409542ec415', '2a9b728a431246b08f853c2529e6ba84', '2');
INSERT INTO `sys_role_menu` VALUES ('a099cc695e2b4ed790040b02048e3aa2', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', 'be659f4c66fb4db989f654eb408d86e1');
INSERT INTO `sys_role_menu` VALUES ('a9c1a424b3c2448a837198aa376b0c30', '737933bffef640329a4f864c4e2746ba', '809db56d93e848e8b43396e125803884');
INSERT INTO `sys_role_menu` VALUES ('aad8290942334ab8ae924eec121246e5', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', '649b484b58414d91aefa5a26143e6557');
INSERT INTO `sys_role_menu` VALUES ('ad55487f431741299fa0d55a281fea91', '2a9b728a431246b08f853c2529e6ba84', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('adff36f9a7b34e1aa0cef6ba66398e46', 'a21876314a764438b6af6bfa422ec09a', '3');
INSERT INTO `sys_role_menu` VALUES ('ae206c19b7aa48eeacf3665cded4f306', 'a21876314a764438b6af6bfa422ec09a', '686630c7cb624cc786dcdc9958971a6b');
INSERT INTO `sys_role_menu` VALUES ('afee6635bae9403a85097631d76ad7ad', 'f08487637b0d4bfc9accc14cbca6f1cd', '2');
INSERT INTO `sys_role_menu` VALUES ('b0c218d14aed40e0a701d89368d49f66', '164f77a778a341d8925251350be7f916', '2');
INSERT INTO `sys_role_menu` VALUES ('b22af56a663b4c35a37de4dcd0559f37', '2a9b728a431246b08f853c2529e6ba84', '649b484b58414d91aefa5a26143e6557');
INSERT INTO `sys_role_menu` VALUES ('b71cea3a72d545ad9d1fb5f302c0d035', 'a21876314a764438b6af6bfa422ec09a', '60dda993d87647f5989c15f14f866df9');
INSERT INTO `sys_role_menu` VALUES ('bb55745930954773b7f2972d3115442d', '2a9b728a431246b08f853c2529e6ba84', '686630c7cb624cc786dcdc9958971a6b');
INSERT INTO `sys_role_menu` VALUES ('bbdd4516f284426483bb3e9075d229e5', '2a9b728a431246b08f853c2529e6ba84', '1db9105008404a3485b6fac30dba3c0e');
INSERT INTO `sys_role_menu` VALUES ('bccc077fd2824fc1ad236361c2d1921e', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '488ef1eff57b4827acade7c0744278ce');
INSERT INTO `sys_role_menu` VALUES ('bffa3ab039c648c9b307050ce3135496', '737933bffef640329a4f864c4e2746ba', '3987d383a7a74b45902e14e027d9b56e');
INSERT INTO `sys_role_menu` VALUES ('c015003b62a84e44aa678bdd9d9ce99b', 'a21876314a764438b6af6bfa422ec09a', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('c280e565620442f988ceb829289f60c1', 'ab7e4b34e5d141fa8566fdbb5d3e66f7', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('cd40c275647b4254ae92169c6406952e', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '42dd5ae31e3a43b3a197440e8ec19a94');
INSERT INTO `sys_role_menu` VALUES ('d200f95d02f74bb58747d26e675152b4', '737933bffef640329a4f864c4e2746ba', '488ef1eff57b4827acade7c0744278ce');
INSERT INTO `sys_role_menu` VALUES ('d40cf656336c45f19610f4a6172cbfd9', '737933bffef640329a4f864c4e2746ba', '42dd5ae31e3a43b3a197440e8ec19a94');
INSERT INTO `sys_role_menu` VALUES ('d9e86f4a7d6145358e96c1a576d57990', '2a9b728a431246b08f853c2529e6ba84', '1');
INSERT INTO `sys_role_menu` VALUES ('e045bf84cd0345a2811d3906de5f7b40', 'a21876314a764438b6af6bfa422ec09a', 'dc5f478d98ed4297a8ae638fe90df050');
INSERT INTO `sys_role_menu` VALUES ('e37a31ed66df4a9a83ecfb5a574a8c28', '2a9b728a431246b08f853c2529e6ba84', 'be659f4c66fb4db989f654eb408d86e1');
INSERT INTO `sys_role_menu` VALUES ('e8ec8b53a6f543b0a54986a0ecc6799b', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '3');
INSERT INTO `sys_role_menu` VALUES ('ee9bc21d592e472b911b0b150085719a', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', 'c0c304be5c294114b5bc0d0c3acef992');
INSERT INTO `sys_role_menu` VALUES ('eec604d8d35d4529a260c1505ec41ce1', '2a9b728a431246b08f853c2529e6ba84', '488ef1eff57b4827acade7c0744278ce');
INSERT INTO `sys_role_menu` VALUES ('f0ff3269b1994d76b015c59bed009386', 'a21876314a764438b6af6bfa422ec09a', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('f4ad113b195c4efdae8cda777691bec4', '3bd9f9e5fa8a4e0587a78cf697e4a9ce', '809db56d93e848e8b43396e125803884');
INSERT INTO `sys_role_menu` VALUES ('f85b67e9af3d42afa018b8790d660189', 'a21876314a764438b6af6bfa422ec09a', '1db9105008404a3485b6fac30dba3c0e');
INSERT INTO `sys_role_menu` VALUES ('f8cecaee2e1d44bd9b1f42eecce37ec9', '737933bffef640329a4f864c4e2746ba', '87d3ef8ecfae40be95f9ac5ac2c1adc8');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `userState` int(2) NOT NULL DEFAULT '1' COMMENT '用户状态,1-启用,-1禁用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userDesc` varchar(300) DEFAULT NULL COMMENT '描述',
  `userImg` varchar(300) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('230e099407244982895ad972929d7228', 'test123', 'CC03E747A6AFBBCBF8BE7668ACFEBEE5', '1', '2017-07-06 14:37:07', '', '/images/face.jpg');
INSERT INTO `sys_user` VALUES ('8ec475bfc69041a4a3984c5510f7982b', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2017-07-05 17:13:45', '超级管理员', '/upload/2017-07-06/bw98sjevkkgi3cvzls5hqpc2dxhzo7qv.jpg');
INSERT INTO `sys_user` VALUES ('be9cb9ae66b64c54a85abee36c274a55', 'test2', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2017-07-05 17:15:07', '测试用户', '/upload/2017-07-05/gwh9rtgdr5ykm3wk2etilrazzgwf3k0d.jpg');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `Id` varchar(50) NOT NULL COMMENT '主键',
  `userId` varchar(50) NOT NULL COMMENT '用户主键',
  `roleId` varchar(50) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '8ec475bfc69041a4a3984c5510f7982b', '737933bffef640329a4f864c4e2746ba');
INSERT INTO `sys_user_role` VALUES ('e293016199924f698771625ab376c1f6', 'be9cb9ae66b64c54a85abee36c274a55', '2a9b728a431246b08f853c2529e6ba84');

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` bigint(20) NOT NULL,
  `classCode` varchar(50) DEFAULT NULL,
  `className` varchar(50) DEFAULT NULL COMMENT '班级名称',
  `teacherId` bigint(20) DEFAULT NULL COMMENT '授课老师',
  `organId` bigint(20) DEFAULT NULL COMMENT '所属机构，冗余',
  `leval` int(4) DEFAULT NULL COMMENT '班级等级',
  `capacity` int(4) DEFAULT NULL COMMENT '最多容纳人容量',
  `createTime` datetime DEFAULT NULL,
  `isEnd` int(1) DEFAULT NULL COMMENT '是否结束',
  `status` int(4) DEFAULT NULL,
  `realQty` int(20) DEFAULT NULL COMMENT '实际容纳学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------

-- ----------------------------
-- Table structure for t_class_courses
-- ----------------------------
DROP TABLE IF EXISTS `t_class_courses`;
CREATE TABLE `t_class_courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organizationId` bigint(20) DEFAULT NULL COMMENT '所属机构',
  `courseCode` varchar(50) DEFAULT NULL COMMENT '课程代码',
  `courseName` varchar(512) DEFAULT NULL COMMENT '课程名称',
  `canQty` int(4) DEFAULT NULL COMMENT '课时数量',
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `courseCateId` varchar(10) DEFAULT NULL COMMENT '大课程类型Code',
  `classId` bigint(20) DEFAULT NULL,
  `surplusClassLesson` int(10) DEFAULT NULL COMMENT '剩余课时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class_courses
-- ----------------------------

-- ----------------------------
-- Table structure for t_class_lesson
-- ----------------------------
DROP TABLE IF EXISTS `t_class_lesson`;
CREATE TABLE `t_class_lesson` (
  `id` bigint(20) NOT NULL,
  `lessonSeq` int(11) DEFAULT NULL COMMENT '课时编号',
  `courseId` bigint(20) DEFAULT NULL COMMENT '所属课程',
  `beginTime` datetime DEFAULT NULL COMMENT '上课开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '上课结束时间',
  `shouldQty` int(4) DEFAULT NULL COMMENT '应到人数',
  `realQty` int(4) DEFAULT NULL COMMENT '实到人数',
  `content` varchar(2046) DEFAULT NULL,
  `shouldTearcherId` bigint(20) DEFAULT NULL COMMENT '应该上课老师',
  `realTearcherId` bigint(20) DEFAULT NULL COMMENT '实际上课老师',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class_lesson
-- ----------------------------

-- ----------------------------
-- Table structure for t_clocks
-- ----------------------------
DROP TABLE IF EXISTS `t_clocks`;
CREATE TABLE `t_clocks` (
  `id` bigint(20) NOT NULL,
  `classId` bigint(20) DEFAULT NULL COMMENT '班级ID',
  `studentId` bigint(20) DEFAULT NULL COMMENT '学生ID',
  `organizationId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '签到签退时间',
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1:签到，2：签退',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_clocks
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL,
  `dynamicId` bigint(20) DEFAULT NULL,
  `fromUserId` bigint(20) DEFAULT NULL COMMENT '评论者',
  `toUserId` bigint(20) DEFAULT NULL COMMENT '评论给谁',
  `fromUserName` varchar(50) DEFAULT NULL,
  `toUserName` varchar(50) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `createTime` datetime DEFAULT NULL,
  `createPlace` varchar(256) DEFAULT NULL,
  `coordinate` varchar(256) DEFAULT NULL COMMENT '坐标',
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------

-- ----------------------------
-- Table structure for t_course_category
-- ----------------------------
DROP TABLE IF EXISTS `t_course_category`;
CREATE TABLE `t_course_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseCategoryName` varchar(255) DEFAULT NULL,
  `courseCategoryCode` int(4) DEFAULT NULL,
  `canQty` int(10) DEFAULT '0' COMMENT '课时数量',
  `explain` varchar(512) DEFAULT NULL COMMENT '简单说明',
  `organId` bigint(20) DEFAULT NULL COMMENT '所属机构',
  `status` int(4) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `alreadyStudySum` int(10) DEFAULT NULL COMMENT '已经招多少人',
  `wishStudySum` int(10) DEFAULT NULL COMMENT '希望招多少个学生',
  `marketPrice` double(20,4) DEFAULT NULL COMMENT '市场价格',
  `preferenPrice` double(20,4) DEFAULT NULL COMMENT '优惠价格',
  `preferStartTime` datetime DEFAULT NULL COMMENT '优惠时间',
  `preferEndTime` datetime DEFAULT NULL COMMENT '课程介绍',
  `courseRemark` text,
  `courseType` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `categoryType` varchar(20) DEFAULT NULL COMMENT '课程分类',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_category
-- ----------------------------
INSERT INTO `t_course_category` VALUES ('1', '机器人初级培训', '4', '20', '20', '1', '0', '2017-10-23 12:03:14', '14', '20', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_dictionarie
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionarie`;
CREATE TABLE `t_dictionarie` (
  `id` bigint(20) NOT NULL,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of t_dictionarie
-- ----------------------------

-- ----------------------------
-- Table structure for t_dyimg
-- ----------------------------
DROP TABLE IF EXISTS `t_dyimg`;
CREATE TABLE `t_dyimg` (
  `id` bigint(20) NOT NULL,
  `thumbImgUrl` varchar(512) DEFAULT NULL COMMENT '缩略图',
  `originalImgUrl` varchar(512) DEFAULT NULL COMMENT '原图',
  `status` int(4) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dyimg
-- ----------------------------

-- ----------------------------
-- Table structure for t_dynamicmsg
-- ----------------------------
DROP TABLE IF EXISTS `t_dynamicmsg`;
CREATE TABLE `t_dynamicmsg` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL COMMENT '动态正文',
  `classId` bigint(20) DEFAULT NULL COMMENT '动态关联的班级',
  `classLessonId` bigint(20) DEFAULT NULL COMMENT '关联的课时',
  `imgUrlIds` varchar(128) DEFAULT NULL COMMENT '图片或视频ID集合',
  `videoId` bigint(20) DEFAULT NULL,
  `jurisdiction` int(4) DEFAULT NULL COMMENT '权限，是否公开等',
  `createTime` datetime DEFAULT NULL,
  `createPlace` varchar(512) DEFAULT NULL COMMENT '创建地方',
  `coordinate` varchar(512) DEFAULT NULL COMMENT '坐标，经纬度',
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dynamicmsg
-- ----------------------------

-- ----------------------------
-- Table structure for t_dyvideo
-- ----------------------------
DROP TABLE IF EXISTS `t_dyvideo`;
CREATE TABLE `t_dyvideo` (
  `id` bigint(20) NOT NULL,
  `bookImg` varchar(512) DEFAULT NULL COMMENT '封面图片',
  `videoUrl` varchar(512) DEFAULT NULL,
  `size` int(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dyvideo
-- ----------------------------

-- ----------------------------
-- Table structure for t_fllow_course
-- ----------------------------
DROP TABLE IF EXISTS `t_fllow_course`;
CREATE TABLE `t_fllow_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `courseCateId` bigint(20) DEFAULT NULL,
  `organId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注的课程';

-- ----------------------------
-- Records of t_fllow_course
-- ----------------------------

-- ----------------------------
-- Table structure for t_fllow_organ
-- ----------------------------
DROP TABLE IF EXISTS `t_fllow_organ`;
CREATE TABLE `t_fllow_organ` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `organId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fllow_organ
-- ----------------------------

-- ----------------------------
-- Table structure for t_follow_parent
-- ----------------------------
DROP TABLE IF EXISTS `t_follow_parent`;
CREATE TABLE `t_follow_parent` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `leaderPid` bigint(20) DEFAULT NULL,
  `followPid` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follow_parent
-- ----------------------------

-- ----------------------------
-- Table structure for t_follow_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_follow_teacher`;
CREATE TABLE `t_follow_teacher` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `teacherId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follow_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for t_front_user
-- ----------------------------
DROP TABLE IF EXISTS `t_front_user`;
CREATE TABLE `t_front_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nickName` varchar(215) DEFAULT NULL COMMENT '昵称',
  `userName` varchar(50) DEFAULT NULL COMMENT '登录用户名',
  `passWord` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '登录邮箱',
  `mobilePhone` varchar(20) DEFAULT NULL COMMENT '登录手机号',
  `wxCode` varchar(100) DEFAULT NULL COMMENT '微信号',
  `createTime` datetime DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '所在城市',
  `userType` int(11) DEFAULT NULL COMMENT '用户类型 1:家长，2:老师，3:机构',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `introduce` varchar(512) DEFAULT NULL COMMENT '描述，个性签名，简介',
  `headImg` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of t_front_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_handle
-- ----------------------------
DROP TABLE IF EXISTS `t_handle`;
CREATE TABLE `t_handle` (
  `id` bigint(20) NOT NULL,
  `handleNo` varchar(255) DEFAULT NULL,
  `hanleName` varchar(255) DEFAULT NULL,
  `handleType` varchar(255) DEFAULT NULL,
  `studentId` bigint(20) DEFAULT NULL,
  `teacherId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `isEffective` int(10) DEFAULT NULL COMMENT '是否有效',
  `organId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_handle
-- ----------------------------

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like` (
  `id` bigint(20) NOT NULL,
  `createUserId` bigint(20) DEFAULT NULL,
  `createUserName` varchar(50) DEFAULT NULL,
  `coordinate` varchar(512) DEFAULT NULL COMMENT '坐标',
  `createTime` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_like
-- ----------------------------

-- ----------------------------
-- Table structure for t_nowxy
-- ----------------------------
DROP TABLE IF EXISTS `t_nowxy`;
CREATE TABLE `t_nowxy` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `longitude` varchar(218) DEFAULT NULL,
  `dimension` varchar(218) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_nowxy
-- ----------------------------

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organCode` varchar(50) DEFAULT NULL,
  `organName` varchar(126) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `cityarea` varchar(50) DEFAULT NULL,
  `linkman` varchar(50) DEFAULT NULL,
  `telePhone` varchar(20) DEFAULT NULL,
  `mobilePhone` varchar(20) DEFAULT NULL,
  `coordinate` varchar(218) DEFAULT NULL,
  `leval` int(4) DEFAULT NULL,
  `introduce` varchar(512) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL COMMENT '登录信息',
  `createTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  `createUser` bigint(50) DEFAULT NULL,
  `logoImg` varchar(512) DEFAULT NULL,
  `organRemark` varchar(2046) DEFAULT NULL COMMENT '机构简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_organization
-- ----------------------------
INSERT INTO `t_organization` VALUES ('1', 'TESTCODE', '测试教育机构', '河南', '郑州', '李先生', '18625787344', '18625783477', null, '1', null, null, '2017-10-23 11:54:06', '0', '1', null, null);

-- ----------------------------
-- Table structure for t_organ_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_organ_activity`;
CREATE TABLE `t_organ_activity` (
  `id` bigint(20) NOT NULL,
  `organId` bigint(20) DEFAULT NULL,
  `activityTitle` varchar(255) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `comment` varchar(1024) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `statusDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_organ_activity
-- ----------------------------

-- ----------------------------
-- Table structure for t_parent
-- ----------------------------
DROP TABLE IF EXISTS `t_parent`;
CREATE TABLE `t_parent` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `userId` bigint(215) DEFAULT NULL COMMENT '登录用户ID',
  `mobilePhone` varchar(20) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL COMMENT '家长真实姓名',
  `idCode` varchar(20) DEFAULT NULL COMMENT '身份证',
  `telePhone` varchar(20) DEFAULT NULL COMMENT '手机',
  `wxCode` varchar(100) DEFAULT NULL COMMENT '微信号',
  `sex` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '所在城市',
  `introduce` varchar(512) DEFAULT NULL,
  `familyIncome` varchar(10) DEFAULT NULL COMMENT '家庭收入',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `createTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_parent
-- ----------------------------

-- ----------------------------
-- Table structure for t_recruit_item
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit_item`;
CREATE TABLE `t_recruit_item` (
  `recruitItemId` bigint(20) NOT NULL,
  `recruitId` bigint(20) DEFAULT NULL,
  `followTime` datetime DEFAULT NULL,
  `followResult` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recruitItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recruit_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_recruit_order
-- ----------------------------
DROP TABLE IF EXISTS `t_recruit_order`;
CREATE TABLE `t_recruit_order` (
  `recruitId` bigint(20) NOT NULL,
  `recruitNo` varchar(255) DEFAULT NULL COMMENT '招生编号',
  `customerName` varchar(255) DEFAULT NULL,
  `sex` int(4) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `courseAdviser` varchar(255) DEFAULT NULL COMMENT '课程顾问',
  `teacherId` bigint(20) DEFAULT NULL,
  `followUpStatus` varchar(255) DEFAULT NULL COMMENT '跟进状态',
  `followTime` datetime DEFAULT NULL,
  `followQty` int(10) DEFAULT NULL,
  `flag` varchar(1024) DEFAULT NULL,
  `intention` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recruitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recruit_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentCode` varchar(255) DEFAULT NULL COMMENT '学号',
  `parentId` bigint(20) DEFAULT NULL COMMENT '学生家长ID',
  `birthDay` date DEFAULT NULL COMMENT '出生日期',
  `nickName` varchar(200) DEFAULT NULL COMMENT '昵称',
  `realName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `sex` int(4) DEFAULT NULL,
  `parentType` bigint(4) DEFAULT NULL COMMENT '1:儿子，2：女儿，3：我自己',
  `headImg` varchar(512) DEFAULT NULL COMMENT '头像',
  `createTime` datetime DEFAULT NULL,
  `introduce` varchar(512) DEFAULT NULL COMMENT '简介',
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for t_student_course
-- ----------------------------
DROP TABLE IF EXISTS `t_student_course`;
CREATE TABLE `t_student_course` (
  `id` bigint(20) NOT NULL,
  `studentId` bigint(20) DEFAULT NULL COMMENT '所属学生Id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `organizationId` bigint(20) DEFAULT NULL,
  `coursesId` bigint(20) DEFAULT NULL COMMENT '课程Id',
  `isEnd` int(4) DEFAULT '0',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `createTime` datetime DEFAULT NULL COMMENT '办理时间',
  `coursePrice` double(20,2) DEFAULT NULL COMMENT '卖个这个学生的实际价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student_course
-- ----------------------------

-- ----------------------------
-- Table structure for t_student_lessones
-- ----------------------------
DROP TABLE IF EXISTS `t_student_lessones`;
CREATE TABLE `t_student_lessones` (
  `id` bigint(20) NOT NULL,
  `studentId` bigint(20) DEFAULT NULL,
  `lessonId` bigint(20) DEFAULT NULL,
  `status` int(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `condition` varchar(255) DEFAULT NULL COMMENT '进度',
  `oldId` bigint(20) DEFAULT NULL COMMENT '调课后保存老的Id',
  `courseId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student_lessones
-- ----------------------------

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) DEFAULT NULL,
  `organizationId` bigint(20) DEFAULT NULL,
  `teacherCode` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(50) DEFAULT NULL,
  `telePhone` varchar(50) DEFAULT NULL,
  `idCode` varchar(255) DEFAULT NULL,
  `sex` int(4) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `teachRemark` varchar(2046) DEFAULT NULL,
  `introduce` varchar(512) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('1', '1', '1', null, '李镜池', '18625783477', '0371-55225222', null, '1', '32', null, null, null, null);
