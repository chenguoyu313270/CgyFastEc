package com.diabin.fastec.example.generators;

import com.flj.latte.annotations.PayEntryGenerator;
import com.flj.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by Administrator on 2018\6\4 0004.
 */
@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.diabin.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
