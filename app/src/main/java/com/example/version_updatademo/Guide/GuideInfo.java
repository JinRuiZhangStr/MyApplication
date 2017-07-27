package com.example.version_updatademo.Guide;

import java.util.List;

/**
 * Created by 张金瑞 on 2017/7/24.
 */

public class GuideInfo {
    /**
     * status : 200
     * updata : false
     * data : {"guidepic":["http://00.minipic.eastday.com/20170713/20170713135604_d1014d6f253149cdf5f16a2a9992c6fc_4.jpeg","http://static.jstv.com/gather/hl/20170713/32/16980251754786271652.jpg","http://n.sinaimg.cn/ent/4_img/upload/fc8e0ed6/20170620/vvO0-fyhfxph4298189.jpg?imageslim%7CimageView2/2/w/700/interlace/1"]}
     */

    private int status;
    private boolean updata;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isUpdata() {
        return updata;
    }

    public void setUpdata(boolean updata) {
        this.updata = updata;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> guidepic;

        public List<String> getGuidepic() {
            return guidepic;
        }

        public void setGuidepic(List<String> guidepic) {
            this.guidepic = guidepic;
        }
    }
}
