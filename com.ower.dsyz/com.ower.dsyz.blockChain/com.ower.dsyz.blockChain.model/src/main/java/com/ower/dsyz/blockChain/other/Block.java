/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.blockChain.other;

import java.io.Serializable;
import java.util.Vector;


/**
 * <pre>
 * 区块
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年3月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Block implements Serializable {

    /**
     * serialVersionUID:TODO。
     */
    private static final long serialVersionUID = 1L;

    private int index;
    
    private String previousHash;
    
    private Vector<Transaction> transactions = new Vector<>(); 
    
    private long timestamp;
    
    private long proof;

    
    public int getIndex() {
        return index;
    }

    
    public String getPreviousHash() {
        return previousHash;
    }

    
    public Vector<Transaction> getTransactions() {
        return transactions;
    }

    
    public long getTimestamp() {
        return timestamp;
    }

    
    public long getProof() {
        return proof;
    }

    
    public void setIndex(int index) {
        this.index = index;
    }

    
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    
    public void setTransactions(Vector<Transaction> transactions) {
        this.transactions = transactions;
    }

    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    
    public void setProof(long proof) {
        this.proof = proof;
    }
    
    
}
