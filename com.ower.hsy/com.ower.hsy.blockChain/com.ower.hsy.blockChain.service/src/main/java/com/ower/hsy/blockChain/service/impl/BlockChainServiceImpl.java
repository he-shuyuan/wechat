/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.hsy.blockChain.service.impl;

import java.util.Vector;
import org.springframework.stereotype.Service;
import com.ower.hsy.blockChain.other.Block;
import com.ower.hsy.blockChain.other.Transaction;
import com.ower.hsy.blockChain.service.IBlockChainService;
import com.ower.hsy.common.core.util.HashUtil;


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
@Service
public class BlockChainServiceImpl implements IBlockChainService {

    // 存储区块链
    private Vector<Block> blocks = new Vector<>();
    
    // 该实例变量用于当前的交易信息列表
    private Vector<Transaction> currentTransactions = new Vector<>();
    
    public BlockChainServiceImpl(){
        createBlock(300,"000");
    }
    @Override
    public Vector<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void setBlocks(Vector<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Vector<Transaction> getCurrentTransactions() {

        return this.currentTransactions;
    }

    @Override
    public void setCurrentTransactions(Vector<Transaction> currentTransactions) {
        this.currentTransactions = currentTransactions;
    }

    @Override
    public Block lastBlock() {
        return this.getBlocks().get(this.getBlocks().size() - 1);
    }

    @Override
    public Block createBlock(long proof) {
        Block block = new Block();
        block.setIndex(this.getBlocks().size()+1);
        block.setTimestamp(System.currentTimeMillis());
        block.setPreviousHash(HashUtil.sha256Hex(this.getBlocks().get(this.getBlocks().size()-1)));
        block.setProof(proof);
        block.setTransactions(this.getCurrentTransactions());
        // 重置当前的交易信息列表
        setCurrentTransactions(new Vector<Transaction>());

        this.getBlocks().add(block);

        return block;
    }


    @Override
    public int newTransactions(String sender,String recipient,long amount) {
        Transaction transaction = new Transaction();
        transaction.setRecipient(recipient);
        transaction.setSender(sender);
        transaction.setAmount(amount);
        return this.newTransactions(transaction);
      
    }

    @Override
    public Object createHash(Block block) {

        return null;
    }

    private Block createBlock(long proof,String previousHash){
        Block block = new Block();
        block.setIndex(this.getBlocks().size()+1);
        block.setTimestamp(System.currentTimeMillis());
        block.setPreviousHash(previousHash);
        block.setProof(proof);
        block.setTransactions(this.getCurrentTransactions());
        this.setCurrentTransactions(new Vector<Transaction>());
        this.getBlocks().add(block);
        return block;
    }

    @Override
    public int newTransactions(Transaction transaction) {
        this.getCurrentTransactions().add(transaction);
        return this.lastBlock().getIndex() + 1;
    }
    
    

    @Override
    public long proofOfWork() {
        long lastProof = this.lastBlock().getProof();
        long proof = 0;
        while (!validProof(lastProof, proof)) {
            proof += 1;
        }
        return proof;
    }

    /**
     * 验证证明: 是否hash(last_proof, proof)以4个0开头?
     * 
     * @param last_proof
     *            上一个块的证明
     * @param proof
     *            当前的证明
     * @return 以4个0开头返回true，否则返回false
     */
    private boolean validProof(long lastProof, long proof) {
        String guess = lastProof + "" + proof;
        String guess_hash = HashUtil.sha256Hex(guess);
        return guess_hash.startsWith("0000");
    }
}
