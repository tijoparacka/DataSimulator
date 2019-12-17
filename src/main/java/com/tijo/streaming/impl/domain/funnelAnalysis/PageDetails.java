package com.tijo.streaming.impl.domain.funnelAnalysis;

import com.tijo.streaming.impl.domain.Event;

public class PageDetails extends Event {
    private int conversion ;
    private String pageId ;
    private String cookie;
    PageDetails(int conversion , String pageId, String cookie){
        this.conversion = conversion;
        this.pageId = pageId;
        this.cookie = cookie;
    }
    @Override
    public String toString() {
        return System.currentTimeMillis() +","+pageId+","+cookie+","+conversion;
    }
}
