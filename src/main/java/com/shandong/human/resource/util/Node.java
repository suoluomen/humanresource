package com.shandong.human.resource.util;

import java.util.List;

/**
 * 无限级节点模型
 * Author: Noprom <tyee.noprom@qq.com>
 * Date: 3/20/16 1:50 PM.
 */

public class Node {
    /**
     * 节点id
     */
    private Long id;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 父节点id
     */
    private Long parentId;

    private List<Node> childList;

    public List<Node> getChildList() {
        return childList;
    }

    public void setChildList(List<Node> childList) {
        this.childList = childList;
    }

    public Node() {
    }

    Node(Long id, Long parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    Node(Long id, String nodeName, Long parentId) {
        this.id = id;
        this.nodeName = nodeName;
        this.parentId = parentId;
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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Node{" +
                "id=" + id +
                ", nodeName='" + nodeName + '\'' +
                ", parentId=" + parentId +
                "=>{");
        Integer cnt = 0;
        traverseNode(builder, getChildList(), cnt);
        builder.append("}}");
        return builder.toString();
    }

    private void traverseNode(StringBuilder builder, List<Node> list, Integer cnt) {
        for (Node child : list) {
            //builder.append("Child" + child.getId() + ", name = " + child.getNodeName() + ";");
            for (int i = 0; i < cnt; i++) {
                builder.append("--");
            }
            builder.append(">");
            builder.append(child);
            traverseNode(builder, child.getChildList(), cnt + 2);
        }
    }
}