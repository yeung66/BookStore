package main.domain;

import java.util.List;

/**
 * @author : CWQ
 * @Description : 分页类
 * @date :2018-06-15
 **/
public class Page {
    private int totalpage;  //总页数
    private int pagesize = 3; //页面大小，默认为3
    private int totalRecord; //总记录数
    private int pagenum; //当前页码
    private List list; //存放书本数据
    private int startpage; //起始页码
    private int endpage; //终止页面
    private int showPageCount = 4; //显示的页码数，即endPage - startPage
    private int startindex; //存放取数据的下标

    public Page(int pagenum, int totalRecord){
        this.pagenum = pagenum;
        this.totalRecord = totalRecord;
        //得出总页数
        this.totalpage = (this.totalRecord + this.pagesize - 1) / this.pagesize;
        //得出取数据的下标
        this.startindex = (this.pagenum - 1) * this.pagesize;
        //算出起始与终止页码
        if(this.totalpage <= showPageCount){
            this.startpage = 1;
            this.endpage = this.totalpage;
        }else{
            this.startpage = pagenum - 1;
            this.endpage = pagenum + 1;
            if(this.startpage < 1){
                this.startpage = 1;
                this.endpage = showPageCount;
            }
            if(this.endpage > this.totalpage){
                this.endpage = this.totalpage;
                this.startpage = this.totalpage - 2;
            }
        }
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getStartpage() {
        return startpage;
    }

    public void setStartpage(int startpage) {
        this.startpage = startpage;
    }

    public int getEndpage() {
        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    public int getStartindex() {
        return startindex;
    }

    public void setStartindex(int startindex) {
        this.startindex = startindex;
    }
}
