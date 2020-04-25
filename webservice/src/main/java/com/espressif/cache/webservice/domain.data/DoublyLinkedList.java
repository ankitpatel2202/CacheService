package com.espressif.cache.webservice.domain.data;

import lombok.Data;

@Data
public class DoublyLinkedList {
    private DoublyLinkedListNode head = null;
    private DoublyLinkedListNode tail = null;

    public void setHeadTo(DoublyLinkedListNode node){
        if (head == node){
            return;
        }else if (head == null){
            head = node;
            tail = node;
        }else if (head == tail){
            tail.prev = node;
            head = node;
            head.next = tail;
        }else{
            if(tail == node){
                removeTail();
            }
            node.removeBindings();
            head.prev = node;
            node.next = head;
            head = node;
        }

    }

    public void removeTail(){
        if(tail == null){
            return;
        }
        if(tail == head){
            head = null;
            tail = null;
            return;
        }
        tail = tail.prev;
        tail.next = null;
    }
}
