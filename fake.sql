Set NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `fake_user`;
CREATE TABLE `fake_user` (
    user_id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL unique ,
    user_pwd varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    user_email varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    user_submit_count INT(11) UNSIGNED NULL DEFAULT 0,
    user_solved_count INT(11) UNSIGNED NULL DEFAULT 0,
    user_problem_count INT(11) UNSIGNED NULL DEFAULT 0,
    `user_is_locked` tinyint(1) UNSIGNED NULL DEFAULT 0,
    `user_is_admin` tinyint(1) UNSIGNED NULL DEFAULT 0,
    PRIMARY KEY (user_id, user_name) USING BTREE ,
    INDEX `or_user_id`(`user_id`) USING BTREE ,
    INDEX `or_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `fake_user` (user_id, user_name, user_pwd, user_email, user_submit_count, user_solved_count, user_problem_count, user_is_locked, user_is_admin) VALUES
(1, 'admin', 'fake135oj1password', '541686002@qq.com', 0, 0, 0, 0, 0),
(233, 'user1', 'test1', 'test@user.com', 0, 0, 0, 0, 0),
(777, 'user2', 'test2', 'test@user.com', 0, 0, 0, 0, 0);

DROP TABLE IF EXISTS `fake_role`;
CREATE TABLE `fake_role` (
    `role_id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `role_name` varchar(20) NOT NULL,
    `role_desc` varchar(50) NULL DEFAULT ''
) ENGINE = InnoDB AUTO_INCREMENT = 233 ROW_FORMAT = DYNAMIC ;

INSERT INTO `fake_role` VALUES (1, 'Reader', 'Read Problem');
INSERT INTO `fake_role` VALUES (2, 'Editor', 'Write Problem, Can not delete');
INSERT INTO `fake_role` VALUES (3, 'SubmitM', 'submit Problem');
INSERT INTO `fake_role` VALUES (4, 'Admin', 'Admin');

DROP TABLE IF EXISTS `fake_permission`;
CREATE TABLE `fake_permission` (
    `per_id` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `model_name` varchar(20) NOT NULL ,
    `permission` VARCHAR(20) NOT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 233 ROW_FORMAT = DYNAMIC ;

INSERT INTO `fake_permission` VALUES (1, 'Read', 'PROBLEM:READ');
INSERT INTO `fake_permission` VALUES (2, 'Edit', 'PROBLEM:ADD');
INSERT INTO `fake_permission` VALUES (3, 'Submit', 'PROBLEM:SUBMIT');
INSERT INTO `fake_permission` VALUES (4, 'Admin', 'Admin');

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role`(
    `ID` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `uid` int(11) UNSIGNED NOT NULL ,
    `rid` int(11) UNSIGNED NOT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 233 ROW_FORMAT = DYNAMIC ;

INSERT INTO `user_role` (uid, rid) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (233, 1), (233, 3), (777, 1);

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
    `ID` int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `rid` int(11) UNSIGNED NOT NULL ,
    `pid` int(11) UNSIGNED NOT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 233 ROW_FORMAT = DYNAMIC ;

INSERT INTO `role_permission` (rid, pid) VALUES
(1, 1), (2, 2), (3, 3), (4, 4);

DROP TABLE IF EXISTS `fake_news`;

CREATE TABLE `fake_news` (
    ID int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    needName tinyint(1) UNSIGNED NULL DEFAULT 0,
    title varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    content varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE InnoDB AUTO_INCREMENT = 233 ROW_FORMAT = DYNAMIC ;

INSERT INTO `fake_news` (needName, title, content) VALUES
(1, '成功注册!', '%s 成功注册了fake OJ, 大家快去叫ta做题, 目前为%d题'),
(1, '震惊! 手速狗来到fakeOJ', '%s 是个手速狗, 居然来到了fake OJ, 他居然已经a了%d道题了!'),
(1, '震古烁今! ta 来了!', '辣个男人, %s来了! Fake OJ为之颤抖, 目前为止, 他已经a掉了 %d题!');

DROP TABLE IF EXISTS `fake_problems`;

CREATE TABLE `fake_problems` (
    id int(5) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    submitCount int(11) UNSIGNED NOT NULL DEFAULT 0,
    solvedCount int(11) UNSIGNED NOT NULL DEFAULT 0,
    title varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
    content longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    hint varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT ''
) ENGINE InnoDB AUTO_INCREMENT = 1 ROW_FORMAT DYNAMIC ;

INSERT INTO `fake_problems` (title, content) VALUES
('a + b', '请求出23 + 23的值'),
('Multiples of 3 and 5', '我们列出小于10的3或5的倍数, 为3, 5, 6, 9, 其和为23, 请求出1000以内3或5的倍数的和'),
('Even Fibonacci numbers', 'Fibonacci数列是通过前两项和生成的,起始为1, 2,前10项就是1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ..., 请求出值不超过四百万的Fibonacci偶数值和'),
('Largest prime factor', '13195的素因子为 5, 7, 13 和 29, 那么600851475143的最大素因子呢?'),
('儿子', '小明的爸爸如何称呼小明的妈妈的儿子'),
('Largest palindrome product', '回文数在两个方向上都相同, 由两个两位数乘积构成的最大回文数为9009=91 x 99\n查找由两个三位数的乘积组成的最大回文数'),
('Smallest multiple', '2520是最小的能被1~10每个数整除的数,求出能被1~20整除的最小正整数'),
('Sum square difference', '前10个数平方和为385, 前十个数和的平方为3025, 他们相差2640,求出前一百个数平方和和和的平方的绝对值差值.'),
('传输时间', '一台计算机通过网络发送信息,发往艾欧尼亚的概率是0.5, 平均传输时间为0.05.\n 发往艾泽拉斯的概率是0,3, 平均传输时间为0.1.\n 发往泽诺尼亚的概率是0.2, 平均传输时间为0.3.\n求随机变量传输时间的期望'),
('10001st prime', '前6个素数为2, 3, 5, 7, 11, 13, 求出第10001个素数.'),
('众所周知', '众所周知, 北纬47°,东经28°5\'是基希讷乌, 那么这个指标维度加上59, 经度加上1.5后,属于那个城市?'),
('乱搞', '在下面这个1000位数中连续四个数字的乘积最大值为9 × 9 × 8 × 9 = 5832，求出连续13位数字的最大乘积

<div style="text-align: center">
73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450
</div>'),
('毕达哥拉斯三元组', '毕达哥拉斯三元组为 a<sup>2</sup> + b<sup>2</sup> = c<sup>2</sup>, a < b < c,只有一个毕达哥拉斯三元组满足a + b + c = 1000, 找到这个三元组，输出a, b, c的乘积');

DROP TABLE IF EXISTS `fake_problem_data`;

CREATE TABLE `fake_problem_data` (
    id int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pid int(5) UNSIGNED NOT NULL ,
    data longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE InnoDB AUTO_INCREMENT = 1 ROW_FORMAT DYNAMIC ;

INSERT INTO `fake_problem_data` (pid, data) VALUES
(1, '46'), (2, '233168'), (3, '4613732'), (4, '6857'), (5, '儿子'), (6, '906609'),
(7, '232792560'), (8, '25164150'), (9, '0.115'), (10, '104743'), (11, '重庆'), (12, '23514624000'),
(13, '31875000');

DROP TABLE IF EXISTS `fake_submit_statue`;

CREATE TABLE `fake_submit_statue` (
    id int(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pid int(5) UNSIGNED NOT NULL ,
    author int(11) UNSIGNED NOT NULL ,
    status int UNSIGNED NOT NULL ,
    content longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    submitTime DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE InnoDB AUTO_INCREMENT = 1 ROW_FORMAT DYNAMIC ;

SET FOREIGN_KEY_CHECKS = 1;