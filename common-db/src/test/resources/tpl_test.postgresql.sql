drop database IF EXISTS db_test;
create database db_test;
\c db_test;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "role";
CREATE TABLE "public"."role" (
  "id" serial NOT NULL ,
  "name" varchar(20) COLLATE "pg_catalog"."default",
  "descp" varchar(36) COLLATE "pg_catalog"."default",
  CONSTRAINT "role_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."role" 
  OWNER TO "postgres";

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO "role" VALUES ('4', 'updater_82', 'updater_d_1');
INSERT INTO "role" VALUES ('5', 'n_7', 'descp_4');
INSERT INTO "role" VALUES ('6', 'n_11', 'descp_64');
INSERT INTO "role" VALUES ('7', 'n_98', 'descp_79');
INSERT INTO "role" VALUES ('8', 'n_21', 'descp_5');
INSERT INTO "role" VALUES ('9', 'n_96', 'descp_98');
INSERT INTO "role" VALUES ('10', 'n_96', 'descp_29');
INSERT INTO "role" VALUES ('11', 'n_42', 'descp_54');
INSERT INTO "role" VALUES ('12', 'n_12', 'descp_65');
INSERT INTO "role" VALUES ('18', 'n_13', 'descp_52');
INSERT INTO "role" VALUES ('19', 'n_80', 'descp_99');
INSERT INTO "role" VALUES ('20', 'n_34', 'descp_18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "user";
CREATE TABLE "public"."user" (
  "id" serial NOT NULL ,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "mobile_no" varchar(11) COLLATE "pg_catalog"."default",
  "age" int4,
  CONSTRAINT "user_pkey" PRIMARY KEY ("id"),
  CONSTRAINT "USER_NAME_UQ" UNIQUE ("username"),
  CONSTRAINT "MOBILE_NO_UQ" UNIQUE ("mobile_no")
)
;

ALTER TABLE "public"."user" 
  OWNER TO "postgres";

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "user" VALUES ('1', 'yufei', '123456', '12345678901', '5');
INSERT INTO "user" VALUES ('2', 'featherfly', '654321', '98765432101', '5');
INSERT INTO "user" VALUES ('3', 'yufei15', '123456', '15345678915', '15');
INSERT INTO "user" VALUES ('4', 'yufei25', '123456', '25345678925', '25');
INSERT INTO "user" VALUES ('5', 'yufei35', '123456', '35345678935', '35');
INSERT INTO "user" VALUES ('6', 'yufei45', '123456', '45345678945', '45');
INSERT INTO "user" VALUES ('7', 'yufei55', '123456', '55345678955', '55');
INSERT INTO "user" VALUES ('8', 'featherfly10', '654321', '10765432110', '10');
INSERT INTO "user" VALUES ('9', 'featherfly20', '654321', '20765432120', '20');
INSERT INTO "user" VALUES ('10', 'featherfly30', '654321', '30765432130', '30');
INSERT INTO "user" VALUES ('11', 'featherfly40', '654321', '40765432140', '40');
INSERT INTO "user" VALUES ('12', 'featherfly50', '654321', '50765432150', '50');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "user_role";
CREATE TABLE "user_role" (
  "user_id" int4 NOT NULL,
  "role_id" int4 NOT NULL,
  "descp" varchar(255),
  CONSTRAINT "user_role_pkey" PRIMARY KEY ("user_id","role_id")
);

ALTER TABLE "public"."user_role" 
  OWNER TO "postgres";

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO "user_role" VALUES ('1', '2', null);
INSERT INTO "user_role" VALUES ('2', '2', 'desc_cc66');
INSERT INTO "user_role" VALUES ('51', '218', 'descp401');
INSERT INTO "user_role" VALUES ('215', '890', 'descp446');
INSERT INTO "user_role" VALUES ('522', '367', 'descp866');
INSERT INTO "user_role" VALUES ('760', '263', 'descp848');
INSERT INTO "user_role" VALUES ('769', '248', 'descp489');
INSERT INTO "user_role" VALUES ('920', '122', 'descp581');


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "user_info";
CREATE TABLE "user_info" (
  "id" serial NOT NULL ,
  "user_id" int4 NOT NULL,
  "name" varchar(255) ,
  "descp" varchar(255) ,
  "province" varchar(255) ,
  "city" varchar(255) ,
  "district" varchar(255) ,
  CONSTRAINT "user_info_pkey" PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------

INSERT INTO "user_info" ("id", "user_id", "name", "descp", "province", "city", "district") VALUES ('1', '1', '羽飞', '羽飞描述', '四川', '成都', '金牛');
INSERT INTO "user_info" ("id", "user_id", "name", "descp", "province", "city", "district") VALUES ('2', '2', '翼', '翼描述', '广东', '深圳', '罗湖');

DROP TABLE IF EXISTS "cms_article";
CREATE TABLE "cms_article" (
  "id" serial NOT NULL ,
  "title" varchar(255) DEFAULT NULL,
  "content" varchar(255) DEFAULT NULL,
  CONSTRAINT "cms_article_pkey" PRIMARY KEY ("id")
);
