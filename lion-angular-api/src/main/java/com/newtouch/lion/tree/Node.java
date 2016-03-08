package com.newtouch.lion.tree;

import java.util.List;

/**
 * JsTree的结构
 * Created by wanglijun on 16/1/7.
 */
public class Node {

    private  Long id;

    private  String text;

    private String icon;

    private State state;

    private Long parentId;

    private List<Node> children;

    /***
     *
     * @param children
     * @param icon
     * @param id
     * @param parentId
     * @param state
     * @param text
     */
    public Node(List<Node> children, String icon, Long id, Long parentId, State state, String text) {
        this.children = children;
        this.icon = icon;
        this.id = id;
        this.parentId = parentId;
        this.state = state;
        this.text = text;
    }

    /**
     *
     * @param icon
     * @param id
     * @param state
     * @param text
     */
    public Node(String icon, Long id, State state, String text) {
        this.icon = icon;
        this.id = id;
        this.state = state;
        this.text = text;
    }


    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "children=" + children +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", state=" + state +
                '}';
    }
}
