{
    "msgtype": "text",
    "text": {
        "content": "${content}",
        "mentioned_list":[],
        <#if atMobiles??>
            "mentioned_mobile_list":[
                <#list atMobiles as mobile>
                    "${mobile}"<#if mobile_has_next>,</#if>
                 </#list>
            ]
        </#if>
    }
}