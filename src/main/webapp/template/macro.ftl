<#--
    宏,类似于java的方法这种形式
    <#macro 宏的名字 参数1 参数2 ...>

    </#macro>

    调用宏
    <@宏的名字 参数1 参数2 .../>
-->
<#macro top title="这是标题" encoding="UTF-8" description="这是网页">
    <html>
	<head lang="en">
        <meta charset=${encoding}>
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;" />
        <meta name="format-detection" content="telephone=no" />
        <title>${title}</title>
        <meta name="description" content="${description}" />

		<#nested> <#--  -->
    </head>
</#macro>

<#macro mymacro p1 p2>
    结果:${p1+p2}
</#macro>

<@mymacro p1=12 p2=24/>
<@mymacro p1=1 p2=24/>

<#--
    <p>Animals:
      <ul>
          <li>Mouse
          <li>Elephant
          <li>Python
      </ul>
-->

<#macro list title items>
  <p>${title?cap_first}:
  <ul>
    <#list items as x>
      <li>${x?cap_first}
    </#list>
  </ul>
</#macro>
<@list items=["mouse", "elephant", "python"] title="Animals"/>


<#--
      <img src="/context/images/test.png"
        alt="Test"
        height="50"
        width="100"
      >
-->
<#macro img src extra...>
  <img src="/context${src?html}"
    <#list extra?keys as attr>
        ${attr}="${extra[attr]?html}"
    </#list>
  >
</#macro>
<@img src="/images/test.png" width=100 height=50 alt="Test"/>


<#--
    <#nested >相当于占位符
-->
<#macro do_write>
    1.<#nested >
    2.<#nested >
</#macro>
<@do_write >pyj</@do_write>

<#--
      1 pyj.
      2 pyj.
      3 pyj.
-->
<#macro do_thrice>
    <#nested 1>
    <#nested 2>
    <#nested 3>
</#macro>
<@do_thrice ; x>
    ${x} pyj.
</@do_thrice>

<#--
      1. 0.5
      2. 1
      3. 1.5
      4. 2 Last!
-->
<#macro repeat count>
    <#list 1..count as x>
        <#nested x, x/2, x==count>
    </#list>
</#macro>
<@repeat count=4 ; c, halfc, last>
    ${c}. ${halfc}<#if last> Last!</#if>
</@repeat>

<#--
    return使用
       将会输出 Test text
-->
<#macro test>
  Test text
    <#return>
  Will not be printed.
</#macro>
<@test/>