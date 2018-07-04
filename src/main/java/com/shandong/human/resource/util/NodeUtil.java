package com.shandong.human.resource.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试递归节点
 * <p>
 * Author: Noprom <tyee.noprom@qq.com>
 * Date: 3/20/16 1:50 PM.
 */
public class NodeUtil {

    private List<Node> returnList = new ArrayList<Node>();

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param pid  传入的父节点ID
     * @return String
     */
    public String getChildNodes(List<Node> list, Long pid) {
        if (list == null && pid == null) return "";
        for (Iterator<Node> iterator = list.iterator(); iterator.hasNext(); ) {
            Node node = (Node) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId().equals(pid)) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList.toString();
    }

    /**
     * 递归调用节点
     *
     * @param list
     * @param node
     */
    private void recursionFn(List<Node> list, Node node) {
        List<Node> childList = getChildList(list, node);// 得到子节点列表
        node.setChildList(childList);
        returnList.add(node);
        if (childList.size() > 0) {// 判断是否有子节点
            Iterator<Node> it = childList.iterator();
            while (it.hasNext()) {
                Node n = (Node) it.next();
                recursionFn(list, n);
            }
        } else {

        }
    }

    /**
     * 得到子节点列表
     *
     * @param list
     * @param node
     * @return
     */
    private List<Node> getChildList(List<Node> list, Node node) {
        List<Node> nodeList = new ArrayList<Node>();
        Iterator<Node> it = list.iterator();
        while (it.hasNext()) {
            Node n = (Node) it.next();
            if (n.getParentId().equals(node.getId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    // 本地模拟数据测试
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Node> nodeList = new ArrayList<Node>();
        Node node1 = new Node(1l, "蔬菜", 0l);
        Node node2 = new Node(2l, "水产", 0l);
        Node node3 = new Node(3l, "畜牧", 0l);
        Node node4 = new Node(4l, "瓜类", 1l);
        Node node5 = new Node(5l, "叶类", 1l);
        Node node6 = new Node(6l, "丝瓜", 4l);
        Node node7 = new Node(7l, "黄瓜", 4l);
        Node node8 = new Node(8l, "白菜", 1l);
        Node node9 = new Node(9l, "虾", 2l);
        Node node10 = new Node(10l, "鱼", 2l);
        Node node11 = new Node(11l, "牛", 3l);
        Node node12 = new Node(12l, "牛", 6l);
        Node node13 = new Node(13l, "牛", 12l);

        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        nodeList.add(node8);
        nodeList.add(node9);
        nodeList.add(node10);
        nodeList.add(node11);
        nodeList.add(node12);
        nodeList.add(node13);

        NodeUtil mt = new NodeUtil();
        System.out.println(mt.getChildNodes(nodeList, 0l));
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start) + "ms");
    }

}