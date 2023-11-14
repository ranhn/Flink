# Apache-Flink

Apache Flink远程代码执行漏洞

漏洞地址：http://flink.xxxxxxx.com/#/submit

漏洞描述：Apache Flink远程代码执行漏洞

漏洞详情：

http://flink.xxxxxxx.com/#/submit

FLINK默认部署无授权，在1.9版本出过漏洞。当前业务是1.13版本，原有的命令执行POC已经无法使用，且网络无法反弹shell。

笔者自行结合flink特性创新设计了一个即使无法反弹或DNS解析依然可以执行命令并回显的POC，成功在机器上执行cat /etc/passwd并回显。

![image](https://github.com/ranhn/Apache-Flink/assets/107679328/e539589a-a938-4897-99d3-85f84475c17e)

POC：详情见 https://github.com/ranhn/Flink


漏洞版本：<=1.13

修复建议：
1、升级到最新版本。

