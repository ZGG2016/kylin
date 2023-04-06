# kylin-job 调度器

原文：[https://cwiki.apache.org/confluence/display/KYLIN/Comparison+of+Kylin+Job+scheduler](https://cwiki.apache.org/confluence/display/KYLIN/Comparison+of+Kylin+Job+scheduler)

------------------------------------

文章描述的版本是 kylin v3.1.0

job 调度器包括 `DefaultScheduler`、`DistributedScheduler` 和 `CuratorScheduler` 三种类型。

通过给属性 `kylin.job.scheduler.default` 设置不同的值来确定不同的调度器。

	0   -->  DefaultScheduler
	2   -->  DistributedScheduler
	100 -->  CuratorScheduler

类型见的区别见原文。