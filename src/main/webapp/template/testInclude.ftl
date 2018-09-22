<#--
    include标签是 包含某个freemarker文件,然后在 包含 文件里可以引用 被包含 文件中定义的变量

    include标签 加上这个文件名

    假如同时包含两个文件,并且两文件中的定义的变量名相同,取值会取最后 被包含的文件所定义的变量

    为此,要想同时取到两文件定义的变量,则需用
-->
<#--<#include "include1.ftl">
<#include "include2.ftl">-->
<#import "include1.ftl" as i1>
<#import "include2.ftl" as i2>

${i1.age}
${i2.age}