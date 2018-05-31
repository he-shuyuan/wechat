/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.blockChain.service;

import java.util.Vector;
import com.ower.hsy.blockChain.other.Block;
import com.ower.hsy.blockChain.other.Transaction;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年3月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public interface IBlockChainService {

    public Vector<Block> getBlocks();

    public void setBlocks(Vector<Block> block);

    public Vector<Transaction> getCurrentTransactions();

    public void setCurrentTransactions(Vector<Transaction> currentTransactions);

    public Block lastBlock();

    Block createBlock(long proof);

    int newTransactions(String sender, String recipient, long amount);

    int newTransactions(Transaction transaction);
    /**
     * 区块的哈希值
     * @param block
     * @return Object
     */
    public Object createHash(Block block);
    
    /**
     * 简单的工作量证明: 
     *   - 查找一个 p' 使得 hash(pp') 以4个0开头 
     *   - p 是上一个块的证明, p' 是当前的证明
     *  上一个块的证明
     * @return
     */
    public long proofOfWork();

}
