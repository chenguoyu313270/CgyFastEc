package com.diabin.fastec.example.generators;

import com.flj.latte.annotations.EntryGenerator;
import com.flj.latte.wechat.templates.WXEntryTemplate;

/** weixin
 * Created by Administrator on 2018\6\4 0004.
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.diabin.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public  interface WeChatEntry {

}
