<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1>Welcome ${user}!</h1>
<p>Our latest product:
    <a href="${latestProduct.url}">${latestProduct.name}</a>!

your sex:
    <#if sex=1>
        man
        <#elseif sex=0>
        women
        <#else >
        unknown
    </#if>
your class:
    <#switch class>
        <#case '1701'>
            this class is 1701;
            <#break >
        <#case '1702'>
            this class is 1702;
            <#break >
        <#case '1703'>
            this class is 1703;
            <#break >
        <#default >
            this class is not existing;
    </#switch>
your id:
    <#-- 这是freemarker的注解 -->
    <#list strs as str>
    <#-- 临时变量名_index 代表该变量名的下标-->
        <#if str_index%2=0>
            <font color="red">${str}--</font>
        </#if>
    </#list>
now user: ${tb.name}---${tb.age}
    <#list userList as user>
        ${user.name}----${user.age}
    </#list>
</body>
</html>