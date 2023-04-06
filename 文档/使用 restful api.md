# 使用 restful api

[TOC]

版本：KYLIN3.1.3

### 执行查询

1. 执行 `python -c "import base64; print base64.standard_b64encode('$UserName:$Password')"` 获得用户名密码的验证信息

```sh
[root@zgg conf]# python -c "import base64; print base64.standard_b64encode('ADMIN:KYLIN')"
QURNSU46S1lMSU4=
```

2. 执行查询

```sh
[root@zgg kylin-3.1.3]# curl -X POST -H "Authorization: Basic QURNSU46S1lMSU4=" -H "Content-Type: application/json" -d '{ "sql":"select dname,sum(sal) from emp e join dept d on e.deptno = d.deptno group by dname", "project":"FirstProject" }' http://zgg:7070/kylin/api/query
{"columnMetas":[{"isNullable":1,"displaySize":256,"label":"DNAME","name":"DNAME","schemaName":"DEFAULT","catelogName":null,"tableName":"DEPT","precision":256,"scale":0,"columnType":12,"columnTypeName":"VARCHAR","autoIncrement":false,"caseSensitive":true,"searchable":false,"currency":false,"signed":true,"writable":false,"definitelyWritable":false,"readOnly":true},{"isNullable":1,"displaySize":15,"label":"EXPR$1","name":"EXPR$1","schemaName":null,"catelogName":null,"tableName":null,"precision":15,"scale":0,"columnType":8,"columnTypeName":"DOUBLE","autoIncrement":false,"caseSensitive":true,"searchable":false,"currency":false,"signed":true,"writable":false,"definitelyWritable":false,"readOnly":true}],"results":[["RESEARCH","10875.0"],["SALES","9400.0"],["ACCOUNTING","8750.0"]],"cube":"CUBE[name=FirstCube]","affectedRowCount":0,"isException":false,"exceptionMessage":null,"duration":9690,"totalScanCount":3,"totalScanBytes":117,"hitExceptionCache":false,"storageCacheUsed":false,"traceUrl":null,"pushDown":false,"partial":false}
```

### 自动构建 cube 脚本

Kylin 提供了 Restful API，因次我们可以将构建 cube 的命令写到脚本中，将脚本交给
azkaban 或者 oozie 这样的调度工具，以实现定时调度的功能。

kylin_cube_build.sh 脚本如下:

```sh
#!/bin/bash
#从第 1 个参数获取 cube_name
cube_name=$1
#从第 2 个参数获取构建 cube 时间
if [ -n "$2" ]
then
 do_date=$2
else
 do_date=`date -d '-1 day' +%F`
fi
#获取执行时间的 00:00:00 时间戳(0 时区)
start_date_unix=`date -d "$do_date 08:00:00" +%s`
#秒级时间戳变毫秒级
start_date=$(($start_date_unix*1000))
#获取执行时间的 24:00 的时间戳
stop_date=$(($start_date+86400000))
curl -X PUT -H "Authorization: Basic QURNSU46S1lMSU4=" -H 
'Content-Type: application/json' -d '{"startTime":'$start_date', 
"endTime":'$stop_date', "buildType":"BUILD"}' 
http://hadoop102:7070/kylin/api/cubes/$cube_name/build
```

注：我们没有修改 kylin 的时区，因此 kylin 内部只识别 0 时区的时间，0 时区的 0 点是东 8 区的早上 8 点，因此我们在脚本里要写$do_date 08:00:00 来弥补时差问题。

--------------------------

来自：

[https://www.bilibili.com/video/BV1QU4y1F7QH?p=12](https://www.bilibili.com/video/BV1QU4y1F7QH?p=12)