{
     "msgtype": "markdown",
     "markdown": {
         "title":"${title}",
         "text": "${text}"
     },
      "at": {
          <#if atMobiles??>
           "atMobiles": [
               <#list atMobiles as mobile>
               "${mobile}"<#if mobile_has_next>,</#if>
               </#list>
           ],
          </#if>
          "isAtAll": ${isAtAll?default("false")}
      }
 }