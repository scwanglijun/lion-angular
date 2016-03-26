package com.newtouch.lion.web.page;/**
 * Created by jovi on 3/25/16.
 */

import com.newtouch.lion.page.PageResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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
public class Page<T> {
    private final long total;
    private final List<T> content;
    private final Pageable pageable;

    public Page(List<T> content, Pageable pageable, long total) {
        this.content = new ArrayList();
        Assert.notNull(content, "Content must not be null!");
        this.content.addAll(content);
        this.pageable = pageable;
        this.total = !content.isEmpty() && pageable != null && (long)(pageable.getOffset() + pageable.getPageSize()) > total?(long)(pageable.getOffset() + content.size()):total;
    }

    public Page(List<T> content) {
        this(content, (Pageable)null, null == content?0L:(long)content.size());
    }

    public int getTotalPages() {
        return this.getPageSize() == 0?1:(int)Math.ceil((double)this.total / (double)this.getPageSize());
    }

    public long getTotalElements() {
        return this.total;
    }

    public boolean hasNext() {
        return this.getPageNumber() + 1 < this.getTotalPages();
    }

    public boolean isLast() {
        return !this.hasNext();
    }

    public int getPageNumber() {
        return this.pageable == null?0:this.pageable.getPageNumber();
    }

    public int getPageSize() {
        return this.pageable == null?0:this.pageable.getPageSize();
    }

    public int getNumberOfElements() {
        return this.content.size();
    }

    public boolean hasPrevious() {
        return this.getPageNumber() > 0;
    }

    public boolean isFirst() {
        return !this.hasPrevious();
    }

    public Pageable nextPageable() {
        return this.hasNext()?this.pageable.next():null;
    }

    public Pageable previousPageable() {
        return this.hasPrevious()?this.pageable.previousOrFirst():null;
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    public String toString() {
        String contentType = "UNKNOWN";
        List content = this.getContent();
        if(content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", new Object[]{Integer.valueOf(this.getPageNumber()), Integer.valueOf(this.getTotalPages()), contentType});
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof Page)) {
            return false;
        } else {
            Page that = (Page)obj;
            return this.total == that.total && super.equals(obj);
        }
    }

    public int hashCode() {
        byte result = 17;
        int result1 = result + 31 * (int)(this.total ^ this.total >>> 32);
        result1 += 31 * super.hashCode();
        return result1;
    }

    public Page(PageResult<T> pageResult){
        Pageable pageable = PageRequest.newPage(pageResult.getCurrentPage(),pageResult.getPageSize());
        this.content = new ArrayList();
        Assert.notNull(content, "Content must not be null!");
        this.content.addAll(pageResult.getContent());
        this.pageable = pageable;
        this.total = !content.isEmpty() && pageable != null && (long)(pageable.getOffset() + pageable.getPageSize()) > pageResult.getTotalCount()?(long)(pageable.getOffset() + content.size()):pageResult.getTotalCount();
    }
}
