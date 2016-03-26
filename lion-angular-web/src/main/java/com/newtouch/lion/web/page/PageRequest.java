package com.newtouch.lion.web.page;/**
 * Created by jovi on 3/25/16.
 */

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: XiQing
 * </p>
 *
 * @author MaoJiaWei
 * @version 1.0
 */
public class PageRequest implements Pageable{
    private final int page;
    private final int size;

    public PageRequest(int page, int size) {
        if(page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        } else if(size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        } else {
            this.page = page;
            this.size = size;
        }
    }

    public static PageRequest newPage(int page, int size) {
        return new PageRequest(page, size);
    }

    public int getPageSize() {
        return this.size;
    }

    public int getPageNumber() {
        return this.page;
    }

    public int getOffset() {
        return (this.page - 1) * this.size;
    }

    public boolean hasPrevious() {
        return this.page > 0;
    }

    public Pageable previousOrFirst() {
        return this.hasPrevious()?this.previous():this.first();
    }

    public Pageable next() {
        return new PageRequest(this.getPageNumber() + 1, this.getPageSize());
    }

    public Pageable previous() {
        return this.getPageNumber() == 0?this:new PageRequest(this.getPageNumber() - 1, this.getPageSize());
    }

    public Pageable first() {
        return new PageRequest(0, this.getPageSize());
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + this.page;
        result1 = 31 * result1 + this.size;
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj != null && this.getClass() == obj.getClass()) {
            PageRequest other = (PageRequest)obj;
            return this.page == other.page && this.size == other.size;
        } else {
            return false;
        }
    }
}
