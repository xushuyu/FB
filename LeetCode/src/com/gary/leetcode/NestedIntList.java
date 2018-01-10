package com.gary.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Gary on 9/27/15.
 */




public class NestedIntList {

    private int value;
    private List<NestedIntList> intList;
    private boolean isNumber;

    public NestedIntList(int v) {
        this.value = v;
        this.isNumber = true;
    }

    public NestedIntList() {
        this.intList = new ArrayList<>();
        this.isNumber = false;
    }

    public void add(NestedIntList l) {
        intList.add(l);
    }

    public static NestedIntList fromString(String s) {
        if(!s.startsWith("[")) {
            return new NestedIntList(Integer.parseInt(s));
        }
        NestedIntList result = null;
        Stack<NestedIntList> stack = new Stack<>();
        int i = 0, left = 1;
        while(i < s.length()) {
            char c = s.charAt(i);
            if(c == '[') {
                NestedIntList num = new NestedIntList();
                if(!stack.isEmpty()) {
                    stack.peek().add(num);
                }
                stack.push(num);
                left = i+1;
            } else if(c == ',' || c == ']') {
                if(left != i) {
                    int v = Integer.parseInt(s.substring(left, i));
                    NestedIntList num = new NestedIntList(v);
                    stack.peek().add(num);
                }
                left = i+1;
                if(c == ']') result = stack.pop();
            }
            i++;
        }
        return result;
    }

    public String toString() {
        if(isNumber) {
            return ""+value;
        } else {
            return intList.toString();
        }
    }

    public static void main(String[] args) {
        NestedIntList list = NestedIntList.fromString("[123,456,[788,799,833],[[]],10,[]]");
        System.out.println(list);
    }
}