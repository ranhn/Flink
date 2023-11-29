# Flink

Apache Flink remote code execution vulnerability

Vulnerability address：http://flink.xxxxxxx.com/#/submit

vulnerability description：Apache Flink remote code execution vulnerability

Vulnerability details：

http://flink.xxxxxxx.com/#/submit

The default deployment of FLINK is unlicensed and there was a vulnerability in version 1.9. The current business is version 1.13, the original command execution POC can no longer be used, and the network cannot rebound shell.

Combined with the characteristics of flink, the author creatively designed a POC that can execute commands and echo even if it can not rebound or DNS parsing. It successfully executes cat / etc/passwd on the machine and echoes.

![image](https://github.com/ranhn/Flink/assets/107679328/3df8d89c-124d-4b19-a8fa-c5f6326a1856)


POC：详情见 

https://github.com/ranhn/Flink/tree/main/src/main/java)https://github.com/ranhn/Flink/tree/main/src/main/java

https://github.com/ranhn/Flink/blob/main/pom.xml


Vulnerable version：<=1.13.2

Repair recommendations:

1. Upgrade to the latest version.

