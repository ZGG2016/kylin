# python 客户端

这里采用的是在 docker 中运行 kylin 的模式，kylin 版本为 3.1.0。

-----------------------------------------------------------------------

如何在 docker 中运行 kylin，点击 [这里](https://kylin.apache.org/cn/docs31/install/kylin_docker.html)

如何安装使用 docker，点击 [这里](https://www.runoob.com/docker/centos-docker-install.html)

pull 下来的镜像中，python 版本是 2.6.6 版本，需要升级版本，因为官网要求 python 解释器版本在 2.7+，或者 3.4+ 以上。但是，重启进入 docker 后，会被自动清理。

如何在 docker centos 镜像中安装 python，点击 [这里](https://blog.csdn.net/u012798683/article/details/108218872)

安装 python 过程中，发现需要更新 yum repo源，如何更新点击 [这里](https://www.cnblogs.com/fatt/p/10302121.html)

如何安装 pip3，点击 [这里](https://www.cnblogs.com/fyly/p/11112169.html)

执行如下命令安装包：

```sh
pip3 install --upgrade kylinpy

pip3 install --upgrade sqlalchemy 
```

测试如下：

```python
>>> import sqlalchemy as sa
>>> kylin_engine = sa.create_engine('kylin://ADMIN:KYLIN@localhost:7070/learn_kylin?version=v1')
>>> results = kylin_engine.execute('SELECT count(*) FROM KYLIN_SALES')
>>> [e for e in results]
[(9988,)]
>>> kylin_engine.table_names()
['KYLIN_ACCOUNT', 'KYLIN_CAL_DT', 'KYLIN_CATEGORY_GROUPINGS', 'KYLIN_COUNTRY', 'KYLIN_SALES']
```

【TODO】执行 Kylinpy 命令行工具出现如下错误：

```
[root@2ae3f20fac82 admin]# kylinpy
bash: kylinpy: command not found
```

---------------------------------------------

官网原文：[https://kylin.apache.org/cn/docs31/tutorial/kylin_client_tool.html](https://kylin.apache.org/cn/docs31/tutorial/kylin_client_tool.html)

github 主页：[https://github.com/Kyligence/kylinpy](https://github.com/Kyligence/kylinpy)